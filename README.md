# Outil de Génération de Code Basé sur les Modèles de Conception

## Description
Cet outil de génération de code automatise la création d’applications respectant des modèles de conception tels que **MVC** et **MVVM**. Il est conçu pour simplifier le processus de développement en fournissant un code standardisé et conforme aux bonnes pratiques. L’architecture repose sur des microservices robustes et scalables, intégrant des technologies modernes comme **Spring Cloud Gateway** et **Eureka Server**.

## Fonctionnalités principales
- **Support multi-plateformes** : Génération de code pour des applications web, Android, iOS et desktop.
- **Modèles de conception** : Implémentation des modèles de conception **MVC** et **MVVM**.
- **Architecture basée sur des microservices** : Utilisation de **Spring Boot**, **Spring Cloud Gateway**, **Eureka Server**.
- **Langages supportés** : Java, TypeScript (Angular pour le frontend).
- **Frontend Angular** : Interface utilisateur moderne et intuitive.
- **Backend avec Spring Boot** : Gestion des microservices et intégration facile avec MySQL.
- **Base de données MySQL** : Support complet pour la gestion des données relationnelles.
- **Automatisation avancée** : Réduction des erreurs humaines grâce à la génération automatique de code standardisé.

## Prérequis
- **Java JDK 21+**
- **Maven 3.8+**
- **Node.js 18+**
- **Docker** (pour exécuter les microservices dans des conteneurs)
- **MySQL 8+**

### Environnements recommandés
- **IDE pour le backend** : IntelliJ IDEA, Eclipse.
- **IDE pour le frontend** : Visual Studio Code.

## Installation
1. Clonez ce dépôt :
   ```bash
   git clone https://github.com/your-repo/Projet-Generation-Code.git
   ```

2. Installez les dépendances backend :
   ```bash
   cd backend
   mvn install
   ```

3. Installez les dépendances frontend :
   ```bash
   cd frontend
   npm install
   ```

4. Configurez votre base de données MySQL dans le fichier `application.properties` de chaque microservice backend.

5. Lancez tous les services via Docker Compose :
   ```bash
   docker-compose up --build
   ```

### Exécution locale sans Docker
1. Configurez MySQL et créez les bases de données nécessaires pour chaque microservice.
2. Lancez chaque microservice individuellement :
   - Accédez au répertoire de chaque service (`user`, `project`, etc.).
   - Exécutez la commande suivante :
     ```bash
     mvn spring-boot:run
     ```
3. Démarrez le frontend Angular :
   ```bash
   cd frontend
   ng serve
   ```
4. Accédez à l'application via :
   ```
   http://localhost:4200
   ```

## Contenu du fichier `docker-compose.yml`
```yaml
version: '3.8'

services:

  eureka:
    build: ./cloud-eureka
    container_name: eureka-server
    ports:
      - "8761:8761"
    networks:
      - app-network
    environment:
      - SPRING_PROFILES_ACTIVE=docker

  user-service:
    build:
      context: ./user
    ports:
      - "8081:8080"
    networks:
      - app-network
    environment:
      - SPRING_PROFILES_ACTIVE=docker

  project-service:
    build:
      context: ./project
    ports:
      - "8084:8080"
    networks:
      - app-network
    environment:
      - SPRING_PROFILES_ACTIVE=docker

  template-service:
    build:
      context: ./template
    ports:
      - "8082:8080"
    networks:
      - app-network
    environment:
      - SPRING_PROFILES_ACTIVE=docker

  gen-service:
    build:
      context: ./gen
    ports:
      - "8083:8080"
    networks:
      - app-network
    environment:
      - SPRING_PROFILES_ACTIVE=docker

  gateway-service:
    build:
      context: ./gateway
    ports:
      - "8888:8080"
    networks:
      - app-network
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - EUREKA_SERVER_URL=http://eureka:8761/eureka/

  frontend:
    build:
      context: ./frontend
    ports:
      - "4200:80"
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
```

## Utilisation
1. Accédez à l’application via votre navigateur :
   ```
   http://localhost:4200
   ```

2. Créez un compte utilisateur ou connectez-vous.

3. Sélectionnez :
   - Le type de projet (Web, Mobile, Desktop).
   - Le modèle de conception souhaité (MVC, MVVM).
   - Les fonctionnalités supplémentaires (authentification, base de données, etc.).

4. Cliquez sur **Générer** pour obtenir un projet prêt à l’emploi.

## Architecture du projet
### Microservices inclus
- **Eureka Server** : Service de découverte pour la gestion des microservices.
- **Gateway Service** : Point d’entrée unique pour gérer les requêtes des utilisateurs.
- **User Service** : Gestion des utilisateurs (inscription, connexion, etc.).
- **Project Service** : Gestion des projets générés.
- **Template Service** : Gestion des modèles de conception et des templates de code.
- **Gen Service** : Génération automatique de code à partir des configurations utilisateur.

### Frontend
- Basé sur **Angular**.
- Utilise **Bootstrap** pour le design réactif.
- Interface utilisateur intuitive pour interagir avec les services backend.


## Vidéo
Pour une démonstration complète du fonctionnement de l’application, regardez cette vidéo :

[![Démonstration Vidéo](https://img.youtube.com/vi/your-video-id/0.jpg)](https://www.youtube.com/watch?v=your-video-id)

## Support
Pour toute question ou problème, veuillez nous contacter :
- **Mohamed Amine El Haid** : [mohamedamine8el@gmail.com](mailto:mohamedamine8el@gmail.com)
- **Fatimazohra Amejoud** : [fatimaanflous1@gmail.com](mailto:fatimaanflous1@gmail.com)
- **Safaa Batrahi** : [safaabatrahi7@gmail.com](mailto:safaabatrahi7@gmail.com)
- **Wiam El Fard** : [elfardwiam@gmail.com](mailto:elfardwiam@gmail.com)

## Licence
Ce projet est sous licence **MIT**.
