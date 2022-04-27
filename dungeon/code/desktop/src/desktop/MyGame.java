package desktop;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.Monster.NormalMonster.Chort;
import EntityController.Monster.NormalMonster.NormalMonsterNameList;
import EntityController.Monster.NormalMonster.NormalMonsterQuantity;
import EntityController.Monster.SmallMonster.Imp;
import EntityController.Monster.SmallMonster.SmallMonsterNameList;
import EntityController.Monster.SmallMonster.SmallMonsterQuantity;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.MovementBehaviour.SimpleMovementBehaviour.PatrolX;
import EntityController.MovementBehaviour.SimpleMovementBehaviour.PatrolY;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;

public class MyGame extends MainController {
    private MyHero hero;
    private SmallMonsterNameList smallMonsterNames;
    private NormalMonsterNameList normalMonsterNames;
    private ArrayList<Monster> smallMonsterList;
    private ArrayList<Monster> normalMonsterList;
    private int floor;

    @Override
    protected void setup() {
        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter, batch);     
        this.smallMonsterNames = new SmallMonsterNameList();
        this.normalMonsterNames = new NormalMonsterNameList();  
        this.smallMonsterList = new ArrayList<>();
        this.normalMonsterList = new ArrayList<>();
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

    private void initMonster() {
        this.smallMonsterList.clear();
        this.normalMonsterList.clear();
        initSmallMonster();
        initNormalMonster();
        System.out.println("Small:" + this.smallMonsterList.size() + "\nNormal:" + this.normalMonsterList.size() + "\n");
        this.floor += 1;
    }

    private void initSmallMonster(){
        for(int i = 0; i < new SmallMonsterQuantity(this.floor).getQuantity(); i++) {
            int ranMon = 0;
            this.smallMonsterList.add(monFac(this.smallMonsterNames.getMonsterList().get(ranMon)));
            entityController.add(this.smallMonsterList.get(i));
            this.smallMonsterList.get(i).setLevel(levelAPI.getCurrentLevel());
        }
    }

    private void initNormalMonster(){
        for(int i = 0; i < new NormalMonsterQuantity(this.floor).getQuantity(); i++) {
            int ranMon = 0;
            this.normalMonsterList.add(monFac(this.normalMonsterNames.getMonsterList().get(ranMon)));
            entityController.add(this.normalMonsterList.get(i));
            this.normalMonsterList.get(i).setLevel(levelAPI.getCurrentLevel());
        }
    }

    private Monster monFac(String monName) {
        IMovementBehaviour behaviour;
        Random random = new Random();
        int i = random.nextInt(2 + 1) + 1;
        if(i == 1){
            behaviour = new PatrolY();
        }else{
            behaviour = new PatrolX();
        }

        if(monName.equals("Imp")){
            return new Imp(painter, batch, this.hero, this.floor, behaviour);
        }
        if(monName.equals("Chort")){
            return new Chort(painter, batch, hero, this.floor, behaviour);
        }
        return null;
    }

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
