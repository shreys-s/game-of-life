def call(Map stageParams) 
{ 
    checkout([$class: 'GitSCM', branches: [[name: stageParams.branch]],doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs:[[credentialsId: '9a71c103-12b0-41c9-8375-90af30de786b', url: stageParams.url]]])
}