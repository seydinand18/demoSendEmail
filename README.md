# demoSendEmail

`demoSendEmail` est une application Spring Boot conçue pour gérer l'envoi d'e-mails de confirmation pour l'inscription des utilisateurs. Ce projet inclut une API REST pour la gestion des utilisateurs et des services pour l'envoi et la gestion des e-mails.

## Pré-requis

- JDK 17 ou supérieur
- Maven 3.8.1 ou supérieur
- MySQL 8.0 ou supérieur (ou une autre base de données de votre choix)
- Compte SMTP pour l'envoi des e-mails (par exemple, Gmail)
- Docker et Docker Compose

## Installation

1. Clonez le dépôt :
    ```bash
    git clone https://github.com/seydinand18/demoSendEmail.git
    cd demoSendEmail
    ```

2. Configurez la base de données :
    - Créez une base de données `send_email_demo` si vous utilisez MySQL localement.
    - Modifiez le fichier `src/main/resources/application-dev.yml` avec vos paramètres de connexion.

3. Configurez le compte SMTP :
    - Modifiez `src/main/resources/application.yml` avec vos paramètres SMTP pour l'envoi d'e-mails.

4. Compilez le projet :
    ```bash
    mvn clean install
    ```

5. Exécutez l'application :
    ```bash
    mvn spring-boot:run
    ```

## Dockerisation

Pour faciliter le déploiement et l'exécution de l'application, nous utilisons Docker et Docker Compose. Suivez ces étapes pour démarrer l'application à l'aide de Docker.

### Étapes pour Dockeriser

1. **Configurer le fichier `.env.dev` :**
    - Mettez à jour le fichier `.env.dev` avec les informations de votre environnement. Voici un exemple de configuration :
      ```dotenv
      # Database Configuration
      POSTGRES_SQL_HOST=postgres
      POSTGRES_SQL_PORT=5432
      POSTGRES_SQL_DATABASE=<NOM_BASE_DE_DONNEES>
      POSTGRES_SQL_USERNAME=<UTILISATEUR>
      POSTGRES_SQL_PASSWORD=<MOT_DE_PASSE>

      # Server Configuration
      CONTAINER_PORT=<PORT_CONTENEUR>
      PROFILES_ACTIVE=dev
      HOST_PORT=<PORT_HOTE>

      # Email Configuration
      EMAIL_HOST=smtp.gmail.com
      EMAIL_PORT=587
      EMAIL_ID=<VOTRE_EMAIL>
      EMAIL_PASSWORD=<VOTRE_MOT_DE_PASSE>
      VERIFY_EMAIL_HOST=http://<POSTGRES_SQL_HOST>:5432
      UI_APP_URL=localhost:4200
      ```

2. **Exécuter le script `start-dev.sh` :**
    - Utilisez le script fourni pour démarrer l'application avec Docker Compose. Le script `start-dev.sh` inclut les commandes nécessaires pour construire et lancer les conteneurs Docker.
    ```bash
    ./start-dev.sh
    ```

## Utilisation

### API Endpoints

- **Créer un utilisateur :** `POST /api/v1/users`
- **Confirmer l'e-mail :** `GET /api/users/verify/{token}`

## Auteurs

- **Seydina** - *Développeur principal* - https://github.com/seydinand18
