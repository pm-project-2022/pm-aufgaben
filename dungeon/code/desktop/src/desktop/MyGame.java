package desktop;

import Entities.Chest.ChestFactory;
import Entities.Fight.Ranged.HunterRanged;
import Entities.Fight.Ranged.KnightRanged;
import Entities.Fight.Ranged.RangedFight;
import Entities.Fight.Ranged.WizzardRanged;
import Entities.FriendlyNPCs.FriendlyNPC;
import Entities.FriendlyNPCs.FriendlyNpcFactory;
import Entities.FriendlyNPCs.MinigameNPC;
import Entities.FriendlyNPCs.MinigameNpcFactory;
import Entities.Items.*;
import Entities.Moveable.Hero.Classes.Hunter;
import Entities.Moveable.Hero.Classes.Knight;
import Entities.Moveable.Hero.Classes.Wizard;
import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterFactory;
import Gui.mainGUI.mainGui;
import HUD.LvlBar;
import HUD.ExpBar;
import HUD.HealthBar;
import HUD.ManaBar;
import Shop.ShopGui;
import Traps.TrapFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import tools.Point;

import java.util.ArrayList;

public class MyGame extends MainController {
    public Hero hero;
    private RangedFight rangedFight;
    private int currentFloor;
    private ArrayList<Monster> monster;
    private ArrayList<Item> items;
    private ArrayList<Item> chests;
    private ArrayList<Item> traps;
    private ArrayList<FriendlyNPC> npcs;
    private ArrayList<MinigameNPC> minigameNPCS;
    private mainGui gui;
    private ShopGui shopGui;
    private Label levelHP, levelMANA, levelCounter, heroStats, heroLevel, heroXp, deathScreen,
        money, skill1, skill2, skill3;

    public static Sound death, newLevel, walking, itemPickup, hit, lvlUp,
        talkNpc, stepTraps, useItem, openChest, equipItem, dropItem, backgroundMusic;

    @Override
    protected void setup() {
        initSounds();
        backgroundMusic.loop(0.1f);
        //Initiates Gui and waits on input
        try {
            gui = new mainGui();
            while (!gui.getBool()) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Initiates chosen hero
        if (gui.getChara() == 1) {
            hero = new Knight(painter, batch);
            this.rangedFight = new KnightRanged(painter, batch, hero);
            this.hero.setRangedFight(this.rangedFight);
        }
        if (gui.getChara() == 2) {
            hero = new Wizard(painter, batch);
            this.rangedFight = new WizzardRanged(painter, batch, hero);
            this.hero.setRangedFight(this.rangedFight);
        }
        if (gui.getChara() == 3) {
            hero = new Hunter(painter, batch);
            this.rangedFight = new HunterRanged(painter, batch, hero);
            this.hero.setRangedFight(this.rangedFight);
        }
        setup2();
    }

    private void setup2() {

        this.currentFloor = 0;
        this.monster = new ArrayList<>();


        // spawns hero
        camera.follow(hero);
        entityController.add(hero);
        entityController.add(rangedFight);
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
        if (!this.hero.getHeroDead()) {
            levelHP.setText(hero.getAttributes().getCurrentHP() + " / " + hero.getAttributes().getMaxHP());
            levelMANA.setText(hero.getAttributes().getCurrentMana() + " / " + hero.getAttributes().getAuraMana());
            levelCounter.setText("Floor " + currentFloor);
            heroStats.setText("AtkP: " + hero.getAttributes().getAuraAttackPower() + "\nDefP: "
                + hero.getAttributes().getAuraDefensePower() +
                "\nEva: " + hero.getAttributes().getAuraEvasion() + "\nAccu: " + hero.getAttributes().getAccuracy());
            heroLevel.setText("Level: " + hero.getAttributes().getLevel());
            this.heroXp.setText(hero.getAttributes().getExp() + " / " + hero.getAttributes().getExpForLvlUp());
            deathScreen.setText("");
            money.setText("Money: " + hero.getMoney());
            manageSkills();
        } else {
            levelHP.setText(hero.getAttributes().getCurrentHP() + " / " + hero.getAttributes().getMaxHP());
            deathScreen.setText("Gamer Over\n`r` to restart");
            if (Gdx.input.isKeyPressed(Input.Keys.R)) {
                restartGame();
            }
        }
        talkToShopNpc();
        updateShop();
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

                for (MinigameNPC npc : this.minigameNPCS) {
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
                newLevel.play(0.4f);
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
        initMinigameNpc();
        this.rangedFight.setLevel(this.hero.getCurrentFloor());
        this.hero.setMonster(monster);
    }

    /**
     * opens the shop
     */
    private void talkToShopNpc() {
        for (MinigameNPC npc : minigameNPCS) {
            if (hero.getCurrentFloor().getTileAt(hero.getPosition().toCoordinate()) == npc.getCurrentFloor()
                .getTileAt(npc.getPosition().toCoordinate())) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                    shopGui = new ShopGui(hero, painter, batch);
                    //new DialogGui().initGui();
                }
            }
        }
    }

