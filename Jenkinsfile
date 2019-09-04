node
{
   def SONAR_URL = 'http://192.168.56.104:9095'
   def SONAR_LOGIN='admin'
   def SONAR_PASSWORD='admin'
   properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '5', artifactNumToKeepStr: '5', daysToKeepStr: '5', numToKeepStr: '5')), pipelineTriggers([])])
   stage('Code Checkout')
   {
       checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'bbb48b43-0e32-4a0d-b833-3d611916d027', url: 'https://github.com/shreys-s/game-of-life.git']]])
   }
   stage('Maven Code Build')
   {
                sh "mvn -f pom.xml clean test package verify checkstyle:checkstyle pmd:pmd findbugs:findbugs javadoc:javadoc cobertura:cobertura -Pmetrics"
   }
   stage('Unit Testing')
   {
                junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                archive 'target/*.jar'
   }
   stage('Sonarqube Analysis')
   {
                withSonarQubeEnv('Sonar')
                {
            sh "mvn -e -B sonar:sonar -Dsonar.host.url=${SONAR_URL} -Dsonar.login=${SONAR_LOGIN} -Dsonar.password=${SONAR_PASSWORD} -Dsonar.scm.disabled=true"
                }
   }
        stage("Publish to Artifactory")
        {
            sh "mvn deploy" 
        }
        stage("Publish UserTest Stories")
        {
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "target/easyb", reportFiles: "easyb.html", reportName: "User Stories Report", reportTitles: ''])
        }
        stage('Prepare Docker Deployment')
        {
            parallel(
                        BuildImage:
                        {
                                sh 'docker build -t docker.io/shreys/gameoflife:${BUILD_NUMBER} .'
                        },
                        ContainerCheck:
                        {
                                sh 'docker stop game_app || true && docker rm game_app || true'
                        }
            )
        }
        stage('Push Docker Image')
        {
            sh 'docker push docker.io/shreys/gameoflife:${BUILD_NUMBER}'
        }
        stage("Docker Deployment")
        {
            sh 'docker run --name game_app -d -p 12001:8080 docker.io/shreys/gameoflife:${BUILD_NUMBER}'
            sh 'echo "Please verify running application here: http://192.168.56.104:12001/gameoflife"'
        }
        stage("Selenium Testing")
        {
	   	    sh "mvn -f gameoflife-selenium/pom.xml -Dhostname=192.168.56.104  -Dport=12001 -Dcontext=gameoflife -Dmaven.test.failure.ignore=true test"
	        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "gameoflife-selenium/target/surefire-reports", reportFiles: "emailable-report.html", reportName: "Selenium Report", reportTitles: ''])
        }
        stage("Performance Testing")
        {
            sh "mvn -f gameoflife-jmeter/pom.xml -Dhostname=192.168.56.104 -Dport=12001 -Dcontext=gameoflife clean verify -Pperformance"
        	publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "gameoflife-jmeter/target/reports", reportFiles: "index.html", reportName: "Jmeter Report", reportTitles: ''])
        }
        stage("Setup Infrastructure Monitoring")
        {
            sh 'chmod -R 777 dockprom;cd dockprom;/bin/docker-compose down;/usr/bin/docker-compose up -d'
            sh 'echo  "\u2600 ACCESS PROMETHEUS ENVIRONMENT HERE: http://192.168.56.104:7090 "'
            sh 'echo  "\u2600 ACCESS PUSH GATEWAY ENVIRONMENT HERE: http://192.168.56.104:7091 "'
            sh 'echo  "\u2600 ACCESS ALERT MANAGER ENVIRONMENT HERE: http://192.168.56.104:7093 "'
            sh 'echo  "\u2600 ACCESS GRAFANA ENVIRONMENT HERE: http://192.168.56.104:7000 "'
        }
}
