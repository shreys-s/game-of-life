@Library('jenkins-pipeline-library@master') _
 
pipeline
{
    agent { label "sjeapvl02" }
    stages 
	{
        stage('Git Checkout') 
		{
            steps 
			{
            gitCheckout(
                branch: "featurepraveen",
                url: "https://github.com/shreys-s/game-of-life"
            )
            }
        }
    }
}
