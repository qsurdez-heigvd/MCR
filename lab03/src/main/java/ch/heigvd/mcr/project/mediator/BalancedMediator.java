package ch.heigvd.mcr.project.mediator;

import ch.heigvd.mcr.project.mediator.strategies.*;
import ch.heigvd.mcr.project.models.Gatherer;
import ch.heigvd.mcr.project.models.Soldier;

/**
 * Class representing a BalancedMediator in the simulation.
 * A balanced mediator is a mediator that will give the expected roles to the different ants.
 * A soldier will attack a predator when one is found.
 * A scout will explore the map and send information when he has found a resource
 * A gatherer go gather resources found
 * A predator explore the map and attack the ants when they are within its range
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class BalancedMediator extends Mediator {

    /**
     * Constructor for the BalancedMediator class.
     * @param nbSoldiers The number of soldier ants.
     * @param nbGatherers The number of gatherer ants.
     * @param nbScouts The number of scout ants.
     */
    public BalancedMediator(int nbSoldiers, int nbGatherers, int nbScouts) {
        super(nbSoldiers, nbGatherers, nbScouts);
    }

    /**
     * Copy constructor for the BalancedMediator class.
     * @param mediator The mediator to copy.
     */
    public BalancedMediator(Mediator mediator) {
        super(mediator);
    }


    /**
     * Handles the update of a gatherer ant.
     * It will have a different strategy depending on some conditions.
     * If the gatherer is fleeing, it will have a FleeingStrategy. It will have a GoHome strategy if it has an objective
     * and its objective is the hub otherwise it will have a goTo resource strategy.
     * Otherwise, it will have an idle strategy.
     * @param gatherer the gatherer to handle the update of
     */
    @Override
    public void handleUpdateGatherer(Gatherer gatherer) {
        if (gatherer.isFleeing()) {
            strategies.put(gatherer, new FleeingStrategy(gatherer));
        } else if (objectives.get(gatherer) != null) {
            if (objectives.get(gatherer) == hub) {
                strategies.put(gatherer, new ObjectiveHomeStrategy(gatherer));
            } else {
                strategies.put(gatherer, new ObjectiveResourceStrategy(gatherer));
            }
        } else {
            strategies.put(gatherer, new IdleStrategy());
        }
        strategies.get(gatherer).executeStrategy();
    }

    /**
     * Handles the update of a soldier ant.
     * It will have a different strategy depending on some conditions.
     * If the gatherer is fleeing, it will have an attacking strategy.
     * It will have a GoHome strategy if it has an objective.
     * Otherwise, it will have an idle strategy.
     * @param soldier the soldier to handle the update of
     */
    @Override
    public void handleUpdateSoldier(Soldier soldier) {
        if (getTarget(soldier) != null) {
            strategies.put(soldier, new AttackStrategy(soldier));
        } else if (objectives.get(soldier) != null) {
            strategies.put(soldier, new ObjectiveHomeStrategy(soldier));
        } else {
            strategies.put(soldier, new IdleStrategy());
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
        return 100;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusToFlee() {
        return 50;
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
        return 8;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int stepToSpawnPredator() {
        return 20;
    }

}

