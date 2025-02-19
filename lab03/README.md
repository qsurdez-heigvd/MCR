# MCR_Project

On a les fourmis qui vont spawn dans le hub. 

Les scouts vont errer dans l'univers. Lorsqu'un scout trouve une ressource -> assigne les gatherer à la ressource. 

// TODO: Réfléchir à quel niveau mettre les fonctions à appeler pour avoir un max de fluidité dans les appels
// Cela serait intéressant de mettre dans Ant plutôt que dans les fourmis d'intérêt pour un médiateur -> Cela 
// nous permettrait d'avoir
Si une fourmi quelconque détecte un prédateur -> annonce aux fourmis proches de fuir -> Soldats appelés pour tuer le prédateur. 

// TODO: Combat

Les fourmis qui fuient checkent à intervalles si prédateur encore dans les parages.

La ressource se vide quand un gatherer en prend un bout. (ça doit se voir graphiquement: un rond
avec un compteur qui se décrémente)

Les gatherer ramènent au hub la ressource qu'ils ont récoltés -> incrémente les ressources à disposition du hub ->
Selon certains critères spawn des nouvelles fourmis d'un certain type.

Cycle constant de ces interactions jusqu'à une fin à déterminer ? 


- [ ] Comment prendre en compte les ressources qui ont déjà été trouvées par les scout précédemment ? 
- [x] Bug avec les ressources qui deviennent négatives :((
- [ ] Fonction plus utilisée 
```java
@Override
public void handleHoldingResource(Gatherer gatherer) {
gatherer.setObjective(hub.getPosition());
}
```
- [ ] Envoyer un certain pourcentage de gatherer dans une ressource 
- [x] Comportement étrange des gatherer lorsque la ressource est morte 
- [x] Donner aux soldats un predator pour avoir la position updated de l'objet qui bouge !
- [ ] Envoyer un certain pourcentage de soldier pour un predator 
- [x] Avoir un predator dans soldat pour qu'il puisse l'attaquer -> changer la logique dépendante
- [x] Avoir une ant dans predator pour qu'il puisse l'attaquer -> changer la logique dépendante
- [x] Checker le comportement du programme avec 200 entités puis 500 puis 1000 puis 100000 -> voir quelle est notre limite avec notre logique !
- [x] HandleEntityDeath -> instanceof c'est dégueu mais don't know what to do autrement
- [x] getter + setter pour predator dans soldier, attribut publique ? 
- [x] Si pas un nb de ressource multiple des gatherers -> reset leur objectif 
- [ ] Pas de référence entre les collègues pour les gatherers 
- [x] Enlever la classe Ant et mettre dans Entity 
- [x] Accéder aux médiateurs dans les stratégies pour le calcul du isWithinRange
- [x] Avoir les gatherer retourné au Hub ne plus avoir d'objectifs

# TODO Urgent
- [x] Les ressources qui sont trouvés, on stock dans une liste (= stocker dans une liste resourcesTrouvées) et on assigne un pourcentage de gatherer à la ressource 
- [x] Enlever les réfs des Predator et Ant dans respectivement Soldier et Predator -> Avoir une hashMap pour les entités qui se track <traqueur, traqué> + de PV pour les Pred
- [x] Nettoyer le code (fonction plus utilisée)
- [x] Que les Predators attaquent les fourmis
- [ ] Nettoyer les communications
- [ ] Décider de la fin du jeu -> Quand plus de fourmis
- [ ] Présentation à faire 
- [x] Gérer le remove correctement

# TODO GUI
- [x] Pouvoir choisir le nombre de fourmi dans le title screen
- [ ] Ajouter le sprite où les fourmis portent des 
- [ ] Ajouter une animation quand les scouts trouvent une ressource
- [ ] Resize l'image pour que resizable fonctionne (+ il faut que les fourmis bounce)
- [ ] Prendre en compte la taille de la barre de boutons 
