package Skills.ConvertStats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Skills.ConvertAttributes;
import Entities.Moveable.Hero.Hero;
import Skills.BasicSkill;
import graphic.Painter;

public class ConvertMPToHP extends BasicSkill{
   
    public ConvertMPToHP(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.convertAttributes = new ConvertAttributes(25,25,2,150);
    }
    
    public void convert(Hero hero){
        hero.getAttributes().setCurrentMana(hero.getAttributes().getCurrentMana() - this.convertAttributes.getMana());
        hero.getAttributes().setCurrentHP(hero.getAttributes().getCurrentHP() + this.convertAttributes.getHp());
        if(hero.getAttributes().getCurrentHP() > hero.getAttributes().getMaxHP()){
            hero.getAttributes().setCurrentHP(hero.getAttributes().getMaxHP());
        } 
    }

    public boolean enoughRessources(Hero hero){
        if(hero.getAttributes().getCurrentMana() < this.convertAttributes.getMana()){
            return false;
        }else{
            return true;
        }
    }
}
