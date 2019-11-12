def ref() {
    return sh(script: 'mvn clean install ', returnStdout: true).trim()
}
