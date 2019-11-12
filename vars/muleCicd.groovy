#!/usr/bin/env groovy
import gitCheckout
pipeline 
{
    agent { label "master" }
    stages 
    {
        stage('Git Checkout') 
        {
            steps 
            {
                gitCheckout(
                branch: "featurepraveen",
                url: "https://github.com/shreys-s/game-of-life.git")
            }
        }
    }
}
def call() 
{
	bat "E:\\apache-maven-3.6.2\\bin\\mvn -f pom.xml clean test package verify checkstyle:checkstyle pmd:pmd findbugs:findbugs javadoc:javadoc cobertura:cobertura -Pmetrics"
}