    /**
     * updates items bought from shop
     */
    private void updateShop(){
        try{
            if(shopGui.getBoughtItem()!=null){
                shopGui.getBoughtItem().setPickUp(true);
                shopGui.getBoughtItem().setIsOnFloor(false);
                shopGui.getBoughtItem().setPosition(hero.getPosition());
                entityController.add(shopGui.getBoughtItem());
                shopGui.setBoughtItem(null);
            }
        }
        catch(NullPointerException ignored){

        }

    }

    /**
     * gibt im hud auskunft darüber ob ein skill gelernt wurde, einsatzbereit oder auf cooldown ist.
     */
    private void manageSkills() {
        skill1();
        skill2();
        skill3();
    }

    private void skill1() {
        this.skill1.setText("Skill 1: " + this.hero.getFightStatus());
    }

    private void skill2() {
        this.skill2.setText("Skill 2:" + this.hero.getConvertStatus());
    }

    private void skill3() {
        this.skill3.setText("Skill 3: " + this.hero.getAuraStatus());
    }

    /**
     * initiates sounds
     */
    private void initSounds() {
        death = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_deathscream_robot1.wav"));
        newLevel = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_sounds_interaction1.wav"));
        walking = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_movement_footsteps1a.wav"));
        itemPickup = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_sounds_interaction19.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_sounds_impact1.wav"));
        lvlUp = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_sounds_powerup2.wav"));
        talkNpc = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_menu_move4.wav"));
        stepTraps = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_sounds_error7.wav"));
        useItem = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_sounds_interaction11.wav"));
        openChest = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_coin_cluster1.wav"));
        equipItem = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_wpn_dagger.wav"));
        dropItem = Gdx.audio.newSound(Gdx.files.internal("Sounds/sfx_movement_ladder1b.wav"));
        backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("Sounds/looperman-l-2922083-0277368-thxrtx-loop-8bit.wav"));
    }

    /**
     * spawns monster
     */
    private void initMons() {
        this.monster = MonsterFactory.monFac(painter, batch, this.hero, this.currentFloor);
        for (Monster monster : this.monster) {
            monster.setMonster(this.monster);
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

    private void initMinigameNpc() {
        this.minigameNPCS = MinigameNpcFactory.npcFac(painter, batch);
        for (MinigameNPC npc : minigameNPCS) {
            npc.setLevel(levelAPI.getCurrentLevel());
            entityController.add(npc);
        }
        this.hero.setMinigameNpcs(minigameNPCS);
    }

    /**
     * Initiiert das hud
     */
    private void initHud() {
        money = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.YELLOW, 20, 40, 40, 500, 300);
        levelHP = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 40, 40, 60, 440);
        levelMANA = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 40, 40, 60, 400);
        levelCounter = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 40, 40, 40, 10, 0);
        heroStats = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 20, 20, 500, 420);
        heroLevel = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 20, 20, 270, 30);
        this.deathScreen = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.RED, 40, 200, 200, 170, 150);
        this.heroXp = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 20, 20, 20, 310, 0);
        this.heroXp.setAlignment(1);
        this.skill1 = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 15, 20, 20, 425, 50);
        this.skill2 = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 15, 20, 20, 425, 25);
        this.skill3 = hudController.drawText("", "ttf/DiaryOfAn8BitMage-lYDD.ttf", Color.WHITE, 15, 20, 20, 425, 0);
        hudController.add(new HealthBar(hudPainter, hudBatch, new Point(0, -330)));
        hudController.add(new ManaBar(hudPainter, hudBatch, new Point(0, -290)));
        hudController.add(new ExpBar(hudPainter, hudBatch, new Point(200, 120)));
        hudController.add(new LvlBar(hudPainter, hudBatch, new Point(200, 90)));
    }

    /**
     * startet das spiel nach dem tod neu
     */
    private void restartGame() {

        if (gui.getChara() == 1) {
            hero = new Knight(painter, batch);
        }
        if (gui.getChara() == 2) {
            hero = new Wizard(painter, batch);
        }
        if (gui.getChara() == 3) {
            hero = new Hunter(painter, batch);
        }
        this.currentFloor = 0;
        this.monster.clear();
        this.items.clear();
        this.traps.clear();
        this.npcs.clear();
        this.entityController.clear();
        camera.follow(hero);
        entityController.add(hero);
        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
