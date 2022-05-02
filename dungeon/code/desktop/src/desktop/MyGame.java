package desktop;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;

import Entities.Items.Item;
import Entities.Items.ItemFactory;
import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Hero.Classes.Knight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterFactory;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;

public class MyGame extends MainController {
    public Hero hero;
    private int currentFloor;
    private ArrayList<Monster> monster;
    private ArrayList<Item> items;
   
    @Override
    protected void setup() {
        this.hero = new Knight(painter, batch);
        this.hero.setAvailableItems(ItemFactory.itemListFac(painter, batch));
        this.currentFloor = 1;
        this.monster = new ArrayList<Monster>();
        this.hero.getAttributes().setCurrentHP(20);
        this.hero.getAttributes().setCurrentMana(20);

        //spawns hero
        camera.follow(hero);
        entityController.add(hero);

        levelAPI.setGenerator(new LevelLoader());
        // load the first level
        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println(
                    "Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
    }

    @Override
    protected void beginFrame() {
    }

    @Override
    protected void endFrame() {
        if (levelAPI.getCurrentLevel().isOnEndTile(hero)) {
            try {
                for (Monster monster : this.monster) {
                    if(entityController.contains(monster)){
                        entityController.remove(monster);
                    }
                }
                for (Item item : this.items) {
                    if(item.getIsOnFloor()){
                        entityController.remove(item);
                    }
                }
                this.monster.clear();
                this.items.clear();
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLevelLoad() {
        hero.setLevel(levelAPI.getCurrentLevel());
        initMons();
        initItems();
    }

    private void initMons(){
        this.monster = MonsterFactory.monFac(painter, batch, this.hero, this.currentFloor);
        for (Monster monster : this.monster) {
            monster.setLevel(levelAPI.getCurrentLevel());
            entityController.add(monster);
        }
        this.currentFloor ++;
    }

    private void initItems(){
        this.items = ItemFactory.itemFac(painter, batch);
        for (Item item : this.items) {
            item.setLevel(levelAPI.getCurrentLevel());
            entityController.add(item);
        }
        this.hero.setFloorItems(items);
    }

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
