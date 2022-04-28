package desktop;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.Monster.MonsterFactory;
import EntityController.Monster.NormalMonster.NormalMonsterNameList;
import EntityController.Monster.NormalMonster.NormalMonsterQuantity;
import EntityController.Monster.SmallMonster.SmallMonsterNameList;
import EntityController.Monster.SmallMonster.SmallMonsterQuantity;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;

public class MyGame extends MainController {
    private MyHero hero;
    private ArrayList<Monster> monster;
    private SmallMonsterNameList smallMonsterNames;
    private NormalMonsterNameList normalMonsterNames;
    private int floor;

    @Override
    protected void setup() {
        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter, batch);
        this.smallMonsterNames = new SmallMonsterNameList();
        this.normalMonsterNames = new NormalMonsterNameList();
        this.monster = new ArrayList<>();
        this.floor = 1;

        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println(
                    "Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
        camera.follow(hero);
        entityController.add(hero);
    }

    @Override
    protected void beginFrame() {

    }

    @Override
    protected void endFrame() {
        if (levelAPI.getCurrentLevel().isOnEndTile(hero)) {
            try {
                entityController.clear();
                entityController.add(hero);
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLevelLoad() {
        hero.setLevel(levelAPI.getCurrentLevel());
        initMonster();

    }

    /**
     * Startmethod to init Monster on leveload
     */
    private void initMonster() {
        this.monster.clear();
        createMonster();
        this.floor += 1;
    }

    /**
     * creates small and normal monster and ad them to the entity controller
     */
    private void createMonster() {
        for (int i = 0; i < new SmallMonsterQuantity(this.floor).getQuantity(); i++) {
            int ranMon = 0;
            Monster monster = MonsterFactory.monFac(painter, batch, this.smallMonsterNames.getMonsterList().get(ranMon), hero, floor);
            monster.setLevel(levelAPI.getCurrentLevel());
            entityController.add(monster);
            this.monster.add(monster);
        }
        for (int i = 0; i < new NormalMonsterQuantity(this.floor).getQuantity(); i++) {
            int ranMon = 0;
            Monster monster = MonsterFactory.monFac(painter, batch, this.normalMonsterNames.getMonsterList().get(ranMon), this.hero, this.floor);
            monster.setLevel(levelAPI.getCurrentLevel());
            entityController.add(monster);
            this.monster.add(monster);
        }

    }

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
