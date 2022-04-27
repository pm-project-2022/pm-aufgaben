package EntityController.Monster.NormalMonster;

import EntityController.Monster.MonsterQuantity;

public class NormalMonsterQuantity extends MonsterQuantity{

    public NormalMonsterQuantity(int floor) {
        super();
        if (floor <= 3) {
            this.quantity = 0;
            return;

        } else if (floor <= 10) {
            this.monsterQuantityMin = 1;
            this.monsterQuantityMax = 3;
        } else {
            this.monsterQuantityMin = 3;
            this.monsterQuantityMax = 7;
        }

        this.quantity = this.random.nextInt(this.monsterQuantityMin + this.monsterQuantityMax) + this.monsterQuantityMin;
    }
    
}
