
## 1. Introduction

**Objectif**
Plateforme web responsive (mobile & desktop) pour gérer des parties de jeu de rôle, avec rôles MJ, joueurs et guests, et un backoffice admin complet.

---

## 2. Acteurs & rôles

| Rôle       | Permissions principales                                                                          |
| ---------- | ------------------------------------------------------------------------------------------------ |
| **MJ**     | Crée et pilote une partie (1 MJ unique par partie). Gère entités, invitations, stats…            |
| **Joueur** | Accède à **ses** parties en cours. Gère ses pré-sets de personnages.                             |
| **Guest**  | Accès limité à **une seule** partie via invitation. Profil minimal (pseudo, mail, mot de passe). |
| **Admin**  | Accès backoffice : CRUD complet sur utilisateurs, parties, entités, stats, exports…              |

*Point important* : séparer fortement les vues MJ / joueurs pour le partage sélectif d’entités.

---

## 3. Cas d’utilisation (User Stories)

### 3.1 Inscription & Authentification

* **Inscription** : Nom, Prénom, Mail, Pseudo, Password, Type (Joueur, MJ, ou les deux).
* **Connexion** : Pseudo + Password.
* **Guest** : Création rapide (Pseudo, Mail, Password). Accès uniquement à la partie d’invitation reçue.

### 3.2 Gestion des parties

* **Statut de partie** : `En cours`, `En pause`, `Terminée`.
* **MJ** :

  * Créer une partie (titre, description, image de couverture).
  * Gérer le statut (démarrer, mettre en pause, terminer).
  * Inviter joueurs/guests via lien envoyé par **email** ou génération de **QR code**.
  * Voir liste des participants.
* **Joueur** :

  * Voir la liste de **ses propres** parties en cours.
  * Rejoindre via lien / QR code.
* **Guest** :

  * Rejoindre **une seule** partie via lien / QR code.
  * Pour une nouvelle partie, recréer un guest ou s’inscrire.

*Solution alternative* : tokens d’invitation à usage unique gérés côté serveur pour plus de sécurité.

### 3.3 Gestion des entités

* **Entité** : titre, description, image (adaptée aux dimensions du style), jusqu’à 6 **stats** dynamiques (nom + valeur entière).
* **MJ** :

  * Créer en direct ou utiliser pré-sets (Items, Lieux, Personnages).
  * Partager chaque entité à tous ou à certains joueurs.
* **Joueur / Guest** :

  * Consulter les entités partagées.
  * Modifier sa fiche personnage avec les points attribués par le MJ.

### 3.4 Écran de partie

* **Vue principale** (responsive) :

  * **Sidebar** : liste des entités (+ filtre selon partage).
  * **Zone centrale** :

    * **Tab « Entités »** : consultation et actions (partage, stats).
    * **Tab « Actions »** : lancer dés, appliquer effets…
* **Pas de chat** intégré.

---

## 4. Modèle de données (schéma simplifié)

**Utilisateur**(id, nom, prénom, mail, pseudo, password_hash, rôle)
**Partie**(id, titre, description, image, statut, mj_id → Utilisateur)
**Invitation**(id, partie_id, token, type = joueur|guest, méthode = email|qr, utilisé)
**Entité**(id, partie_id, titre, description, image, …)
**Stat**(id, entité_id, nom, valeur)
**Export**(id, utilisateur_id, partie_id, type = PDF|JSON, url, date)
**Admin**(id, user_id → Utilisateur)

![image](https://github.com/user-attachments/assets/c8beb658-103f-4182-b905-c787e0a018a0)

---

##5. Architecture technique

**Frontend** : Java Spring MVC avec Thymeleaf et CSS classique (media queries pour responsive).

**Backend** : Spring MVC (Spring Boot), controllers REST pour API, Spring Security avec JWT pour l’authentification et le rafraîchissement de token.

**Base de données** : PostgreSQL.

**Stockage médias** : gestion via système de fichiers ou cloud (ex : AWS S3) avec redimensionnement et contrôles d’accès.

**Génération PDF** : bibliothèque Java (Apache PDFBox ou iText).

*Point important* : maintenir une séparation nette entre couches Controller, Service et Repository pour faciliter la maintenance et les tests.

---

## 6. Maquettes & wireframes

1. **Landing page** / Inscription-Connexion
2. **Dashboard MJ** (créer partie, liste parties, statut)
3. **Dashboard Joueur** (ses parties)
4. **Écran Partie** (mobile & desktop) – onglets Entités / Actions
5. **Backoffice Admin** (listings CRUD)
6. **Modale invitation** (email & QR code)
7. **Modal Export** (PDF / JSON)

---

## 7. Backoffice Administrateur

* CRUD complet sur : Utilisateurs, Parties, Invitations, Entités, Stats, Exports.
* Pages de listing, création, modification pour chaque ressource.
* Filtres & recherche avancée.

