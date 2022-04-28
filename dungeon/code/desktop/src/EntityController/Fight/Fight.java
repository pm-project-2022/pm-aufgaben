package EntityController.Fight;

import java.util.Random;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;

public class Fight {
    Monster monster;
    MyHero hero;
    int hit;
    int dmg;
    Random random;

    public Fight(Monster monster, MyHero hero){
        this.monster = monster;
        this.hero = hero;
        this.random = new Random();
    }

    public void fight(){
        if(this.hero.getStats().getAccuracy() > this.monster.getStats().getEvasion()){
            calculateDamage();
        }else{
            calculateHit();
        }
    }

    private void calculateHit() {
        this.hit = random.nextInt(2+1) + 1;
        if(this.hit == 1){
            calculateDamage();
        }
    }

    private void calculateDamage(){
        if(this.hero.getStats().getStrength() > this.monster.getStats().getdefense()){
            this.dmg = (this.monster.getStats().getdefense() - this.hero.getStats().getStrength()) - this.monster.getStats().getHealhtPoints();

            if(this.dmg >= 1){
                this.monster.getStats().setHealhtPoints(this.dmg);
            }else{
                this.monster.getStats().setHealhtPoints(0);
            }
        }
        System.out.println(this.monster.getStats().toString());
    }
}
