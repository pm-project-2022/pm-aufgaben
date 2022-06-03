package Skills.ConvertStats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Skills.ConvertAttributes;
import Entities.Moveable.Hero.Hero;
import Skills.BasicSkill;
import graphic.Painter;

public class MovementSpeedBoost extends BasicSkill{

    public MovementSpeedBoost(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.convertAttributes = new ConvertAttributes(5, 5, 2, 150, 0.1f, 60);
    }
    
    public void convert(Hero hero){
        hero.getAttributes().setCurrentHP(hero.getAttributes().getCurrentHP() - this.convertAttributes.getHp());
        hero.getAttributes().setCurrentMana(hero.getAttributes().getCurrentMana() - this.convertAttributes.getMana());
        hero.getAttributes().setMovementSpeed(hero.getAttributes().getMovementSpeed() + this.convertAttributes.getMovementSpeed());
    }

    public boolean enoughRessources(Hero hero){
        if(hero.getAttributes().getCurrentHP() < this.convertAttributes.getHp() && hero.getAttributes().getCurrentMana() < this.convertAttributes.getMana()){
            return false;
        }else{
            return true;
        }
    }
}
