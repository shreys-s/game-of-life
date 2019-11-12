def ref() 
{
    return bat(script: 'mvn -f pom.xml clean install', returnStdout: true).trim()
}
