class MuleCICD
{
	if (env.BRANCH_NAME == 'develop' || env.BRANCH_NAME.startsWith('feature'))
	{
		bat "E:\\apache-maven-3.6.2\\bin\\mvn -f pom.xml clean test package verify checkstyle:checkstyle pmd:pmd findbugs:findbugs javadoc:javadoc cobertura:cobertura -Pmetrics"
 	}
}
