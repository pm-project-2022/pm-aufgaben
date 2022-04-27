package EntityController.Monster;

import java.util.ArrayList;

public abstract class MonsterNameList {
    protected ArrayList<String> monsterList;

    public MonsterNameList(){
        this.monsterList = new ArrayList<>();
    }

    public ArrayList<String> getMonsterList(){
        return this.monsterList;
    }
}
