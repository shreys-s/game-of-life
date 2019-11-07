@Library('jenkins-pipeline-library@featurepraveen') _
 
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
                branch: "master",
                url: "https://github.com/shreys-s/game-of-life"
            )
            }
        }
    }
}