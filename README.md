# Backend API - WildLens (Spring Boot)

Ce projet représente le backend de l’application **WildLens**, une plateforme de gestion de posts (ou contenus), développée avec **Spring Boot** et conteneurisée avec **Docker**.

---

## Objectif

Ce backend fournit une API RESTful pour gérer les opérations (CRUD) sur les entités de l’application (ex: posts, utilisateurs).

---

## 🐳 Lancer le projet avec Docker

### Prérequis

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installé
- [Git](https://git-scm.com/) installé
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou un autre éditeur de code
- Java 17 ou supérieur (si vous souhaitez lancer l’API sans Docker)

### Cloner le projet

```bash
git clone https://github.com/OUKOUKES-Nour-El-Houda/mspr-wildlens.git
cd wildlensApi
```
### Modèle IA

Le modèle IA n’est **pas inclus** dans le dépôt Git (pour des raisons de taille et de bonnes pratiques).

## Télécharger le modèle
Téléchargez-le ici : https://drive.google.com/file/d/1UQsKBF8qnqqCQIWLSsKbQrrkCQzA0eDh/view?usp=drive_link

Puis placez-le dans le dossier : `/models/best.pt`


### Démarrer l'application avec Docker
Ouvrir un terminal à la racine du projet puis exécuter :

```bash
docker compose up --build
```
### 🌐 Accès à l’API
L'API sera disponible à l'adresse :

http://localhost:8080/api/v1

### Pour Arrêter l'application
```bash
docker compose down
```

