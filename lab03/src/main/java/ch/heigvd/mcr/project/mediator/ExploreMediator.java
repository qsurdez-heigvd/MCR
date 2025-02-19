package ch.heigvd.mcr.project.mediator;

import ch.heigvd.mcr.project.mediator.strategies.*;
import ch.heigvd.mcr.project.models.*;


/**
 * Class representing an ExploreMediator in the simulation.
 * This mediator makes all the ants explore the environment. Only the gatherers can
 * hold resources and carry them back to the hub. The soldiers will attack the predators.
 * The hub will spawn ants when a certain amount of food is reached.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class ExploreMediator extends Mediator {

    /**
     * Constructor for the ExploreMediator class.
     * @param mediator The mediator to copy.
     */
    public ExploreMediator(Mediator mediator) {
        super(mediator);
    }


    /**
     * Handles the update of a gatherer ant.
     * It will have a different strategy depending on some conditions.
     * If the gatherer is fleeing, it will have a FleeingStrategy. It will have a GoHome strategy if it has an objective.
     * Otherwise, it will scout
     * @param gatherer the gatherer to handle the update of
     */
    @Override
    public void handleUpdateGatherer(Gatherer gatherer) {
        if (gatherer.isFleeing()) {
            strategies.put(gatherer, new FleeingStrategy(gatherer));
        } else if (objectives.get(gatherer) != null) {
            strategies.put(gatherer, new ObjectiveHomeStrategy(gatherer));
        } else {
            strategies.put(gatherer, new ScoutingStrategy(gatherer));
        }
        strategies.get(gatherer).executeStrategy();
    }

    /**
     * Handles the update of a soldier ant.
     * It will have a different strategy depending on some conditions.
     * If the gatherer has a target, it will have an attacking strategy.
     * It will have a GoHome strategy if it has an objective.
     * Otherwise, it will scout.
     * @param soldier the soldier to handle the update of
     */
    @Override
    public void handleUpdateSoldier(Soldier soldier) {
        if (getTarget(soldier) != null) {
            strategies.put(soldier, new AttackStrategy(soldier));
        } else if (objectives.get(soldier) != null) {
            strategies.put(soldier, new ObjectiveHomeStrategy(soldier));
        } else {
            strategies.put(soldier, new ScoutingStrategy(soldier));
        }
        strategies.get(soldier).executeStrategy();
    }

    /**
     * Spawn a new gatherer if there is enough food in the hub or a soldier if there
     * is enough food and too many predators.
     */
    @Override
    public void addAnt() {
        if (hub.getNbFood() >= getThresholdToCreateAnt() && !predators.isEmpty()) {
            soldiers.add(new Soldier(this, hub.getPosition()));
            hub.decrementFood(getThresholdToCreateAnt());
        } else if (hub.getNbFood() >= getThresholdToCreateAnt()) {
            gatherers.add(new Gatherer(this, hub.getPosition()));
            hub.decrementFood(getThresholdToCreateAnt());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusToPredator() {
        return 50;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusToFlee() {
        return 20;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getThresholdToCreateAnt() {
        return 6;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusPredatorToPrey() {
        return 60;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int stepToSpawnResource() {
        return 5;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getRadiusResource() {
        return 5;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int stepToSpawnPredator() {
        return 30;
    }
}
