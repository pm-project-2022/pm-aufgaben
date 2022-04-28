package EntityController.Monster;

import java.util.ArrayList;

/**
 * Absctract class to create a list with all monster names of one monster sublass
 */

public abstract class MonsterNameList {
    //stores the monster names
    protected ArrayList<String> monsterList;

    /**
     * Constructor
     */
    public MonsterNameList(){
        this.monsterList = new ArrayList<>();
    }

    /**
     * getter for monsterlist 
     * @return the list that stores the monsternames
     */
    public ArrayList<String> getMonsterList(){
        return this.monsterList;
    }
}
