package Skills.Auras;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Skills.AuraAttributes;
import Entities.Moveable.Hero.Hero;
import Skills.BasicSkill;
import graphic.Painter;


public class KnightAura extends BasicSkill {
    private final int defenseBuff = 4;

    public KnightAura(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.auraAttributes = new AuraAttributes(10,0,2);
    }

    /**
     * wenn die aura activiert wird, wird der maximale manapool um einen gewissen Prozentsatz reduziert und dafür die defense power des helden gesteigert
     * @param hero
     */
    public void activateAura(Hero hero){
        hero.getAttributes().setAuraMana(hero.getAttributes().getMaxMana() - hero.getAttributes().getMaxMana() / this.auraAttributes.getManaStorage());
        if(hero.getAttributes().getCurrentMana() > hero.getAttributes().getAuraMana()){
            hero.getAttributes().setCurrentMana(hero.getAttributes().getAuraMana());
        }
        hero.getAttributes().setAuraDefensePower(hero.getAttributes().getDefensePower() + hero.getAttributes().getDefensePower() / defenseBuff);

    }

    /**
     * deaktiviert die aura wieder und setzt die werte zurück auf default
     */
    public void deactiveAura(Hero hero){
        hero.getAttributes().setAuraMana(hero.getAttributes().getMaxMana());
        hero.getAttributes().setAuraDefensePower(hero.getAttributes().getDefensePower());
    }
    
    

    
    
}
