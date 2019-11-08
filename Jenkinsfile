node("master")
{
	MAVEN_HOME=E:\\apache-maven-3.6.2
	properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '5', artifactNumToKeepStr: '5', daysToKeepStr: '5', numToKeepStr: '5')), pipelineTriggers([])])
	stage('Code Checkout')
	{
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'bbb48b43-0e32-4a0d-b833-3d611916d027', url: 'https://github.com/shreys-s/game-of-life.git']]])
	}
	stage('Maven Code Build')
	{
		bat "%MAVEN_HOME%\\bin\\mvn -f pom.xml clean test package verify checkstyle:checkstyle pmd:pmd findbugs:findbugs javadoc:javadoc cobertura:cobertura -Pmetrics"
	}
	stage('Unit Testing')
	{
		junit allowEmptyResults: true, testResults: '**\\target\\surefire-reports\\*.xml'
                archive 'target\\*.jar'
	}   
    stage("Selenium Testing")
    {
		sh "%MAVEN_HOME%\\bin\\mvn -f gameoflife-selenium\\pom.xml -Dhostname=localhost  -Dport=8082 -Dcontext=gameoflife -Dmaven.test.failure.ignore=true test"
	    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "gameoflife-selenium\\target\\surefire-reports", reportFiles: "emailable-report.html", reportName: "Selenium Report", reportTitles: ''])
    }
    stage("Performance Testing")
    {
		sh "%MAVEN_HOME%\\bin\\mvn -f gameoflife-jmeter\\pom.xml -Dhostname=localhost -Dport=8082 -Dcontext=gameoflife clean verify -Pperformance"
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: "gameoflife-jmeter\\target\\reports", reportFiles: "index.html", reportName: "Jmeter Report", reportTitles: ''])
    }
}
