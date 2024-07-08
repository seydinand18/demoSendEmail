#!/bin/bash

# Reconstruire et redéployer les services avec le fichier d'environnement spécifié
docker-compose --env-file ./.env.dev up -d --build

# Nettoyer les conteneurs, images et volumes inutilisés pour éviter les conflits
docker system prune -f

# Afficher les logs pour vérifier que tout fonctionne correctement
docker-compose logs
