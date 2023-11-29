# Epitech Simulator - Version Solo et Multijoueur

## Vue d'ensemble du Projet
Bienvenue dans notre version remixée de Pacman, inspirée par l'univers Epitech. Dans ce jeu, les joueurs naviguent dans un labyrinthe, non pas poursuivis par des fantômes, mais par nos pédagogues, avec une petite touche d'originalité : les gros points bonus sont remplacés par des AERs (Assistantes d'Enseignement et de Recherche) d'Epitech, chacun offrant un bonus de 100 points. De plus, attraper Lois, une AER situé au milieu du spawn des pédagogues et difficile à capturer, donne une vie supplémentaire. Ce projet se décline en deux versions : `Epitech_Simulator` pour la version solo et `E_Smultijoueurs` pour la version multijoueur utilisant le protocole UDP.

## Installation et Lancement du Jeu
### Version Solo
1. Assurez-vous d'avoir Java installé sur votre machine.
2. Naviguez jusqu'au dossier `Epitech_Simulator/Mypacman`.
3. Compilez les fichiers Java en utilisant `javac *.java`.
4. Lancez le jeu avec `java Game`.

### Version Multijoueur
1. Suivez les étapes 1 et 2 comme pour la version solo, mais dans le dossier `E_Smultijoueurs/Mypacman`.
2. Compilez et lancez le UDPserveur dans le dossier `Multi`.
3. Lancez deux UDP clients, une fois deux clients connecté au server, la partie pourra commencer.

## Gameplay et Fonctionnalités
- **Pacman**: Vous contrôlez Pacman pour collecter des points tout en évitant les pédagogues. Utilisez les touches fléchées (haut, bas, gauche, droite) pour déplacer Pacman dans le labyrinthe.
- **Pédagogues**: Ils agissent comme des fantômes, poursuivant Pacman.
- **AERs**: Représentés par des points bonus sur la carte.
- **Lois**: Capturez ce personnage au milieu du spawn des pédagogues pour une vie supplémentaire.

## Détails du Multijoueur
La version multijoueur permet à plusieurs joueurs de jouer ensemble via un réseau local. Chaque joueur doit se connecter au serveur pour rejoindre la partie. Veuillez noter que cette fonctionnalité est encore en développement et peut ne pas fonctionner parfaitement. Nous travaillons activement à améliorer cette expérience de jeu.

## Contributeurs
- Supiot Balthazar
- Hedia Imen
- Elkurdi Chade



