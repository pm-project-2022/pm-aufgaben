package Entities.Fight;

import Entities.Moveable.Monster.Monster;

import java.util.Random;

/**
 * Calculates a fight/attack between hero and monster
 */

public class Fight {
    Monster monster;

    //hitchance
    int hit;

    //damage dealt
    int dmg;

    Random random;

    /**
     * Constructor
     * @param monster attacked by the hero
     */
    public Fight(Monster monster) {
        this.monster = monster;
        this.random = new Random();
    }

    /**
     * starts the fight
     * @return dealt damage or not
     */

    public boolean fight() {
        if (this.monster.getHero().getAttributes().getAccuracy() > this.monster.getAttributes().getEvasion()) {
            return calculateDamage();
        } else {
            return calculateHit();
        }
    }

    /**
     * calculate if the hit misses or not
     * @return hit miss or not
     */

    private boolean calculateHit() {
        this.hit = random.nextInt(2 + 1) + 1;
        if (this.hit == 1) {
            return calculateDamage();
        }
        return false;
    }

    /**
     * calculates damage if the hit was successful
     * @return true
     */

    private boolean calculateDamage() {
        if (this.monster.getHero().getAttributes().getAttackPower() > this.monster.getAttributes().getDefensePower()) {
            this.dmg = (this.monster.getAttributes().getDefensePower() - this.monster.getHero().getAttributes().getAttackPower());
            int monHP = this.monster.getAttributes().getCurrentHP() + this.dmg;
            if (monHP < 0) {
                this.monster.getAttributes().setCurrentHP(0);
            } else {
                this.monster.getAttributes().setCurrentHP(monHP);
            }
        } else {
            this.monster.getAttributes().setCurrentHP(this.monster.getAttributes().getCurrentHP() - 1);
        }
        return true;

    }
}
