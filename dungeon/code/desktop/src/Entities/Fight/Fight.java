package Entities.Fight;

import Entities.Moveable.Monster.Monster;
import Helper.Booleans;

import java.util.Random;

/**
 * Calculates a fight/attack between hero and monster
 */

public class Fight {
    private Monster monster;

    //hitchance
    private int hit;

    //damage dealt
    private int dmg;

    private Booleans fightResult;

    Random random;

    /**
     * Constructor
     * @param monster attacked by the hero
     */
    public Fight(Monster monster) {
        this.monster = monster;
        this.random = new Random();
        this.fightResult = new Booleans();
    }

    /**
     * starts the fight
     * @return dealt damage or not
     */

    public Booleans fight() {
        //monster does dmg to hero
        if(this.monster.getAttributes().getAccuracy() > this.monster.getHero().getAttributes().getEvasion()){
            this.fightResult.setHeroDmg(calculateHeroDmg());
        }else{
            if(calculateHit()){
                this.fightResult.setHeroDmg(calculateHeroDmg());
            }else{
                this.fightResult.setHeroDmg(false);
            }
        }

        //hero does dmg to monster
        if (this.monster.getHero().getAttributes().getAccuracy() > this.monster.getAttributes().getEvasion()) {
            this.fightResult.setMonsterDmg(calculateDamageMonster());
        } else {
            if(calculateHit()){
                this.fightResult.setMonsterDmg(calculateDamageMonster());
            }else{
                this.fightResult.setMonsterDmg(false);
            }
        }

        return this.fightResult;
    }

    /**
     * calculate if the hit misses or not
     * @return hit miss or not
     */

    private boolean calculateHit() {
        this.hit = random.nextInt(2 + 1) + 1;
        if (this.hit == 1) {
            return true;
        }
        return false;
    }

    private boolean calculateHeroDmg(){
        if(this.monster.getAttributes().getAttackPower() > this.monster.getHero().getAttributes().getDefensePower()){
            this.dmg = (this.monster.getHero().getAttributes().getDefensePower() - this.monster.getAttributes().getAttackPower());
            int heroHp = this.monster.getHero().getAttributes().getCurrentHP() + this.dmg;
            if(heroHp < 0 ){
                this.monster.getHero().getAttributes().setCurrentHP(0);
            }else{
                this.monster.getHero().getAttributes().setCurrentHP(heroHp);
            }
        }else{
            this.monster.getHero().getAttributes().setCurrentHP(this.monster.getHero().getAttributes().getCurrentHP() - 1);
        }

        return true;
    }

    /**
     * calculates damage if the hit was successful
     * @return true
     */
    private boolean calculateDamageMonster() {
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
