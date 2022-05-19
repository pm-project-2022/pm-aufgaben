package Attribute.Moveables.Hero;

import Attribute.BasicAttributes;

/**
 * Attribute Klasse für den Helden
 */

public class HeroAttributes extends BasicAttributes {

    public HeroAttributes(int hp, int mana, int atkP, int defP, int acc, int eva, int level, int exp, int expForLvlUp,
            float ms) {
        this.currentHP = hp;
        this.maxHP = hp;
        this.currentMana = mana;
        this.maxMana = mana;
        this.auraMana = mana;
        this.attackPower = atkP;
        this.auraAttackPower = atkP;
        this.auraDefensePower = defP;
        this.defensePower = defP;
        this.accuracy = acc;
        this.evasion = eva;
        this.auraEvasion = eva;
        this.level = level;
        this.currentExp = exp;
        this.expForLvlUp = expForLvlUp;
        this.movementSpeed = ms;
    }

    

}
