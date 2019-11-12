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
