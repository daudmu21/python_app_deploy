node {
    properties([parameters([string(defaultValue: 'IP', description: 'where to build e.g IP', name: 'ENV'"", trim: true)])])
    stage("Clone repo"){
      git "git@github.com:daudmu21/flask-examples.git"
    }
    stage("Install Requirements"){
        sh "ssh ec2-user@${ENV} sudo yum install virtualenv -y"
        sh "ssh ec2-user@${ENV} sudo yum install pip -y"
        sh "scp -r *  ec2-user@${ENV}:/tmp"
        sh "ssh ec2-user@${ENV} virtualenv /tmp/venv"
        sh "ssh ec2-user@${ENV} ./tmp/ven/activate"
        sh "ssh ec2-user@${ENV} sudo pip install -r /tmp/requirements.txt"
     }
    stage("start python app"){
        sh "ssh ec2-user@${ENV} python /tmp/01-hello-world/hello.py"
    }
}   