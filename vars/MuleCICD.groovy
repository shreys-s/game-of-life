import gitCheckout
def call()
{
  node("master") 
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
    if (env.BRANCH_NAME == 'develop' || env.BRANCH_NAME.startsWith('feature'))
    {
        stage('Code Build')
        {
					bat "E:\\apache-maven-3.6.2\\bin\\mvn -f pom.xml clean test package verify checkstyle:checkstyle pmd:pmd findbugs:findbugs javadoc:javadoc cobertura:cobertura -Pmetrics"
        }
    }
  }
}
