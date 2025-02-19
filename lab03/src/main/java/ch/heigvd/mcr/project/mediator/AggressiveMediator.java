package ch.heigvd.mcr.project.mediator;

import ch.heigvd.mcr.project.mediator.strategies.*;
import ch.heigvd.mcr.project.models.*;


/**
 * Class representing an AggressiveMediator in the simulation.
 * An aggressive mediator is a mediator that sends all ants to attack a predator when one is found.
 * It adds another layer of logic that when the ants have killed
 * a predator it will go to the next found predator.
 * The only ants scouting are still the scouts.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class AggressiveMediator extends Mediator {

    /**
     * Constructor for the AggressiveMediator class.
     * @param mediator The mediator to copy.
     */
    public AggressiveMediator(Mediator mediator) {
        super(mediator);
    }

    /**
     * Overrides the handleResourceFound method from the Mediator class.
     * In this implementation, nothing happens when a resource is found.
     * @param resource The resource that was found.
     */
    @Override
    public void handleResourceFound(Resource resource) {
        // Do nothing
    }

    /**
     * Overrides the handlePredatorFound method from the Mediator class.
     * In this implementation, when a predator is found, it is added to the list of predators and all ants are sent to track it.
     * @param predator The predator that was found.
     */
    @Override
    public void handlePredatorFound(Predator predator) {
        if(!predatorsFound.contains(predator)){
            predatorsFound.add(predator);
        }
        for(Entity entity: getAnts()){
            targets.putIfAbsent(entity, predator);
            System.out.println(entity + " has been sent to track the predator");
        }
    }

    /**
     * Method to handle when a predator dies.
     * When a predator dies, it is removed from the list of predators and all ants that were tracking it are sent to track another predator or return to the hub.
     * @param predator the predator that died
     */
    @Override
    public void handlePredatorDeath(Predator predator) {
        toRemove.add(predator);
        for (Entity ant : getAnts()) {
            if(targets.get(ant) == predator){
                if(!predatorsFound.isEmpty()){
                    for (Predator p : predators) {
                        if (!p.equals(predator)) {
                            targets.put(ant, p);
                        }
                    }
                } else {
                    targets.put(ant, null);
                    objectives.put(ant, hub);
                }
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void predatorStillNearby(Ant ant) {
        // Keep the ant in the same state
    }

    /**
     * Handles the update of a gatherer ant.
     * It will have a different strategy depending on some conditions.
     * If the gatherer has a target it will have an attacking strategy.
     * If it has an objective it will go home.
     * @param gatherer the gatherer to handle the update of
     */
    @Override
    public void handleUpdateGatherer(Gatherer gatherer) {
        if (targets.get(gatherer) != null) {
            strategies.put(gatherer, new AttackStrategy(gatherer));
        } else {
            strategies.put(gatherer, new ObjectiveHomeStrategy(gatherer));
        }
        strategies.get(gatherer).executeStrategy();
    }

    /**
     * Handles the update of a soldier ant.
     * It will have a different strategy depending on some conditions.
     * If the soldier has a target it will have an attacking strategy.
     * If it has an objective it will go home.
     * @param soldier the gatherer to handle the update of
     */
    @Override
    public void handleUpdateSoldier(Soldier soldier) {
        if (getTarget(soldier) != null) {
            strategies.put(soldier, new AttackStrategy(soldier));
        } else {
            strategies.put(soldier, new ObjectiveHomeStrategy(soldier));
        }
        strategies.get(soldier).executeStrategy();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusResource() {
        return 50;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusToPredator() {
        return 200;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusToFlee() {
        return 40;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getThresholdToCreateAnt() {
        return 3;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusPredatorToPrey() {
        return 100;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int stepToSpawnResource() {
        return 10000;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int stepToSpawnPredator() {
        return 5;
    }

    /**
     * Method to handle when a predator dies.
     * When a predator dies, it is removed from the list of predators and all ants that were tracking it are sent to track another predator or return to the hub.
     */
    @Override
    public void handleDeath() {
        super.handleDeath();
        for (var re : toRemove) {
            predatorsFound.removeIf(e -> e == re);
        }
    }


}
