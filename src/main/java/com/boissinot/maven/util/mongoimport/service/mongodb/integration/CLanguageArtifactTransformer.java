package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocumentForC;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Gregory Boissinot
 */
public class CLanguageArtifactTransformer {


    @Transformer
    @SuppressWarnings("unused")
    public MongoDBArtifactDocument addCMetadata(MongoDBArtifactDocument artifactObj, String repoURL) {
        //if (repoURL.contains("native")) {

            String artifactName = artifactObj.getName();

            artifactObj.setcMetadata(buildCArtfact(artifactName));

        //}
        return artifactObj;
    }

    private MongoDBArtifactDocumentForC buildCArtfact(String name) {
        return new MongoDBArtifactDocumentForC("x86", "Linux", "gcc", "static", "release");
    }

}

/*
CrashReporting-i386-Linux-gcc-static-release-1.0.3-i386-Linux-g%2B%2B-static.nar
CrashReporting-i386-Linux-gcc-static-release-1.0.3-noarch.nar
CrashReporting-i386-Linux-gcc-static-release-1.0.3.pom


YAT4Tango-i386-Linux-gcc-static-release-1.6.7-tango8-i386-Linux-g%2B%2B-static.nar
YAT4Tango-i386-Linux-gcc-static-release-1.6.7-tango8-noarch.nar
YAT4Tango-i386-Linux-gcc-static-release-1.6.7-tango8.pom

YAT4Tango-i386-Linux-gcc-shared-release-1.6.9b-tango8-i386-Linux-g%2B%2B-shared.nar
YAT4Tango-i386-Linux-gcc-shared-release-1.6.9b-tango8-noarch.nar
YAT4Tango-i386-Linux-gcc-shared-release-1.6.9b-tango8.pom

HDF5-x86-Windows-msvc-shared-release-1.8.3-1-x86-Windows-msvc-shared.nar
HDF5-x86-Windows-msvc-shared-release-1.8.3-1.pom


PlxApi-x86-Windows-msvc-shared-release-6.0.0-noarch.nar
PlxApi-x86-Windows-msvc-shared-release-6.0.0-x86-Windows-msvc-shared.nar
PlxApi-x86-Windows-msvc-shared-release-6.0.0.pom

python2.7-i386-Linux-gcc-shared-release-2.7.5-i386-Linux-g%2B%2B-shared.nar
python2.7-i386-Linux-gcc-shared-release-2.7.5-noarch.nar
python2.7-i386-Linux-gcc-shared-release-2.7.5.pom

ACE-x86-Windows-msvc-shared-debug-1.0.0-noarch.nar
ACE-x86-Windows-msvc-shared-debug-1.0.0-x86-Windows-msvc-shared.nar
ACE-x86-Windows-msvc-shared-debug-1.0.0.pom
 */
