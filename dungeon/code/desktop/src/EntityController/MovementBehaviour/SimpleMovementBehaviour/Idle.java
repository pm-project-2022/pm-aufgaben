package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.Fight.Fight;
import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.Statuswerte.StatusValues;
import helper.PointAndBoolean;
import level.elements.Level;
import tools.Point;

public class Idle implements IMovementBehaviour {
    boolean collision;
    boolean runDirection;

    public Idle(boolean runDirection) {
        this.collision = true;
        this.runDirection = runDirection;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Point currentPosition, StatusValues stats, Level currentLevel,
            MyHero hero, Monster monster) {
        Point newPosition = new Point(currentPosition);
        if (this.runDirection) {
            if (currentLevel.getTileAt(newPosition.toCoordinate()) == hero.getLevel()
                    .getTileAt(hero.getPosition().toCoordinate())) {
                newPosition.x -= 1.0f;
                new Fight(monster, hero).fight();
            }

            if (currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointAndBoolean(this.runDirection, this.collision, newPosition);
            } else {
                return new PointAndBoolean(this.runDirection, this.collision, currentPosition);

            }
        } else {
            if (currentLevel.getTileAt(newPosition.toCoordinate()) == hero.getLevel()
                    .getTileAt(hero.getPosition().toCoordinate())) {
                newPosition.x += 1.0f;
                new Fight(monster, hero).fight();
            }

            if (currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointAndBoolean(this.runDirection, this.collision, newPosition);
            } else {
                return new PointAndBoolean(this.runDirection, this.collision, currentPosition);

            }

        }
    }

}
