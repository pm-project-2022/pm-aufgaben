package EntityController.Monster;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import EntityController.Hero.MyHero;
import EntityController.Monster.NormalMonster.Chort;
import EntityController.Monster.SmallMonster.Imp;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.MovementBehaviour.SimpleMovementBehaviour.PatrolX;
import EntityController.MovementBehaviour.SimpleMovementBehaviour.PatrolY;
import graphic.Painter;

public class MonsterFactory{
    private MonsterFactory() {
        
    }

    public static Monster monFac(Painter painter, SpriteBatch batch, String monName, MyHero hero, int floor){
        IMovementBehaviour behaviour;
        Random random = new Random();
        int i = random.nextInt(2+1) + 1;

        if (i == 1) {
            behaviour = new PatrolY();
        } else {
            behaviour = new PatrolX();
        }

        if (monName.equals("Imp")) {
            return new Imp(painter, batch, hero, floor, behaviour);
        }
        if (monName.equals("Chort")) {
            return new Chort(painter, batch, hero, floor, behaviour);
        }
        
        return null;
    }
}
