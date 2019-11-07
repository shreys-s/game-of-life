def call()
{
	stage('Code Build')
	{
		sh 'mvn clean install'
	}
}
