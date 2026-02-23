pipeline {
    agent any
    
    tools {
        maven 'Maven3.9.12'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Obteniendo código desde GitHub...'
                checkout scm
            }
        }
        
        stage('Compile') {
            steps {
                script {
                    echo '========================================'
                    echo 'INICIANDO COMPILACIÓN AUTOMÁTICA'
                    echo '========================================'
                    
                    sh 'java -version'
                    sh 'mvn -version'
                    
                    sh 'mvn clean compile -B -ntp'
                }
            }
            post {
                success {
                    echo 'COMPILACIÓN EXITOSA'
                }
                failure {
                    echo 'ERROR DE COMPILACIÓN'
                }
            }
        }
        
        stage('Test') {
            steps {
                script {
                    echo '========================================'
                    echo 'EJECUTANDO PRUEBAS AUTOMÁTICAS'
                    echo '========================================'
                    
                    // Verificar si existen pruebas
                    def hayPruebas = sh(
                        script: 'find src/test -name "*.java" | wc -l',
                        returnStdout: true
                    ).trim()
                    
                    if (hayPruebas.toInteger() > 0) {
                        echo "Se encontraron ${hayPruebas} archivos de prueba"
                        
                        // Ejecutar pruebas y capturar resultado
                        def resultado = sh(
                            script: 'mvn test -B -ntp',
                            returnStatus: true
                        )
                        
                        if (resultado == 0) {
                            echo 'PRUEBAS EXITOSAS'
                        } else {
                            echo 'ALGUNAS PRUEBAS FALLARON'
                            // Marcar como inestable pero continuar
                            currentBuild.result = 'UNSTABLE'
                        }
                    } else {
                        echo 'NO SE ENCONTRARON PRUEBAS'
                        echo 'Considera agregar pruebas unitarias al proyecto'
                    }
                }
            }
    post {
        always {
            // Publicar reportes si existen
            junit allowEmptyResults: true, 
                  testResults: 'target/surefire-reports/*.xml'
            
            // Mostrar resumen
            script {
                if (fileExists('target/surefire-reports')) {
                    echo '📊 Reportes de pruebas publicados'
                } else {
                    echo '📭 No hay reportes de pruebas para publicar'
                }
            }
        }
    }
}
        
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests -B -ntp'
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline completado con éxito'
        }
        failure {
            echo 'Pipeline falló'
        }
    }
}