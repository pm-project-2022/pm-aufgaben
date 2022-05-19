package Attribute.Moveables.Monster;

import Attribute.BasicAttributes;

/**
 * Attribute Klasse für monster
 */

public class MonsterAttributes extends BasicAttributes {

    public MonsterAttributes(int currentHP, int attackPower, int defensePower, int accuracy, int evasion, int level, int currentExp, float movementSpeed){
        this.maxHP = currentHP * level;
        this.currentHP = currentHP * level;
        this.attackPower = attackPower * level;
        this.defensePower = defensePower * level;
        this.accuracy = accuracy * level;
        this.evasion = evasion * level;
        this.level = 1 * level;
        this.currentExp = currentExp * level;
        this.movementSpeed = movementSpeed;
    }

    
}
