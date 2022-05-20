package Entities.Fight.Ranged;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entities.BasicEntity;
import Entities.Fight.Melee.Fight;
import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.Monster;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public class RangedFight extends BasicEntity {
    private Animation invis;
    private boolean isInvis;
    private boolean rangedAttack;
    private Hero hero;

    public RangedFight(Painter painter, SpriteBatch batch, Hero hero) {
        super(painter, batch);
        setInvis();
        this.isInvis = true;
        this.rangedAttack = false;
        this.hero = hero;
    }

    public void setLevel(Level level) {
        this.currentFloor = level;
        this.currentPosition = hero.getPosition();
    }

    /**
     * setter für die invis aniamtion
     */
    private void setInvis() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("InvisibleItem/invisibileItem.png");
        this.invis = new Animation(animation, 1);
    }

    @Override
    public void update() {
        if (this.isInvis) {
            this.activeAnimation = this.invis;
            this.currentPosition = this.hero.getPosition();
        } else {
            if (this.hero.getViewDirection()) {
                this.activeAnimation = this.idleAnimation;
            } else {
                this.activeAnimation = this.idleMirroredAnimation;
            }

            if (this.rangedAttack == true) {
                rangeAttack();
            }

        }
    }

    public void startRangedAttack() {
        this.rangedAttack = true;
        this.isInvis = false;
    }

    public void rangeAttack() {
        Point newPosition = new Point(this.currentPosition);
        if (this.hero.getViewDirection()) {
            newPosition.x += 0.1f;
            if (this.currentFloor.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.currentPosition = newPosition;
                for (Monster monster : this.hero.getMonster()) {
                    if (this.currentFloor.getTileAt(this.currentPosition.toCoordinate()) == monster.getCurrentFloor()
                            .getTileAt(monster.getPosition().toCoordinate())) {
                        new Fight(hero, monster).rangedFight();
                        this.currentPosition = hero.getPosition();
                        this.rangedAttack = false;
                        this.isInvis = true;
                    }
                }
            } else {
                this.currentPosition = hero.getPosition();
                this.rangedAttack = false;
                this.isInvis = true;
            }
        } else {
            newPosition.x -= 0.1f;
            if (this.currentFloor.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.currentPosition = newPosition;
                for (Monster monster : this.hero.getMonster()) {
                    if (this.currentFloor.getTileAt(this.currentPosition.toCoordinate()) == monster.getCurrentFloor()
                            .getTileAt(monster.getPosition().toCoordinate())) {
                        new Fight(hero, monster).rangedFight();
                        this.currentPosition = hero.getPosition();
                        this.rangedAttack = false;
                        this.isInvis = true;
                    }
                }
            } else {
                this.currentPosition = hero.getPosition();
                this.rangedAttack = false;
                this.isInvis = true;
            }
        }
    }

    /**
     * getter für isinvis
     * 
     * @return true, wenn invisible, false wenn nicht
     */
    public boolean getIsInvis() {
        return this.isInvis;
    }

    @Override
    public Animation getActiveAnimation() {
        return this.activeAnimation;
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }

}
