# Chatop - Guide d'installation et d'exécution

## Prérequis
Avant d'installer et de lancer le projet, assurez-vous d'avoir les éléments suivants :

- **Java 17** (JDK 17+ recommandé)
- **Maven** (version 3+)
- **MySQL** (serveur de base de données)
- **Git** (optionnel mais recommandé)

## Installation

### 1. Cloner le projet
Si vous utilisez **Git**, exécutez la commande suivante :
```sh
  git clone https://github.com/Shishifoxy/Chatop
  cd chatop
```
Si vous ne souhaitez pas utiliser Git, téléchargez le projet en `.zip`, puis extrayez-le et accédez au dossier extrait.

### 2. Configuration de la base de données
Avant de démarrer l'application, créez une base de données MySQL nommée `chatopocr` :

```sql
CREATE DATABASE chatopocr;
```

Ajoutez ensuite un utilisateur et attribuez-lui les permissions nécessaires :
```sql
CREATE USER 'chatopuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON chatopocr.* TO 'chatopuser'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configuration des variables d'environnement

Créez un fichier `.env` à la racine du projet avec les informations suivantes :
```env
MYSQL_USER=chatopuser
MYSQL_PASSWORD=password
```

### 4. Configuration de l'application
Dans le fichier `src/main/resources/application.properties`, les paramètres suivants sont déjà configurés :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chatopocr
spring.datasource.username=${MYSQL_USER}
spring.datasource.password=${MYSQL_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
security.ignored=/**
security.basic.enable: false
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
spring.mvc.static-path-pattern=/images/**
spring.web.resources.static-locations=file:./uploads/images/
logging.level.org.springframework=DEBUG
logging.level.root=INFO
server.port=3001
```

Si nécessaire, modifiez les valeurs de `MYSQL_USER` et `MYSQL_PASSWORD` pour correspondre à votre environnement.

## Compilation et exécution

### 1. Compiler le projet

Dans le dossier du projet, exécutez :
```sh
mvn clean install
```

### 2. Lancer l'application

Exécutez la commande suivante :
```sh
mvn spring-boot:run
```
L'application devrait maintenant être disponible sur **http://localhost:3001**.

## Documentation de l'API
Une documentation interactive de l'API est disponible via Swagger à l'adresse :

📌 **Swagger UI** : [http://localhost:3001/swagger-ui.html](http://localhost:3001/swagger-ui.html)

📌 **OpenAPI JSON** : [http://localhost:3001/v3/api-docs](http://localhost:3001/v3/api-docs)

## Structure du projet
```
chatop/
├── src/
│   ├── main/
│   │   ├── java/com/openclassroom/chatop/
│   │   │   ├── controllers/
│   │   │   ├── services/
│   │   │   ├── repositories/
│   │   │   ├── models/
│   │   │   ├── dtos/
│   │   │   ├── security/
│   │   │   ├── ChatopApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/
│   │   │   ├── templates/
├── pom.xml
├── README.md
```

## Dépannage

### Erreur "Cannot connect to database"
- Vérifiez que le serveur MySQL est bien démarré.
- Assurez-vous que l'utilisateur `chatopuser` et la base de données `chatopocr` existent.
- Vérifiez votre fichier `.env` et assurez-vous que les informations de connexion sont correctes.

### Port déjà utilisé
Si le port `3001` est déjà utilisé, modifiez `server.port` dans `application.properties` :
```properties
server.port=8080
```

Puis redémarrez l'application.

---

🎯 **Votre application Spring Boot "Chatop" est maintenant installée et prête à être utilisée !** 🎯

