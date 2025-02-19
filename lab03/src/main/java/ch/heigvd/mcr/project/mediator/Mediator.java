package ch.heigvd.mcr.project.mediator;

import ch.heigvd.mcr.project.Simulation;
import ch.heigvd.mcr.project.gui.*;
import ch.heigvd.mcr.project.mediator.strategies.*;
import ch.heigvd.mcr.project.models.Gatherer;
import ch.heigvd.mcr.project.models.Resource;
import ch.heigvd.mcr.project.models.Scout;
import ch.heigvd.mcr.project.models.Soldier;
import ch.heigvd.mcr.project.models.Predator;
import ch.heigvd.mcr.project.models.Colony;
import ch.heigvd.mcr.project.models.AbstractColleague;
import ch.heigvd.mcr.project.utils.Vector2;
import ch.heigvd.mcr.project.models.Ant;
import ch.heigvd.mcr.project.models.Entity;

import javax.swing.Timer;
import java.util.*;

/**
 * The Mediator class is an abstract class that represents the mediator in the mediator design pattern.
 * It contains methods to handle updates from different types of entities (Gatherer, Scout, Soldier, Predator),
 * methods to check for nearby resources and predators, methods to handle when a resource is found or depleted,
 * methods to handle when a predator is found or dies, methods to handle when an ant dies or reaches an objective,
 * and methods to get the next turn of the simulation, add a new ant, check for nearby preys, display the entities,
 * check collision, spawn resources and predators, and finish the simulation.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public abstract class Mediator {
    protected HashMap<Entity, Entity> targets = new HashMap<>(); // Map of entities and their targets
    protected HashMap<Entity, AbstractColleague> objectives = new HashMap<>(); // Map of entities and their objectives
    protected HashMap<Entity, MovementStrategy> strategies = new HashMap<>(); // Map of entities and their strategies
    protected LinkedList<Gatherer> gatherers = new LinkedList<>(); // List of gatherer ants
    protected LinkedList<Soldier> soldiers = new LinkedList<>(); // List of soldier ants
    protected LinkedList<Scout> scouts = new LinkedList<>(); // List of scout ants
    protected LinkedList<Resource> resources = new LinkedList<>(); // List of resources
    protected LinkedList<Predator> predators = new LinkedList<>(); // List of predators
    protected LinkedList<Resource> resourcesFound = new LinkedList<>(); // List of resources found
    protected LinkedList<Predator> predatorsFound = new LinkedList<>(); // List of predators found
    protected Colony hub; // The colony
    protected Random random = new Random(); // Random object for generating random numbers
    protected int stepFromLastResourceSpawn = 0; // Steps from the last resource spawn
    protected int stepFromLastPredatorSpawn = 0; // Steps from the last predator spawn
    private final Timer simulationTime = new Timer(1000, e -> { // Timer for simulating the simulation time
        stepFromLastResourceSpawn++;
        stepFromLastPredatorSpawn++;
    });
    protected LinkedList<AbstractColleague> toRemove = new LinkedList<>(); // List of entities to be removed

    /**
     * Copy constructor of the mediator
     * @param mediator the mediator to copy
     */
    public Mediator(Mediator mediator) {
        this.hub = mediator.hub;
        this.scouts = mediator.scouts;
        this.predators = mediator.predators;
        this.gatherers = mediator.gatherers;
        this.soldiers = mediator.soldiers;
        this.resources = mediator.resources;
        this.targets = mediator.targets;
        this.objectives = mediator.objectives;
        this.resourcesFound = mediator.resourcesFound;
        this.toRemove = mediator.toRemove;
        this.random = mediator.random;
        this.predatorsFound = mediator.predatorsFound;
        this.stepFromLastPredatorSpawn = mediator.stepFromLastPredatorSpawn;
        this.stepFromLastResourceSpawn = mediator.stepFromLastResourceSpawn;
        for (var e : soldiers) {
            e.setMediator(this);
            objectives.put(e, hub);
            strategies.put(e, new ObjectiveResourceStrategy(e));
        }
        for (var e : gatherers) {
            e.setMediator(this);
            objectives.put(e, hub);
            strategies.put(e, new ObjectiveHomeStrategy(e));
        }
        for(var s: scouts){
            s.setMediator(this);
        }
        for (var e : resources) {
            e.setMediator(this);
        }
        for (var e : predators) {
            e.setMediator(this);
        }
        hub.setMediator(this);
        simulationTime.start();
    }

    /**
     * Constructor of the mediator
     * @param nbSoldiers the number of soldiers
     * @param nbGatherers the number of gatherers
     * @param nbScouts the number of scouts
     */
    public Mediator(int nbSoldiers, int nbGatherers, int nbScouts) {
        this.hub = new Colony(this, new Vector2(400, 300));
        for (int i = 0; i < nbSoldiers; i++) {
            soldiers.add(new Soldier(this, hub.getPosition()));
        }
        for (int i = 0; i < nbGatherers; i++) {
            gatherers.add(new Gatherer(this, hub.getPosition()));
        }
        for (int i = 0; i < nbScouts; i++) {
            scouts.add(new Scout(this, hub.getPosition()));
        }

        simulationTime.start();
    }

    /**
     * Method to handle the update of a gatherer
     * @param gatherer the gatherer to handle the update of
     */
    public abstract void handleUpdateGatherer(Gatherer gatherer);

    /**
     * Method to get the radius of a resource
     * @return the radius of a resource
     */
    public abstract int getRadiusResource();

    /**
     * Method to get the radius to a predator
     * @return the radius to a predator
     */
    public abstract int getRadiusToPredator();

    /**
     * Method to get the radius to flee
     * @return the radius to flee
     */
    public abstract int getRadiusToFlee();

    /**
     * Method to get the threshold to create an ant
     * @return the threshold to create an ant
     */
    public abstract int getThresholdToCreateAnt();

    /**
     * Method to get the radius of a predator to a prey
     * @return the radius of a predator to a prey
     */
    public abstract int getRadiusPredatorToPrey();

    /**
     * Method to get the step to spawn a predator
     * @return the step to spawn a predator
     */
    public abstract int stepToSpawnPredator();

    /**
     * Method to handle the update of a soldier
     * @param soldier the soldier to handle the update of
     */
    public abstract void handleUpdateSoldier(Soldier soldier);

    /**
     * Method to handle the update of a predator.
     * It will have a specific strategy depending on the conditions.
     * If it has a target it will have an attack strategy, otherwise it will have a predator strategy.
     * @param predator the predator to handle the update of
     */
    public void handleUpdatePredator(Predator predator) {
        if (getTarget(predator) != null) {
            strategies.put(predator, new AttackStrategy(predator));
        } else {
            strategies.put(predator, new PredatorStrategy(predator));
        }
        strategies.get(predator).executeStrategy();
    }

    /**
     * Method to handle the update of a scout
     * A specific strategy will be assigned to it depending
     * on some conditions. If it is fleeing it will have a fleeing strategy,
     * if it has an objective it will go home otherwise it will have a scouting strategy
     * @param scout the scout to handle the update of
     */
    public void handleUpdateScout(Scout scout) {
        if (scout.isFleeing()) {
            strategies.put(scout, new FleeingStrategy(scout));
        } else {
            strategies.put(scout, new ScoutingStrategy(scout));
        }
        strategies.get(scout).executeStrategy();
    }

    /**
     * Get the number of scouts
     * @return the number of scouts
     */
    public int getNbScouts() {
        return scouts.size();
    }

    /**
     * Get the number of gatherers
     * @return the number of gatherers
     */
    public int getNbGatherers() {
        return gatherers.size();
    }

    /**
     * Get the number of soldiers
     * @return the number of soldiers
     */
    public int getNbSoldiers() {
        return soldiers.size();
    }

    /**
     * Get the number of predators
     * @return the number of predators
     */
    public int getNbPredators() {
        return predators.size();
    }

    /**
     * Method to get the target of an entity
     * @param entity the entity to get the target of
     * @return the target of the entity
     */
    public Entity getTarget(Entity entity) {
        return targets.get(entity);
    }

    /**
     * Method to check if a resource is near a scout
     * @param scout the scout to check for nearby resources
     */
    public void checkForNearbyResources(Ant scout) {
        Vector2 scoutPosition = scout.getPosition();
        for (Resource resource : resources) {
            double distance = scoutPosition.dist(resource.getPosition());
            if (distance <= getRadiusResource() + ScoutRenderer.getInstance().getSize() / 2.0 + scout.getVisionRange()) {
                handleResourceFound(resource);
            }
        }
    }

    /**
     * Method to handle when a resource is found by a scout
     * @param resource the resource that was found
     */
    public void handleResourceFound(Resource resource) {

        if (!resourcesFound.contains(resource)) {
            resourcesFound.add(resource);
        }
        for (var ant : gatherers) {
            if (!resourcesFound.isEmpty() && !ant.isHolding() && objectives.get(ant) == null) {
                for (var r : resourcesFound) {
                    if (r.getNbResources() > countNbTimesColleagueInObjectivesHashMap(r)) {
                        System.out.println(ant + " is going to the found resource: " + r + " with {" + r.getNbResources() + "} resources");
                        objectives.put(ant, r);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Method to check if a predator is near an ant
     * @param ant the ant to check for nearby predators
     */
    public void checkForNearbyPredators(Ant ant) {
        for (Predator predator : predators) {
            double distance = ant.getPosition().dist(predator.getPosition());
            if (distance <= getRadiusToPredator()) {
                handlePredatorFound(predator);
            }
        }
    }

    /**
     * Method to handle when a predator is found by an ant
     * The soldiers will be sent to attack it and the others
     * ant will flee from it if they are in a certain range
     * @param predator the predator that was found
     */
    public void handlePredatorFound(Predator predator) {
        Vector2 predatorPosition = predator.getPosition();
        for (Soldier soldier : soldiers) {
            targets.putIfAbsent(soldier, predator);
        }
        for (Gatherer gatherer : gatherers) {
            double distance = gatherer.getPosition().dist(predatorPosition);
            if (gatherer.getPosition().dist(hub.getPosition()) < hub.getSize() / 2.0) {
                return;
            }
            if (distance <= getRadiusToFlee()) {
                gatherer.flee(predatorPosition);
            }
        }
        for (Scout scout : scouts) {
            double distance = scout.getPosition().dist(predatorPosition);
            if (scout.getPosition().dist(hub.getPosition()) < hub.getSize() / 2.0) {
                return;
            }
            if (distance <= getRadiusToFlee()) {
                scout.flee(predatorPosition);
            }
        }
    }

    /**
     * Method to ask the mediator if a predator is still near a specific ant
     * @param ant the ant to check if a predator is still near
     */
    public void predatorStillNearby(Ant ant) {
        Vector2 antPosition = ant.getPosition();
        for (Predator predator : predators) {
            double distance = antPosition.dist(predator.getPosition());
            if (distance > getRadiusToFlee()) {
                ant.stopFleeing();
            }
        }
    }

    /**
     * Method to handle when a gatherer has reached an objective
     * It will go to the hub if it has reached its resource
     * and will go to a found resource if it has reached the hub or
     * stay in waiting at the hub
     * @param ant the ant that has reached an objective
     */
    public void handleObjectiveReached(Ant ant) {
        System.out.println(ant + ", reached objective at " + objectives.get(ant).getPosition());
        if (!ant.isHolding()) {
            Optional<Resource> resource = resources.stream()
                    .filter(r -> r.getPosition().equals(objectives.get(ant).getPosition()))
                    .findFirst();
            objectives.put(ant, null);
            if (resource.isPresent() && resource.get().getNbResources() > 0) {
                System.out.println("Resource is decremented");
                ant.setHolding(true);
                objectives.put(ant, hub);
                resource.ifPresent(Resource::decrementResource);
            }
        } else if (objectives.get(ant) == hub) {
            ant.setHolding(false);
            hub.incrementFood();
            objectives.put(ant, null);
            if (!resourcesFound.isEmpty()) {
                for (var r : resourcesFound) {
                    if (r.getNbResources() > countNbTimesColleagueInObjectivesHashMap(r)) {
                        System.out.println(ant + " is going to the found resource: " + r + " with {" + r.getNbResources() + "} resources");
                        objectives.put(ant, r);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Method to count the number of times a colleague is in the objectives hash map
     * @param abstractColleague the colleague to count the number of times it is in the objectives hash map
     * @return the number of times the colleague is in the objectives hash map
     */
    public int countNbTimesColleagueInObjectivesHashMap(AbstractColleague abstractColleague) {
        int count = 0;
        for (AbstractColleague colleague : objectives.values()) {
            if (colleague == abstractColleague) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method to get the next turn of the simulation
     */
    public void nextTurn() {

        for (var e : getAnts()) {
            e.update();
        }
        for (Resource resource : resources) {
            resource.update();
        }
        for (Predator predator : predators) {
            predator.update();
        }
        spawnResource();
        spawnPredator();
        handleDeath();
        finish();
        hub.update();
    }

    /**
     * Method to add a new ant depending on the parameters
     */
    public void addAnt() {
        // Does nothing
    }

    /**
     * Method to check for nearby preys
     * @param predator the predator to check for nearby preys
     */
    public void checkForNearbyPreys(Predator predator) {
        for (var e : getAnts()) {
            if (predator.getPosition().dist(e.getPosition()) <= getRadiusPredatorToPrey()) {
                System.out.println(predator + " is targetting: " + e);
                targets.put(predator, e);
            }
        }
    }

    /**
     * Method to handle when a resource is depleted
     * @param resource the resource that was depleted
     */
    public void handleResourceDepleted(Resource resource) {
        for (Gatherer ga : gatherers) {
            if (objectives.get(ga) == resource) {
                objectives.put(ga, hub);
            }
        }
        toRemove.add(resource);
    }

    /**
     * Method to handle when an ant dies
     * @param ant the ant that died
     */
    public void handleAntDeath(Ant ant) {
        toRemove.add(ant);
        if (targets.containsValue(ant)) {
            for (Map.Entry<Entity, Entity> entry : targets.entrySet()) {
                if (entry.getValue() == ant) {
                    targets.remove(entry.getKey());
                    break;
                }
            }
        }
        objectives.remove(ant);
    }

    /**
     * Method to handle when a predator dies
     * @param predator the predator that died
     */
    public void handlePredatorDeath(Predator predator) {
        toRemove.add(predator);
        for (Soldier so : soldiers) {
            targets.put(so, null);
            objectives.put(so, hub);
        }
    }

    /**
     * Method to check if an entity is within range
     * @param from the entity to check from
     * @param to the entity to check to
     * @return true if the entity is within range, false otherwise
     */
    public boolean withinRange(AbstractColleague from, AbstractColleague to, int range) {
        return (Vector2.isWithinRange(from.getPosition(), to.getPosition(), range));
    }

    /**
     * Method to handle when an entity is attacked
     * @param entity the entity that was attacked
     */
    public void entityAttacked(Entity entity) {
        System.out.println(entity + " is attacking: " + getTarget(entity));
        getTarget(entity).decrementPv(entity.getStrength());
    }

    /**
     * Method to display the entities
     */
    public void display() {
        for (var e : resources) {
            e.draw();
        }
        for (var e : getAnts()) {
            e.draw();
        }
        for (var e : predators) {
            e.draw();
        }
        hub.draw();
        SimulationDisplayer.getInstance().repaint();
    }

    /**
     * Method to get the ants
     *
     * @return a list of the ants
     */
    public List<Ant> getAnts() {
        List<Ant> ants = new ArrayList<>();
        ants.addAll(gatherers);
        ants.addAll(scouts);
        ants.addAll(soldiers);
        return ants;
    }

    /**
     * Checks if a given entity is colliding with any other entity or if it's out of bounds.
     * @param newPosition The new position the entity is trying to move to.
     * @param entity The entity that is trying to move.
     * @return Returns true if the entity is colliding with another entity or is out of bounds, false otherwise.
     */
    public boolean checkCollision(Vector2 newPosition, AbstractColleague entity) {
        if (newPosition.dist(hub.getPosition()) <= ColonyRenderer.COLONY_SIZE / 2.0 + 5) {
            return false;
        }

        if (Vector2.outOfBounds(entity.getPosition(), newPosition, entity.getSize() / 2.0, SimulationDisplayer.getInstance().getWidth(), SimulationDisplayer.getInstance().getHeight())) {
            return true;
        }
        for (var e : getAnts()) {

            if (e != entity && Vector2.isColliding(entity.getPosition(), newPosition, e.getPosition(), entity.getSize() / 2.0, e.getSize() / 2.0)) {
                return true;
            }
        }
        for (var e : predators) {
            if (e != entity && Vector2.isColliding(entity.getPosition(), newPosition, e.getPosition(), entity.getSize() / 2.0, e.getSize() / 2.0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Abstract method to get the number of steps required to spawn a resource.
     * @return Returns the number of steps required to spawn a resource.
     */
    public abstract int stepToSpawnResource();

    /**
     * Spawns a resource if the number of steps from the last resource spawn is greater than or equal to the number of steps required to spawn a resource.
     */
    public void spawnResource() {
        if (stepFromLastResourceSpawn >= stepToSpawnResource()) {
            stepFromLastResourceSpawn = 0;

            Vector2 newResourcePosition;
            do {
                newResourcePosition = new Vector2(Vector2.random, SpiderRenderer.SPIDER_SPRITE_SIZE / 2, SpiderRenderer.SPIDER_SPRITE_SIZE / 2, SimulationDisplayer.getInstance().getWidth() - SpiderRenderer.SPIDER_SPRITE_SIZE / 2, SimulationDisplayer.getInstance().getHeight() - SpiderRenderer.SPIDER_SPRITE_SIZE / 2);
            } while (newResourcePosition.dist(hub.getPosition()) <= hub.getSize());

            resources.add(new Resource(this, newResourcePosition, Vector2.random.nextInt(5) + 1));
        }
    }

    /**
     * Spawns a predator if the number of steps from the last predator spawn is greater than or equal to the number of steps required to spawn a predator.
     */
    public void spawnPredator() {
        if (stepFromLastPredatorSpawn >= stepToSpawnPredator()) {
            stepFromLastPredatorSpawn = 0;

            Vector2 newPredatorPosition;
            do {
                newPredatorPosition = new Vector2(Vector2.random, SpiderRenderer.SPIDER_SPRITE_SIZE / 2, SpiderRenderer.SPIDER_SPRITE_SIZE / 2, SimulationDisplayer.getInstance().getWidth() - SpiderRenderer.SPIDER_SPRITE_SIZE / 2, SimulationDisplayer.getInstance().getHeight() - SpiderRenderer.SPIDER_SPRITE_SIZE / 2);
            } while (newPredatorPosition.dist(hub.getPosition()) <= hub.getSize());

            predators.add(new Predator(this, newPredatorPosition));
        }
    }

    /**
     * Handles the death of entities by removing them from their respective lists.
     */
    public void handleDeath() {
        for (var re : toRemove) {
            gatherers.removeIf(e -> e == re);
            scouts.removeIf(e -> e == re);
            soldiers.removeIf(e -> e == re);
            predators.removeIf(e -> e == re);
            resources.removeIf(e -> e == re);
            resourcesFound.removeIf(e -> e == re);
        }
    }

    /**
     * Gets the objective of a given entity.
     * @param entity The entity to get the objective of.
     * @return Returns the objective of the given entity.
     */
    public AbstractColleague getObjective(Entity entity) {
        return objectives.get(entity);
    }

    /**
     * Ends the simulation if there are no gatherers, scouts, or soldiers left.
     */
    public void finish() {
        if (gatherers.isEmpty() && scouts.isEmpty() && soldiers.isEmpty()) {
            Simulation.getInstance().setDone();
        }
    }

}
