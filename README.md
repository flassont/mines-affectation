Installation du projet sur machine de test

Démarrage de JBoss / Wildfly
===============================
Installation
------------
- Télécharger la distribution JBoss Wildfly à l'adresse http://wildfly.org/downloads/
- Décompresser la distribution dans un répertoire

Configuration
-------------
Ajouter la variable d'environnement JBOSS_HOME avec pour valeur le répertoire de décompression.

### Configuration du serveur
La configuration du serveur en mode *standalone* est modifiable dans *JBOSS_HOME/standalone/configuration* et particulièrement le fichier *standalone.xml*

Démarrage
---------
- Démarrer le fichier *JBOSS_HOME/bin/standalone.bat* (Windows) ou *JBOSS_HOME/bin/standalone.sh* (Mac/UNIX)
- Accéder à http://localhost:8080 pour vérifier que le serveur fonctionne

Bonus
-----
### Console d'administration
- Utiliser le script *JBOSS_HOME/bin/add-user.bat* (Windows) ou *JBOSS_HOME/bin/add-user.sh* (Mac/UNIX) pour ajouter un *Management User* pour créer un premier utilisateur
- Accéder à http://localhost:9990 avec les identifiants de l'utilisateur créé

#### Console non accessible
Certains services bloquent le port 9990.
Il faut alors soit arrêter le service responsable, soit changer le port dans la configuration du serveur.

Démarrage de l'application
==========================
Récupérer le projet en local.
Avec Maven (console ou IDE), démarrez la tâche *clean package wildfly:deploy*.

Arrêter l'application
---------------------
Lancer la tâche Maven *wildfly:undeploy*