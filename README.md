# AutoP2P · Annonces de véhicules entre particuliers

Application de bureau en **Java** avec une interface graphique **Swing**, pour publier et consulter
des annonces de voitures et de motos entre particuliers.

Projet d'école réalisé pour mettre en pratique la programmation orientée objet en Java :
héritage, classes abstraites, interfaces, collections, généricité, sérialisation et interfaces graphiques.

## Fonctionnalités

- **Comptes** : inscription, connexion et déconnexion, avec deux rôles (utilisateur et administrateur)
- **Annonces** : publication d'une voiture ou d'une moto (marque, modèle, année, kilométrage, prix)
- **Consultation** de toutes les annonces, avec leur statut (disponible ou vendu)
- **Mes annonces** : modification du prix de ses propres annonces
- **Messagerie** : contacter le vendeur d'une annonce
- **Administration** : suppression d'annonces et d'utilisateurs (un administrateur ne peut pas être supprimé)
- **Sauvegarde** : les annonces sont enregistrées dans un fichier à la fermeture et rechargées au démarrage

## Notions de POO mises en œuvre

| Notion | Où |
|---|---|
| Classe abstraite et héritage | `Vehicule` → `Voiture`, `Moto` ; `Utilisateur` → `UtilisateurSimple`, `Administrateur` |
| Interfaces | `Affichable`, `Contactable` |
| Polymorphisme | `getTypeVehicule()`, `afficher()` |
| Encapsulation | attributs privés, accesseurs, vérification du prix dans `setPrix()` |
| Collections | `ArrayList` (annonces, utilisateurs), `HashMap` (messages), `Stack` (historique des actions) |
| Généricité | `rechercherParPrixMax(...)` |
| Sérialisation | sauvegarde et chargement des annonces (`ObjectOutputStream` / `ObjectInputStream`) |
| Interface graphique | Swing : fenêtres, onglets, formulaires, lancement dans l'EDT avec `SwingUtilities.invokeLater` |

## Lancer le projet

Prérequis : un JDK 11 ou plus récent.

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

Sous Windows (PowerShell) :

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out Main
```

Ou ouvrir le dossier dans IntelliJ IDEA ou Eclipse et lancer `Main.java`.

Comptes de démonstration : `admin@auto.fr`, `marie@mail.fr`, `lucas@mail.fr` (mot de passe `1234`).

## Organisation du code

```
src/
├── Main.java                  point d'entrée
├── gui/                       fenêtres Swing (connexion, inscription, fenêtre principale)
├── interfaces/                Affichable, Contactable
├── utilisateurs/              Utilisateur, UtilisateurSimple, Administrateur, GestionUtilisateurs
└── vehicules/                 Vehicule, Voiture, Moto, GestionVehicules
```

## Améliorations possibles

- Hacher les mots de passe au lieu de les stocker en clair
- Remplacer la sérialisation par une base de données (SQLite, JDBC)
- Ajouter des tests unitaires avec JUnit
- Filtres de recherche dans l'interface (prix, type, année)

---

Projet d'école réalisé par **Nicolas Zogo Obame**, étudiant en Bachelor Ingénierie du Web à l'ESGI Grenoble.
