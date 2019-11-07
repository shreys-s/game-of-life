def call()
{
stage('Code Build')
{
	sh 'mvn clean install'
		//sh 'mvn clean install -Dv=${BUILD_NUMBER} -Du=${ANYPOINT_CREDENTIALS_USR} -Dp=${ANYPOINT_CREDENTIALS_PSW}'
}
}
