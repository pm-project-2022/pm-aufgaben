package EntityController.Monster.SmallMonster;

import EntityController.Monster.MonsterQuantity;

public class SmallMonsterQuantity extends MonsterQuantity{


    public SmallMonsterQuantity(int floor) {
        super();
        if (floor <= 5) {
            this.monsterQuantityMin = 1;
            this.monsterQuantityMax = 4;

        } else if (floor <= 10) {
            this.monsterQuantityMin = 4;
            this.monsterQuantityMax = 10;
        } else {
            this.monsterQuantityMin = 10;
            this.monsterQuantityMax = 15;
        }
        this.quantity = this.random.nextInt(this.monsterQuantityMin + this.monsterQuantityMax) + this.monsterQuantityMin;
    }
    
}
