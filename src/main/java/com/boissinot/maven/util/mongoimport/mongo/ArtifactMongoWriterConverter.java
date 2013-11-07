package com.boissinot.maven.util.mongoimport.mongo;

import com.boissinot.maven.util.mongoimport.domain.ArtifactObj;
import com.boissinot.maven.util.mongoimport.domain.Order;
import com.boissinot.maven.util.mongoimport.domain.Required;
import com.boissinot.maven.util.mongoimport.exception.MongoImportException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Gregory Boissinot
 */
public class ArtifactMongoWriterConverter implements Converter<ArtifactObj, DBObject> {

    private class MongoObjElement {
        private String fieldName;
        private Object filedValue;

        private MongoObjElement(String fieldName, Object filedValue) {
            this.fieldName = fieldName;
            this.filedValue = filedValue;
        }

        public String getFieldName() {
            return fieldName;
        }

        public Object getFiledValue() {
            return filedValue;
        }
    }

    public DBObject convert(ArtifactObj artifactObj) {
        DBObject dbo = new BasicDBObject();

        try {

            final Field[] fields = artifactObj.getClass().getDeclaredFields();
            Map<Integer, MongoObjElement> elementsMap = new TreeMap<Integer, MongoObjElement>();
            for (Field field : fields) {
                field.setAccessible(true);

                final org.springframework.data.mongodb.core.mapping.Field fieldAnnotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                String targetFieldName = (fieldAnnotation != null) ? fieldAnnotation.value() : field.getName();

                Object targetFieldValue = field.get(artifactObj);

                final Order orderFieldAnnotation = field.getAnnotation(Order.class);
                int orderFieldNumber = orderFieldAnnotation.value();
                if ((field.getAnnotation(Required.class) != null) || (targetFieldValue != null)) {
                    elementsMap.put(orderFieldNumber, new MongoObjElement(targetFieldName, targetFieldValue));
                }

                for (Map.Entry<Integer, MongoObjElement> elementEntry : elementsMap.entrySet()) {
                    MongoObjElement mongoObjElement = elementEntry.getValue();
                    dbo.put(mongoObjElement.fieldName, mongoObjElement.filedValue);
                }

            }
        } catch (IllegalAccessException iae) {
            throw new MongoImportException(iae);
        }

        return dbo;
    }
}
