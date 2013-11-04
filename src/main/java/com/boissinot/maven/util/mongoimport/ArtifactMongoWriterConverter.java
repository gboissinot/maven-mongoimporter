package com.boissinot.maven.util.mongoimport;

import com.boissinot.maven.util.mongoimport.domain.ArtifactObj;
import com.boissinot.maven.util.mongoimport.domain.Required;
import com.boissinot.maven.util.mongoimport.exception.MongoImportException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;

/**
 * @author Gregory Boissinot
 */
public class ArtifactMongoWriterConverter implements Converter<ArtifactObj, DBObject> {

    public DBObject convert(ArtifactObj artifactObj) {
        DBObject dbo = new BasicDBObject();

        try {

            final Field[] fields = artifactObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                final org.springframework.data.mongodb.core.mapping.Field fieldAnnotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                String targetFieldName = (fieldAnnotation != null) ? fieldAnnotation.value() : field.getName();

                Object targetFieldValue = field.get(artifactObj);
                if (field.getAnnotation(Required.class) != null) {
                    dbo.put(targetFieldName, targetFieldValue);
                } else if (targetFieldValue != null) {
                    dbo.put(targetFieldName, targetFieldValue);
                }

            }
        } catch (IllegalAccessException iae) {
            throw new MongoImportException(iae);
        }

        return dbo;
    }
}
