def call(body) {
script
{
	if (env.BRANCH_NAME == 'featurepraveen' || env.BRANCH_NAME.startsWith('feature') || env.BRANCH_NAME.startsWith('release'))
	{
		echo "Running build from branch ${env.BRANCH_NAME}"
		sh 'mvn clean install'
		//sh 'mvn clean install -Dv=${BUILD_NUMBER} -Du=${ANYPOINT_CREDENTIALS_USR} -Dp=${ANYPOINT_CREDENTIALS_PSW}'
	}
	else
	{
		echo 'The repository/branch name does not meet Clorox branch naming standard'
	}
}
	    return this
}
