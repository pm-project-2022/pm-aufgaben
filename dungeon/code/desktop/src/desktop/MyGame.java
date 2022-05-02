package desktop;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.Monster.MonsterFactory;
import EntityController.Monster.NormalMonster.NormalMonsterNameList;
import EntityController.Monster.NormalMonster.NormalMonsterQuantity;
import EntityController.Monster.SmallMonster.SmallMonsterNameList;
import EntityController.Monster.SmallMonster.SmallMonsterQuantity;
import HUD.Ability;
import HUD.AbilityBox;
import HUD.Background;
import HUD.FullHeart;
import Inventar.ColumnFormatter;
import Inventar.Inventory;
import Items.HealthPotion;
import Items.Item;
import Items.ManaPotion;
import Items.SawSword;
import Items.SpeedOrb;
import Items.StrengthOrb;
import Items.Sword;
import Items.DungeonLoot.DungeonLoot;
import Items.DungeonLoot.Speedorb.LootSpeedOrb;
import Items.DungeonLoot.Speedorb.SpeedOrbList;
import Items.DungeonLoot.Speedorb.SpeedOrbQuantity;
import Items.DungeonLoot.StrengthOrb.LootStrengthOrb;
import Items.DungeonLoot.StrengthOrb.StrengthOrbList;
import Items.DungeonLoot.StrengthOrb.StrengthOrbQuantity;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import tools.Point;

public class MyGame extends MainController {

    // hero
    public static MyHero hero;

    // manages monster and monsternames
    private ArrayList<Monster> monster;
    private SmallMonsterNameList smallMonsterNames;
    private NormalMonsterNameList normalMonsterNames;

    // manages item names
    private SpeedOrbList speedOrbNames;
    private StrengthOrbList strengthOrbNames;

    // manages list of loot
    private ArrayList<DungeonLoot> speedOrbList;
    private ArrayList<DungeonLoot> strengthOrbList;

    // floor/levelcounter
    private int floor;

    // manages hud components
    private Label levelLabel, healthPoints;

    // manages inventory
    private Inventory inventory;

    // manages item variables
    private Sword s1;
    private SawSword ss1;
    private HealthPotion hp1;
    private ManaPotion mp1;
    private SpeedOrb sbL;
    private StrengthOrb dbO;

    // manages logger
    private Logger log;

