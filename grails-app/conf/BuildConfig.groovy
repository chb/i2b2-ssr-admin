sgrails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}.war"

grails.project.dependency.resolution = {
    pom false
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums false // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
        mavenLocal()

        mavenRepo("http://repo.open.med.harvard.edu/nexus/content/repositories/releases")

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        runtime "com.unboundid:unboundid-ldapsdk:2.0.1"
        runtime "net.sf.opencsv:opencsv:2.3"
//        runtime("net.shrine:shrine-commons:1.13.1") {
//            excludes 'spring-expression', 'spring-core', 'spring-context', 'spring-tx',
//                    'spring-aop', 'spring-jdbc', 'spring-web', 'spring-test', 'aspectjrt',
//                    'aspectjweaver', 'cglib-nodep', 'ehcache', 'commons-collections',
//                    'hsqldb', 'jsr250-api', 'log4j', 'junit', 'mockito-core', 'jmock-junit4',
//                    'hibernate-core', 'hibernate-annotations', 'hibernate-commons-annotations', 'hibernate-entitymanager'
//        }
        runtime "com.sun.jersey:jersey-bundle:1.7"
        runtime "org.scala-lang:scala-library:2.10.0"
        runtime "net.liftweb:lift-json_2.10:2.5-M4"
        runtime "org.spin:tools:1.18.1"
        runtime "org.nuiton.thirdparty:JRI:0.8-4"
        runtime "mysql:mysql-connector-java:5.1.6"

        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        //runtime 'mysql:mysql-connector-java:5.1.20'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.2"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        compile ":quartz:1.0-RC9"
        compile ":twitter-bootstrap:2.3.2"
        build ":tomcat:$grailsVersion"
        runtime ":database-migration:1.2.1"
        compile ':cache:1.0.1'
    }
}
