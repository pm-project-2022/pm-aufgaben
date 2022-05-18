package Skills;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Skills.AuraAttributes;
import Entities.BasicEntity;
import Entities.Moveable.Hero.Hero;
import graphic.Painter;

public abstract class BasicSkill extends BasicEntity{
    protected AuraAttributes auraAttributes;
    
    public BasicSkill(Painter painter, SpriteBatch batch) {
        super(painter, batch);
    }

    /**
     * getter für die attribute der aura
     * @return aurattribute
     */
    public AuraAttributes getAuraAttributes() {
        return auraAttributes;
    }

    public void activateAura(Hero hero){
    

    }

    public void deactiveAura(Hero hero){
        
    }
}
