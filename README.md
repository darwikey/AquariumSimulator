AquariumSimulator
=============================================
Projet de réseaux Enseirb-Matméca 2015


Controleur
=============================================
= Pour compiler les sources =
* Se placer dans Controleur, lancer la commande "cmake ." puis "make"

= Dépendance =
* bibliothèque graphviz/cgraph
* pThread

= Pour exécuter =
* Se placer dans Serveur, lancer la commande "./Controleur"


= Liste des commandes disponibles =
* load 
* show 
* add
* del
* remove
* save
* exit


Client
=============================================
= Pour compiler les sources =
* Se placer dans Affichage, lancer la commande "javac *.java"


= Dépendance =
* Awt / swing


= Pour exécuter = 
* Se placer dans Affichage, lancer la commande "java Client" ou "java Client -v" (degré de verbosité)


= Liste des poissons disponibles =
* Base.png
* Blob.png
* Clown.png
* Empereur.png
* PoissonRouge.png
* Stylay.png


= Remarques générales =
Pour envoyer plusieurs commandes en une seule fois, assurez-vous d'avoir copié-collé un retour à la ligne (ligne vide) à la fin. Il ne sera alors pas nécessaire d'appuyer sur la touche entrée.

Si vous essayez d'ajouter un poisson dont le nom ne correspond pas à une image, l'image Base.png sera utilisée pour l'affichage.


= Liste des commandes reconnues =
* addFish
* startFish
* getFishesContinuously
* delFish
* status
* log out
* ping (fait automatiquement à intervalle régulier, ne pas écrire ping dans le terminal)


= Exemples de commandes =
addFish Clown at 61x52, 4x3, RandomWayPoint
startFish Clown
getFishesContinuously

addFish Empereur at 80x20, 10x10, VerticalWayPoint
addFish Stylay at 80x20, 10x10, VerticalWayPoint
addFish Blob at 80x20, 10x10, HorizontalWayPoint
startFish Empereur
startFish Stylay
startFish Blob
getFishesContinuously

delFish Clown

status

log out


