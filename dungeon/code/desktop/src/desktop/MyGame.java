package desktop;

import java.util.ArrayList;

import Entities.Chest.ChestFactory;
import Entities.FriendlyNPCs.FriendlyNPC;
import Entities.FriendlyNPCs.FriendlyNpcFactory;
import HUD.ExpBar;
import HUD.HealthBar;
import HUD.ManaBar;
import com.badlogic.gdx.Gdx;

import Entities.Items.Item;
import Entities.Items.ItemFactory;
import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Hero.Classes.Knight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterFactory;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import controller.MainController;
import Traps.TrapFactory;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import tools.Point;

public class MyGame extends MainController {
    public Hero hero;
    private FriendlyNPC npc;
    private int currentFloor;
    private ArrayList<Monster> monster;
    private ArrayList<Item> items;
    private ArrayList<Item> chests;
    private ArrayList<Item> traps;
    private ArrayList<FriendlyNPC> npcs;
    private Label levelHP, levelMANA, levelCounter, heroStats, heroLevel;

    @Override
    protected void setup() {

        this.hero = new Knight(painter, batch);
        this.currentFloor = 0;
        this.monster = new ArrayList<>();
        this.hero.getAttributes().setCurrentHP(20);
        this.hero.getAttributes().setCurrentMana(20);

        //spawns hero
        camera.follow(hero);
        entityController.add(hero);
        initHud();
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
        levelHP.setText(hero.getAttributes().getCurrentHP() + " / " + hero.getAttributes().getMaxHP());
        levelMANA.setText(hero.getAttributes().getCurrentMana() + " / " + hero.getAttributes().getMaxMana());
        levelCounter.setText("Floor " + currentFloor);
        heroStats.setText("AtkP: " + hero.getAttributes().getAttackPower() + "\nDefP: " + hero.getAttributes().getDefensePower() +
            "\nEva: " + hero.getAttributes().getEvasion() + "\nAccu: " + hero.getAttributes().getAccuracy());
        heroLevel.setText("Level: " + hero.getAttributes().getLevel());
    }

    public void initHud() {
        levelHP = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 40, 40, 60, 440);
        levelMANA = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 40, 40, 60, 400);
        levelCounter = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 40, 40, 40, 10, 0);
        heroStats = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 20, 20, 500, 420);
        heroLevel = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 20, 20, 270, 30);
        hudController.add(new HealthBar(hudPainter, hudBatch, new Point(0, -330)));
        hudController.add(new ManaBar(hudPainter, hudBatch, new Point(0, -290)));
        hudController.add(new ExpBar(hudPainter, hudBatch, new Point(200, 90)));
    }

    @Override
    protected void endFrame() {
        if (levelAPI.getCurrentLevel().isOnEndTile(hero)) {
            try {
                for (Monster monster : this.monster) {
                    if (entityController.contains(monster)) {
                        entityController.remove(monster);
                    }
                }
                for (Item item : this.items) {
                    if (item.getIsOnFloor()) {
                        entityController.remove(item);
                    }
                }

                for (Item traps : this.traps) {
                    entityController.remove(traps);
                }
                for (FriendlyNPC npc : this.npcs) {
                    entityController.remove(npc);
                }

                if (currentFloor % 5 == 0) {
                    for (Item chests : this.chests) {
                        if (chests.getIsOnFloor() || !chests.getPickUp()) {
                            entityController.remove(chests);
                        }
                    }
                    this.chests.clear();
                }
                this.monster.clear();
                this.items.clear();
                this.traps.clear();
                this.npcs.clear();
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLevelLoad() {
        currentFloor++;
        hero.setLevel(levelAPI.getCurrentLevel());
        initMons();
        initItems();
        initChest();
        initTraps();
        initNpc();
    }

    private void initMons() {
        this.monster = MonsterFactory.monFac(painter, batch, this.hero, this.currentFloor);
        for (Monster monster : this.monster) {
            monster.setLevel(levelAPI.getCurrentLevel());
            entityController.add(monster);
        }
    }

    private void initTraps() {
        this.traps = TrapFactory.trapFac(painter, batch);
        for (Item traps : this.traps) {
            traps.setLevel(levelAPI.getCurrentLevel());
            entityController.add(traps);
        }
        this.hero.setFloorTraps(traps);
    }

    private void initItems() {
        this.items = ItemFactory.itemFac(painter, batch);
        for (Item item : this.items) {
            item.setLevel(levelAPI.getCurrentLevel());
            entityController.add(item);
        }
        this.hero.setFloorItems(items);
    }

    private void initChest() {
        if (currentFloor % 5 == 0) {
            this.chests = ChestFactory.chestFac(painter, batch);
            chests.get(0).setLevel(levelAPI.getCurrentLevel());
            chests.get(1).setLevel(chests.get(0));
            entityController.add(chests.get(0));
            entityController.add(chests.get(1));
            chests.get(1).setIsOnFloor(false);
            this.hero.setFloorChests(chests);
        }
    }

    private void initNpc(){
        this.npcs = FriendlyNpcFactory.npcFac(painter,batch);
        for(FriendlyNPC npc : npcs){
            npc.setLevel(levelAPI.getCurrentLevel());
            entityController.add(npc);
        }
        this.hero.setNpcs(npcs);
    }


    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
