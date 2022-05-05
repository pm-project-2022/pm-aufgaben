package desktop;

import Entities.Chest.ChestFactory;
import Entities.FriendlyNPCs.FriendlyNPC;
import Entities.FriendlyNPCs.FriendlyNpcFactory;
import Entities.Items.Item;
import Entities.Items.ItemFactory;
import Entities.Moveable.Hero.Classes.Hunter;
import Entities.Moveable.Hero.Classes.Knight;
import Entities.Moveable.Hero.Classes.Wizard;
import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterFactory;
import Gui.Gui;
import HUD.ExpBar;
import HUD.HealthBar;
import HUD.ManaBar;
import Traps.TrapFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import tools.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyGame extends MainController {
    public Hero hero;
    private int currentFloor;
    private ArrayList<Monster> monster;
    private ArrayList<Item> items;
    private ArrayList<Item> chests;
    private ArrayList<Item> traps;
    private ArrayList<FriendlyNPC> npcs;
    private Label levelHP, levelMANA, levelCounter, heroStats, heroLevel;
    private Gui gui;

    @Override
    protected void setup() {

        //Initiates Gui and waits on input
        try {
            gui = new Gui();
            while(!gui.getBool()){
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Initiates chosen hero
        if (gui.getChara() == 1) {
            hero = new Knight(painter, batch);
        }
        if (gui.getChara() == 2) {
            hero = new Wizard(painter, batch);
        }
        if (gui.getChara() == 3) {
            hero = new Hunter(painter, batch);
        }
        setup2();
    }

    private void setup2() {

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
            System.out.println("Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
    }

    /**
     * sets text for hud
     */
    @Override
    protected void beginFrame() {
        levelHP.setText(hero.getAttributes().getCurrentHP() + " / " + hero.getAttributes().getMaxHP());
        levelMANA.setText(hero.getAttributes().getCurrentMana() + " / " + hero.getAttributes().getMaxMana());
        levelCounter.setText("Floor " + currentFloor);
        heroStats.setText("AtkP: " + hero.getAttributes().getAttackPower() + "\nDefP: " + hero.getAttributes().getDefensePower() +
            "\nEva: " + hero.getAttributes().getEvasion() + "\nAccu: " + hero.getAttributes().getAccuracy() + "\nExp: " + hero.getAttributes().getExp() + " / " + hero.getAttributes().getExpForLvlUp());
        heroLevel.setText("Level: " + hero.getAttributes().getLevel());
    }

    /**
     * initiates hud elements
     */
    public void initHud() {
        levelHP = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 40, 40, 60, 440);
        levelMANA = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 40, 40, 60, 400);
        levelCounter = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 40, 40, 40, 10, 0);
        heroStats = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 20, 20, 480, 410);
        heroLevel = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 20, 20, 270, 30);
        hudController.add(new HealthBar(hudPainter, hudBatch, new Point(0, -330)));
        hudController.add(new ManaBar(hudPainter, hudBatch, new Point(0, -290)));
        hudController.add(new ExpBar(hudPainter, hudBatch, new Point(200, 90)));
    }

    /**
     * clears stage on endframe
     */
    @Override
    protected void endFrame() {
        if (levelAPI.getCurrentLevel().isOnEndTile(hero)) {
            try {
                for (Monster monster : this.monster) {
                    entityController.remove(monster);
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

    /**
     * initiates all entities
     */
    @Override
    public void onLevelLoad() {
        currentFloor++;
        hero.setLevel(levelAPI.getCurrentLevel());
        initTraps();
        initMons();
        initItems();
        initChest();
        initNpc();
    }

    /**
     * spawns monster
     */
    private void initMons() {
        this.monster = MonsterFactory.monFac(painter, batch, this.hero, this.currentFloor);
        for (Monster monster : this.monster) {
            monster.setLevel(levelAPI.getCurrentLevel());
            entityController.add(monster);
        }
    }

    /**
     * spawns traps
     */
    private void initTraps() {
        this.traps = TrapFactory.trapFac(painter, batch);
        for (Item traps : this.traps) {
            traps.setLevel(levelAPI.getCurrentLevel());
            entityController.add(traps);
        }
        this.hero.setFloorTraps(traps);
    }

    /**
     * spawns items
     */
    private void initItems() {
        this.items = ItemFactory.itemFac(painter, batch);
        for (Item item : this.items) {
            item.setLevel(levelAPI.getCurrentLevel());
            entityController.add(item);
        }
        this.hero.setFloorItems(items);
    }

    /**
     * spawns chests
     */
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

    /**
     * spawns npc
     */
    private void initNpc() {
        this.npcs = FriendlyNpcFactory.npcFac(painter, batch);
        for (FriendlyNPC npc : npcs) {
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
