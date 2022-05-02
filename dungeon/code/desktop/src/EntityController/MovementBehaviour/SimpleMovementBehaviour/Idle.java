package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.Fight.Fight;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import helper.PointAndBoolean;
import tools.Point;

/**
 * Puts the monster into idle animation
 */
public class Idle implements IMovementBehaviour {
    boolean collision;
    boolean runDirection;

    public Idle(boolean runDirection) {
        this.collision = true;
        this.runDirection = runDirection;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Monster monster) {
        Point newPosition = new Point(monster.getPosition());
        if (this.runDirection) {
            if (monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()) == monster.getHero().getLevel()
                    .getTileAt(monster.getHero().getPosition().toCoordinate())) {
                if (new Fight(monster).fight()) {
                    newPosition.x -= 1.0f;
                }
            }

            if (monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointAndBoolean(this.runDirection, this.collision, newPosition);
            } else {
                return new PointAndBoolean(this.runDirection, this.collision, monster.getPosition());

            }
        } else {
            if (monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()) == monster.getHero().getLevel()
                    .getTileAt(monster.getHero().getPosition().toCoordinate())) {
                if (new Fight(monster).fight()) {
                    newPosition.x += 1.0f;
                }
            }
            if (monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointAndBoolean(this.runDirection, this.collision, newPosition);
            } else {
                return new PointAndBoolean(this.runDirection, this.collision, monster.getPosition());

            }

        }
    }

}
