package EntityController.Fight;

import java.util.Random;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;

public class Fight {
    Monster monster;
    int hit;
    int dmg;
    Random random;

    public Fight(Monster monster){
        this.monster = monster;
        this.random = new Random();
    }

    public void fight(){
        if(this.monster.getHero().getStats().getAccuracy() > this.monster.getStats().getEvasion()){
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
        if(this.monster.getHero().getStats().getStrength() > this.monster.getStats().getdefense()){
            this.dmg = (this.monster.getStats().getdefense() - this.monster.getHero().getStats().getStrength()) - this.monster.getStats().getHealhtPoints();

            if(this.dmg >= 1){
                this.monster.getStats().setHealhtPoints(this.dmg);
            }else{
                this.monster.getStats().setHealhtPoints(0);
            }
        }
        System.out.println(this.monster.getStats().toString());
    }
}
