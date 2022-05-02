package Attribute.Moveables.Hero;

import Attribute.BasicAttributes;

public class HeroAttributes extends BasicAttributes {

    public HeroAttributes(int hp, int mana, int atkP, int defP, int acc, int eva, int level, int exp, int expForLvlUp,
            float ms) {
        this.currentHP = hp;
        this.maxHP = hp;
        this.currentMana = mana;
        this.maxMana = mana;
        this.attackPower = atkP;
        this.defensePower = defP;
        this.accuracy = acc;
        this.evasion = eva;
        this.level = level;
        this.currentExp = exp;
        this.expForLvlUp = expForLvlUp;
        this.movementSpeed = ms;
    }

    

}