    @Override
    protected void setup() {
        levelAPI.setGenerator(new LevelLoader());

        // initiates hero
        hero = new MyHero(painter, batch);

        // initiates monstername list
        this.smallMonsterNames = new SmallMonsterNameList();
        this.normalMonsterNames = new NormalMonsterNameList();

        // initiates monster list
        this.monster = new ArrayList<>();

        // initiates itemname list
        this.speedOrbNames = new SpeedOrbList();
        this.strengthOrbNames = new StrengthOrbList();

        // initiates item list
        this.speedOrbList = new ArrayList<>();
        this.strengthOrbList = new ArrayList<>();

        // initial floor at floorlevel 1
        this.floor = 1;

        // initiates inventory
        this.inventory = new Inventory();

        // initiates items
        this.s1 = new Sword(painter, batch);
        this.ss1 = new SawSword(painter, batch);
        this.hp1 = new HealthPotion(painter, batch);
        this.mp1 = new ManaPotion(painter, batch);
        this.sbL = new SpeedOrb(painter, batch);
        this.dbO = new StrengthOrb(painter, batch);

        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println(
                    "Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
        // calls logger, visible and hud
        initLogger();
        initVisible();
        initHud();

        // spawns hero
        camera.follow(hero);
        entityController.add(hero);

        // sets default item
        inventory.setActiveItem(s1);
        entityController.add(s1);
        inventory.add(s1);
        inventory.equipped(1);
    }

    /**
     * sets created items not visible on screen
     */
    private void initVisible() {
        s1.isVisible = false;
        ss1.isVisible = false;
        hp1.isVisible = false;
        mp1.isVisible = false;
        sbL.isVisible = false;
        dbO.isVisible = false;
    }

    @Override
    protected void beginFrame() {
        // adds to inventory
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            entityController.add(ss1);
            inventory.add(ss1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            entityController.add(hp1);
            inventory.add(hp1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            entityController.add(mp1);
            inventory.add(mp1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            entityController.add(sbL);
            inventory.add(sbL);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            entityController.add(dbO);
            inventory.add(dbO);
        }

        // equips from inventory
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            inventory.getActiveItem().isVisible = false;
            inventory.equipped(1);
            entityController.add(inventory.getActiveItem());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            inventory.getActiveItem().isVisible = false;
            inventory.equipped(2);
            entityController.add(inventory.getActiveItem());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            inventory.getActiveItem().isVisible = false;
            inventory.equipped(3);
            entityController.add(inventory.getActiveItem());
        }
        // removes items
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            removeItem();
        }
        // shows inventory
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            inventory.showInventory();
        }
        // picks up items
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            onPickUp();
        }
        // uses items
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if (inventory.getActiveItem().getName().equals("Heiltrank")) {
                onUse(inventory.getActiveItem());
            }
            if (inventory.getActiveItem().getName().equals("Manatrank")) {
                onUse(inventory.getActiveItem());
            }
            if (inventory.getActiveItem().getName().equals("SpeedbuffOrb")) {
                onUse(inventory.getActiveItem());
            }
            if (inventory.getActiveItem().getName().equals("DamagebuffOrb")) {
                onUse(inventory.getActiveItem());
            }
        }

        inventory.getActiveItem().isVisible = true;
    }

    /**
     * picks up items and adds to inventory
     */
    private void onPickUp() {
        if (inventory.isFull()) {
            log.warning("Inventar ist voll!");
            return;
        }
        if (levelAPI.getCurrentLevel().getTileAt(strengthOrbList.get(0).getPosition().toCoordinate()) == hero.getLevel()
                .getTileAt(hero.getPosition().toCoordinate())) {
            strengthOrbList.get(0).isVisible = false;
            StrengthOrb newOrb = new StrengthOrb(painter, batch);
            inventory.add(newOrb);
        } else if (levelAPI.getCurrentLevel().getTileAt(speedOrbList.get(0).getPosition().toCoordinate()) == hero
                .getLevel().getTileAt(hero.getPosition().toCoordinate())) {
            speedOrbList.get(0).isVisible = false;
            SpeedOrb newOrb = new SpeedOrb(painter, batch);
            inventory.add(newOrb);
        }

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

        //adds components to hud
        if (this.floor == 1) {
            levelLabel = hudController.drawText("Level  " + this.floor, "ttf/8-bit Arcade In.ttf", Color.WHITE, 50, 50, 50, 30, 15);
            healthPoints = hudController.drawText("CharLevel " + hero.getStats().getExp(), "ttf/8-bit Arcade In.ttf", Color.WHITE, 50, 50, 50, 320, 15);
        } else {
            //increases counter and hero xp
            levelLabel.setText("Level " + this.floor );
            healthPoints.setText("CharLevel " + hero.getStats().getExp());
        }

        //initiates monster and items
        initMonster();
        initItems();
    }


    /**
     * removes item from screen
     */
    public void removeItem() {
        inventory.removeItem(inventory.getActiveItem());
        entityController.remove(inventory.getActiveItem());
        inventory.getActiveItem().isVisible = false;
        inventory.equipped(1);
        entityController.add(inventory.getActiveItem());
    }

    /**
     * uses items
     *
     * @param item item that is used by hero
     */
    private void onUse(Item item) {
        if (item.getName().equals("Heiltrank")) {
            hero.getStats().setHealhtPoints(hero.getStats().getHealhtPoints() + 100000);
            removeItem();
            log.info("Health: " + hero.getStats().getHealhtPoints());
        } else if (item.getName().equals("Manatrank")) {
            hero.getStats().setManaPoints(hero.getStats().getManaPoints() + 100000);;
            removeItem();
            log.info("Mana: " + hero.getStats().getManaPoints());
        } else if (item.getName().equals("SpeedbuffOrb")) {
            hero.getStats().setMovementspeed(hero.getStats().getMovementspeed() + 0.02f);;
            removeItem();
            log.info("Speed: " + hero.getStats().getMovementspeed());
        } else if (item.getName().equals("DamagebuffOrb")) {
            hero.getStats().setStrength(hero.getStats().getStrength() + 1);
            removeItem();
            log.info("Strength: " + hero.getStats().getStrength());
        }

    }

    /**
     * initiates the hud
     */
    private void initHud() {
        hudController.add(new Background(hudPainter, hudBatch, new Point(0f, 0f)));
        hudController.add(new FullHeart(hudPainter, hudBatch, new Point(0f, 0f)));
        hudController.add(new FullHeart(hudPainter, hudBatch, new Point(25f, 0f)));
        hudController.add(new FullHeart(hudPainter, hudBatch, new Point(50f, 0f)));
        hudController.add(new FullHeart(hudPainter, hudBatch, new Point(75f, 0f)));
        hudController.add(new FullHeart(hudPainter, hudBatch, new Point(100f, 0f)));
        hudController.add(new AbilityBox(hudPainter, hudBatch, new Point(30, 330)));
        hudController.add(new Ability(hudPainter, hudBatch, new Point(32, 332)));
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
            Monster monster = MonsterFactory.monFac(painter, batch, this.smallMonsterNames.getMonsterList().get(ranMon),
                    hero, floor);
            monster.setLevel(levelAPI.getCurrentLevel());
            entityController.add(monster);
            this.monster.add(monster);
        }
        for (int i = 0; i < new NormalMonsterQuantity(this.floor).getQuantity(); i++) {
            int ranMon = 0;
            Monster monster = MonsterFactory.monFac(painter, batch,
                    this.normalMonsterNames.getMonsterList().get(ranMon), this.hero, this.floor);
            monster.setLevel(levelAPI.getCurrentLevel());
            entityController.add(monster);
            this.monster.add(monster);
        }

    }

    /**
     * initiates items
     */
    private void initItems() {
        initSpeedOrb();
        initStrengthOrb();
    }

    /**
     * creates item speedorb
     */
    private void initSpeedOrb() {
        for (int i = 0; i < new SpeedOrbQuantity().getQuantity(); i++) {
            int item = 0;
            this.speedOrbList.add(itemFac(this.speedOrbNames.getItemList().get(item)));
            entityController.add(this.speedOrbList.get(i));
            this.speedOrbList.get(i).setLevel(levelAPI.getCurrentLevel());
            speedOrbList.get(0).isVisible = true;
        }
    }

    /**
     * creates item strengthorb
     */
    private void initStrengthOrb() {
        for (int i = 0; i < new StrengthOrbQuantity().getQuantity(); i++) {
            int item = 0;
            this.strengthOrbList.add(itemFac(this.strengthOrbNames.getItemList().get(item)));
            entityController.add(this.strengthOrbList.get(i));
            this.strengthOrbList.get(i).setLevel(levelAPI.getCurrentLevel());
            strengthOrbList.get(0).isVisible = true;
        }
    }

    /**
     * spawns items in dungeon
     *
     * @param itemName name of item
     * @return returns spawned item
     */
    private DungeonLoot itemFac(String itemName) {
        if (itemName.equals("Speedorb")) {
            return new LootSpeedOrb(painter, batch);
        }
        if (itemName.equals("Strengthorb")) {
            return new LootStrengthOrb(painter, batch);
        }
        return null;
    }

    /**
     * initiates the logger
     */
    private void initLogger() {
        log = Logger.getLogger("MyGame");
        log.setUseParentHandlers(false);
        log.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
    }

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
