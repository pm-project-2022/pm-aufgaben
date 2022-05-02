package EntityController.Fight;

import java.util.Random;

import EntityController.Monster.Monster;

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
        if (this.monster.getHero().getStats().getAccuracy() > this.monster.getStats().getEvasion()) {
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
        if (this.monster.getHero().getStats().getStrength() > this.monster.getStats().getdefense()) {
            this.dmg = (this.monster.getStats().getdefense() - this.monster.getHero().getStats().getStrength());
            int monHP = this.monster.getStats().getHealhtPoints() + this.dmg;
            if (monHP < 0) {
                this.monster.getStats().setHealhtPoints(0);
                System.out.println(this.monster.getStats().toString());

            } else {
                this.monster.getStats().setHealhtPoints(monHP);
                System.out.println(this.monster.getStats().toString());
            }
        } else {
            this.monster.getStats().setHealhtPoints(this.monster.getStats().getHealhtPoints() - 1);
            System.out.println(this.monster.getStats().toString());
        }
        return true;

    }
}
