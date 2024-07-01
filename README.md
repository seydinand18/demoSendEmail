# demoSendEmail

`demoSendEmail` est une application Spring Boot conçue pour gérer l'envoi d'e-mails de confirmation pour l'inscription des utilisateurs. Ce projet inclut une API REST pour la gestion des utilisateurs et des services pour l'envoi et la gestion des e-mails.

## Pré-requis

- JDK 17 ou supérieur
- Maven 3.8.1 ou supérieur
- MySQL 8.0 ou supérieur (ou une autre base de données de votre choix)
- Compte SMTP pour l'envoi des e-mails (par exemple, Gmail)

## Installation

1. Clonez le dépôt :
    ```bash
    git clone https://github.com/seydinand18/demoSendEmail.git
    cd demoSendEmail
    ```

2. Configurez la base de données :
    - Créez une base de données `send_email_demo`.
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

## Utilisation

### API Endpoints

- **Créer un utilisateur :** `POST /api/v1/users`
- **Confirmer l'e-mail :** `GET /api/users/verify/{token}

## Auteurs

- **Seydina** - *Développeur principal* - https://github.com/seydinand18
