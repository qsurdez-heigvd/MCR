package ch.heigvd.mcr.project.mediator.strategies;

import ch.heigvd.mcr.project.models.Entity;

import static ch.heigvd.mcr.project.utils.Vector2.getDirection;

/**
 * Class representing an Attack strategy in the simulation.
 * An AttackStrategy is a movement strategy where an entity moves towards a target and attacks it.
 * @author REDACTED
 * @author REDACTED
 * @author Surdez Quentin
 */
public class AttackStrategy implements MovementStrategy {

    private final Entity entity; // The entity that is using this strategy

    /**
     * Constructor for the AttackStrategy class.
     * @param entity The entity that will be using this strategy.
     */
    public AttackStrategy(Entity entity) {
        this.entity = entity;
    }

    /**
     * Executes the Attack strategy.
     * In this strategy, the entity moves towards a target and attacks it if it is within range.
     * The entity's position is updated, and it checks if the target is within attack range.
     */
    @Override
    public void executeStrategy() {
        Entity target = entity.getMediator().getTarget(entity);
        if (target != null) {
            entity.move(entity.getPosition().add(getDirection(entity.getPosition(), target.getPosition())));
            if (entity.getMediator().withinRange(entity, target, (entity.getSize() / 2) + (target.getSize()/2 + 5))) {
                entity.attack();
            }
        }
    }
}
