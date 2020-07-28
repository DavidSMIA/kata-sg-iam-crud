# KATA SGIAM CRUD

Cette application permet de créer, modifier, récupérer et supprimer des employés et leur rôles.
Un employé peut avoir n rôles
Un rôle peut être attribué à n employés

**Done :**
 - Utiliser Springboot
 - Utiliser lombok
 - Utiliser Rest-Assured
 - utiliser un repository de type pagingandsortingrepository
 - utiliser openapi 3.0
 - BDD Postgresql
 - id UUID-v4
 - 2 champs DATE pour conserver les dates de modification et de création
 - Acessible uniquement en HTTPS et la redirection de http vers https
 - Gradle
 - Actuator activé
 - docker-compose de dev avec uniquement la bdd
 
 
**TODO :** 
 - le framework log4j2 qui loggue dans un elastic search
 - docker-compose complet
 - securisation de l'api
 - CRUD sur les rôles
 - Ne pas utiliser les mécanismes standards d’injection de dépendances offertes par SpringBoot
 ---
 
 - Swagger : https://localhost:8443/swagger-ui.html
 - PGAdmin : http://localhost:8081
 
 ----
 
 **Points d'attention :**

- Concernant la contrainte sur les mécanismes d'injection standard de Springboot, j'avais en tête de faire un soit un pur singleton
soit d'utiliser ApplicationContextAware et ApplicationContext pour récupérer des beans manuellements

- J'ai fait le choix de générer mes UUID-v4 en java afin d'avoir un id lors de la création de mon objet et pas uniquement après l'insertion en BDD

- J'ai utilisé testcontainer pour simuler une bdd postgre (au lieu de h2) afin d'être iso à la cible lors des tests d'intégration

- J'ai longtemps hésité sur la classe de test EmployeeServiceTest qui test le repository et le service en même temps. J'aurais souhaité diviser cette classe de test en 2 classes EmployeeServiceTest et EmployeeRepositoryTest

- Pour la gestion des logs sur un ELS, j'aurais utilisé Logstash

- J'ai opté pour essayer de toucher un maximum de sujets différents (CRUD, ManytoMany avec colonnes supplémentaires sur la table d'association, gestion exception, tests, ect..) parfois non complet en terme de tests ou de fonctionnalités

- J'ai eu des difficultés principalement sur la gestion de la relation ManyToMany et les tests.

- Les rôles d'un employé sont créés et mis à jour lors de la création et modification de l'utilisateur.
  J'aurais souhaité à terme essayer de faire plutot cela via un CRUD sur les roles d'employé via le endpoint /api/v1/employees/{idemployee}/roles

- Il manque pas mal de tests

- Pour openapi, j'ai hésité entre un fichier et les annotations. J'ai opté pour les annotations pour gagner du temps (avec juste un exemple d'annotation possible)

- J'ai hésité à créer des branches pour chacun des chantiers

- J'ai hésité à utiliser un type String/text ou UUID pour stocker les id
---
```
Le livrable n°1:

Créer une REST API qui effectue un CRUD sur trois tables avec une relation many-to-many  en utilisant :

- le framework SpringBoot
- le framework lombok
- le framework Rest-Assured pour les tests
- un repository de type : pagingandsortingrepository
- openapi version 3.0
- le framework log4j2 qui loggue dans un elastic search
 
En n’utilisant aucun: Des mécanismes standards d’injection de dépendances offertes par SpringBoot.
 

Les contraintes :

- La base de données doit s’appuyer sur « POSTGRESQL »
- l’ID auto généré est un UUID-v4
- Chaque table contient deux champs DATE au format « UTC » : CREATIONDATE && MODIFICATIONDATE
- L’API est accessible uniquement en HTTPS et la redirection de http vers https en utilisant les fonctionnalités de SpringBoot.
- L’outil de build « Gradle » en lieu et place de Maven
- Actuator est activé
 

Livrable n°2 :

- Le docker compose associé
- Le repository sur « github.com » avec tous les commits sur les deux livrables.
- Inutile de dire qu’un seul commit est éliminatoire
 

Livrable n°3 : (complétement optionnel, juste pour le fun)

L’API est sécurisée par guthub.com et SpringSecurity
```