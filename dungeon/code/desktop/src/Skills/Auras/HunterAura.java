package Skills.Auras;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Skills.AuraAttributes;
import Entities.Moveable.Hero.Hero;
import Skills.BasicSkill;
import graphic.Painter;

public class HunterAura extends BasicSkill{
    private final int evasionBuff = 4;
    
    public HunterAura(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.auraAttributes = new AuraAttributes(5, 0, 2);
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
        hero.getAttributes().setAuraEvasion(hero.getAttributes().getEvasion() + hero.getAttributes().getEvasion() / this.evasionBuff);

    }

    /**
     * deaktiviert die aura wieder und setzt die werte zurück auf default
     */
    public void deactiveAura(Hero hero){
        hero.getAttributes().setAuraMana(hero.getAttributes().getMaxMana());
        hero.getAttributes().setAuraEvasion(hero.getAttributes().getEvasion());
    }
}
