package Entities.Fight;

import java.util.Random;

import Entities.Moveable.Monster.Monster;

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
     * calculates damage if the hit was successfull
     * @return true
     */

    private boolean calculateDamage() {
        if (this.monster.getHero().getAttributes().getAttackPower() > this.monster.getAttributes().getDefensePower()) {
            this.dmg = (this.monster.getAttributes().getDefensePower() - this.monster.getHero().getAttributes().getAttackPower());
            int monHP = this.monster.getAttributes().getCurrentHP() + this.dmg;
            if (monHP < 0) {
                this.monster.getAttributes().setCurrentHP(0);
                //System.out.println(this.monster.getAttributes().toString());

            } else {
                this.monster.getAttributes().setCurrentHP(monHP);
                //System.out.println(this.monster.getAttributes().toString());
            }
        } else {
            this.monster.getAttributes().setCurrentHP(this.monster.getAttributes().getCurrentHP() - 1);
            //System.out.println(this.monster.getAttributes().toString());
        }
        return true;

    }
}
