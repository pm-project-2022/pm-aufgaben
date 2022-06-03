package Skills.ConvertStats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Skills.ConvertAttributes;
import Entities.Moveable.Hero.Hero;
import Skills.BasicSkill;
import graphic.Painter;

public class ConvertHptoMana extends BasicSkill{

    public ConvertHptoMana(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.convertAttributes = new ConvertAttributes(25,25,2,150);
    }

    public void convert(Hero hero){
        hero.getAttributes().setCurrentMana(hero.getAttributes().getCurrentMana() + this.convertAttributes.getMana());
        if(hero.getAttributes().getCurrentMana() > hero.getAttributes().getAuraMana()){
            hero.getAttributes().setCurrentMana(hero.getAttributes().getAuraMana());
        }
        hero.getAttributes().setCurrentHP(hero.getAttributes().getCurrentHP() - this.convertAttributes.getHp());
    }

    public boolean enoughRessources(Hero hero){
        if(hero.getAttributes().getCurrentHP() <= this.convertAttributes.getHp()){
            return false;
        }else{
            return true;
        }
    }
    
}
