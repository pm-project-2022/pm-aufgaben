package Skills;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Skills.AuraAttributes;
import Attribute.Skills.ConvertAttributes;
import Entities.BasicEntity;
import Entities.Moveable.Hero.Hero;
import graphic.Painter;

public abstract class BasicSkill extends BasicEntity{
    protected AuraAttributes auraAttributes;
    protected ConvertAttributes convertAttributes;
    
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

    public ConvertAttributes getConvertAttributes() {
        return convertAttributes;
    }

    /**
     * aktiviert die aura des heldens
     * @param hero held
     */
    public void activateAura(Hero hero){
    

    }
    
    /**
     * deaktiviert die aura des heldebs
     * @param hero held
     */
    public void deactiveAura(Hero hero){
        
    }

    /**
     * konvertiert die stats des heldens in andere
     * @param hero held
     */
    public void convert(Hero hero){

    }

    /**
     * checkt ob die stats des helden hoch genug sind um konvertieren zu können
     * @param hero held
     * @return true wenn ja, sonst false
     */
    public boolean enoughRessources(Hero hero){
        return true;
    }
}
