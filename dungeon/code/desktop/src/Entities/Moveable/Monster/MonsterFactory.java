package Entities.Moveable.Monster;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement.PatrolXAxis;
import Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement.PatrolYAxis;
import Entities.Moveable.Monster.NormalMonster.Chort;
import Entities.Moveable.Monster.WeakMonster.Imp;
import graphic.Painter;

public class MonsterFactory {
    
   private MonsterFactory(){}

    public static ArrayList<Monster> monFac(Painter painter, SpriteBatch batch, Hero hero, int currentFloor){
        ArrayList<Monster>monster = new ArrayList<>();

        for (int i = 0; i< randomizeQuantity(currentFloor, 1); i++){
            monster.add(new Imp(painter, batch, hero, createMovement(), currentFloor));
        }

        for(int i = 0; i < randomizeQuantity(currentFloor, 2); i++){
            monster.add(new Chort(painter, batch, hero, createMovement(), currentFloor));
        }
        
        return monster;
    }

    private static int randomizeQuantity(int currentFloor, int monsterclass){
        Random random = new Random();
        int monsterQuantityMin;
        int monsterQuantityMax;
        if(monsterclass == 1){
            if (currentFloor <= 5) {
                monsterQuantityMin = 1;
                monsterQuantityMax = 3;
    
            } else if (currentFloor <= 10) {
                monsterQuantityMin = 3;
                monsterQuantityMax = 6;
            } else {
                monsterQuantityMin = 1;
                monsterQuantityMax = 3;
            }
        }else{
            if (currentFloor < 3) {
                return 0;
    
            } else if (currentFloor <= 10) {
                monsterQuantityMin = 1;
                monsterQuantityMax = 2;
            } else {
                monsterQuantityMin = 3;
                monsterQuantityMax = 6;
            }
        }
        return random.nextInt(monsterQuantityMin + monsterQuantityMax) + monsterQuantityMin;   
    }

    private static IMovement createMovement(){
        Random random = new Random();
        int monsterQuantityMin = 1;
        int monsterQuantityMax = 2;
         ;
        if(random.nextInt(monsterQuantityMin + monsterQuantityMax) + monsterQuantityMin == 1){
            return new PatrolXAxis();
        }else{
            return new PatrolYAxis();
        }
    }

    /*private Monster createSmallMonster(Painter painter, SpriteBatch batch, String monName, Hero hero, int floor, IMovement movement){
        return new Imp(painter, batch, hero, movement, floor);
    }

    private Monster createNormalMonster(Painter painter, SpriteBatch batch, String monName, Hero hero, int floor, IMovement movement){
        return new Chort(painter, batch, hero, movement, floor);
    }*/
    
}
