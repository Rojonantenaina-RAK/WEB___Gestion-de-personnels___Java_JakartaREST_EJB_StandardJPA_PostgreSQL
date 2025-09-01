# Comment lancer ce Backend ?

- Prérequis: installe d'abord **Java JDK** (NB: Installer Java avant WildFly)
```bash
# java -version          #(pour verifier)
sudo apt update
sudo apt install openjdk-17-jdk -y
```

- Prérequis: installe ensuite **WildFly** (Serveur d'application Java EE, Libre, écrit en Java)
```bash
# Telecharge WildFly 30
wget https://github.com/wildfly/wildfly/releases/download/30.0.0.Final/wildfly-30.0.0.Final.tar.gz

# Extraire et installer
tar -xvzf wildfly-30.0.0.Final.tar.gz
sudo mv wildfly-30.0.0.Final /opt/wildfly
```

- On pourra maintenant cloner le code source du backend
```bash
git clone https://github.com/Rojonantenaina-RAK/WEB___Gestion-de-personnels___Java_JakartaREST_EJB_StandardJPA_PostgreSQL

cd WEB___Gestion-de-personnels___Java_JakartaREST_EJB_StandardJPA_PostgreSQL
```
- Installer **Maven** (package manager pour Java, comme npm pour JavaScript et pip pour Python)
```bash
sudo apt update
sudo apt install maven -y
# mvn -version          #(pour verifier)
```
- Builder le backend avec **Maven**
```bash
mvn clean package
# Resultat: ./target/gestion-personnel-0.0.1-SNAPSHOT.war
```
- Mettre le build du backend dans /opt/wildfly/standalone/deployments/
```bash
cp ./target/gestion-personnel-0.0.1-SNAPSHOT.war /opt/wildfly/standalone/deployments/gestion-personnel.war
```
- On peut maintenant lancer le backend en lançant WildFly
```bash
/opt/wildfly/bin/standalone.sh
```