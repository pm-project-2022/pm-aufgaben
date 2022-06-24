package Entities.Moveable.Hero;

import Dialog.DialogGui;
import Inventory.InventoryGui;
import Entities.Fight.Ranged.RangedFight;
import Entities.FriendlyNPCs.FriendlyNPC;
import Entities.FriendlyNPCs.MinigameNPC;
import Entities.Items.Item;
import Entities.Moveable.Moveable;
import Entities.Moveable.Monster.Monster;
import Helper.PointBooleanTransmitter;
import Inventory.Inventory;
import Minigames.Hangman;
import Minigames.RPS;
import Minigames.TicTacToeMain;
import Shop.ShopGui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.EntityController;
import desktop.MyGame;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.io.IOException;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import Logger.ColumnFormatter;
import Skills.BasicSkill;

/**
 * Verwaltet die allgemeinen Attribute und Eigenschaften des Helds
 */

public class Hero extends Moveable {
    // blickrichtung des helds
    protected boolean viewDirection;

    // statsu ob der held lebt oder tot ist
    protected boolean heroDead;

    // listen mit entitys
    protected ArrayList<Item> floorItems;
    protected ArrayList<Item> chests;
    protected ArrayList<Item> traps;
    protected ArrayList<FriendlyNPC> npcs;
    protected ArrayList<MinigameNPC> minigameNPCS;
    protected ArrayList<Monster> monster;

    // inventar
    protected Inventory inventory;

    // Skills
    protected RangedFight rangedFight;
    protected BasicSkill aura;
    protected BasicSkill convert;
    private String fightStatus;
    private String auraStatus;
    private String convertStatus;
    private int convertCooldown = 0;
    private int convertDuration = 0;
    // Logger
    protected Logger log;
    protected String name;

    protected TicTacToeMain t;
    protected Hangman h;
    protected RPS rps;
    // counter
    protected int walkingCount, trapCount = 0;

    protected int money;

    protected InventoryGui inventoryGui;
    public Hero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.viewDirection = true;
        this.inventory = new Inventory();
        this.heroDead = false;
        this.floorItems = new ArrayList<>();
        this.chests = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.money = 0;
        this.t = new TicTacToeMain();
        this.rps = new RPS();
        this.h = new Hangman();
        this.inventoryGui = new InventoryGui();
        this.fightStatus = "Not enough mana";
        initLogger();
    }

    /**
     * initiiert den logger
     */
    private void initLogger() {
        log = Logger.getLogger("Hero");
        log.setUseParentHandlers(false);
        log.setLevel(java.util.logging.Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new ColumnFormatter());
        ch.setLevel(java.util.logging.Level.ALL);
        log.addHandler(ch);
    }

    @Override
    public void update() {
        if (!this.heroDead) {
            animations(movementUpdate(movementKeyPressed(), this.currentPosition));
            pickUpItem();
            updateInventoryItemPosition();
            equipItem();
            itemViewDirection();
            dropItem();
            consumeItem();
            showInventory();
            openChest();
            stepOnTrap();
            talkToNpc();
            updateDead();
            manageSkills();
            updateStats();
            //talkToMinigameNpc();
            updateMinigame();
        }
    }

    /**
     * manages and sets animations
     */
    private void animations(boolean keyPressed) {
        if (keyPressed) {
            if (viewDirection) {
                this.activeAnimation = this.runAnimation;
            } else {
                this.activeAnimation = this.runMirroredAnimation;
            }
        } else {
            if (this.viewDirection) {
                this.activeAnimation = this.idleAnimation;
            } else {
                this.activeAnimation = this.idleMirroredAnimation;
            }
        }
    }

    /**
     * checks whether the button has been pressed or not.
     *
     * @return true if a button was pressed, false if not
     */
    private boolean movementKeyPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)
            || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ändert die position des heldens nach erfolgreicher tasteneingabe
     *
     * @param keyPressed      taste wurde gedrückt
     * @param currentPosition aktuelle position des heldens
     * @return true wenn sich der held erfolgreich bewegen konnte, ansonsten false
     */
    private boolean movementUpdate(boolean keyPressed, Point currentPosition) {
        Point newPosition = new Point(currentPosition);

        if (keyPressed) {
            walkingCount++;
            if (walkingCount % 10 == 0) {
                MyGame.walking.play(0.05f);
            }
            // Wenn die Taste W gedrückt ist, bewege dich nach oben
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                newPosition.y += this.attributes.getMovementSpeed();
            }
            // Wenn die Taste S gedrückt ist, bewege dich nach unten
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                newPosition.y -= this.attributes.getMovementSpeed();
            }
            // Wenn die Taste D gedrückt ist, bewege dich nach rechts
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                this.viewDirection = true;
                newPosition.x += this.attributes.getMovementSpeed();
            }
            // Wenn die Taste A gedrückt ist, bewege dich nach links
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                this.viewDirection = false;
                newPosition.x -= this.attributes.getMovementSpeed();
            }
            if (this.currentFloor.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.currentPosition = newPosition;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * checkt ob der charakter ein level aufsteigen kann oder nicht
     *
     * @return true, wenn ja, false wenn nein
     */
    private boolean updateExp() {
        if (this.attributes.getExp() >= this.attributes.getExpForLvlUp()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * updates die stats nach einem level up
     */
    private void updateStats() {
        if (updateExp()) {
            this.attributes.updateHeroLevelAndStats();
            if (this.aura.getAuraAttributes().getActive()) {
                this.aura.activateAura(this);
            }
        }
    }

    /**
     * steht der held auf einem item, kann man mit dieser funktionen das item aufheben
     */
    private void pickUpItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            for (Item item : this.floorItems) {
                if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == item.getCurrentFloor()
                    .getTileAt(item.getPosition().toCoordinate()) && item.getIsOnFloor()) {
                    if (this.inventory.addItem(item)) {
                        MyGame.itemPickup.play(0.2f);
                        item.setIsOnFloor(false);
                        item.setPickUp(true);
                        item.setPosition(this.currentPosition);
                    }

                }
            }
        }
    }

    /**
     * updated die position der items im inventar
     */
    private void updateInventoryItemPosition() {
        if (this.inventory.isEmpty()) {
        } else {
            for (int i = 0; i < this.inventory.getInventory().length; i++) {
                if (this.inventory.getInventory()[i] != null) {
                    this.inventory.getInventory()[i].setPosition(this.currentPosition);
                }
            }
        }
    }

    /**
     * rüstet einen gegenstand aus
     */
    private void equipItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            this.inventory.equipItem(0);
            MyGame.equipItem.play(0.1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            this.inventory.equipItem(1);
            MyGame.equipItem.play(0.1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            this.inventory.equipItem(2);
            MyGame.equipItem.play(0.1f);
        }

    }

    /**
     * verwartet die animationen des items
     */
    private void itemViewDirection() {
        for (Item item : this.inventory.getInventory()) {
            if (item != null && item.isEquipped()) {
                item.setViewDirection(new PointBooleanTransmitter(this.viewDirection, this.currentPosition));
            }
        }
    }

    /**
     * droppt ein items aus dem inventar
     */
    private void dropItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            this.inventory.dropItem();
            MyGame.dropItem.play(0.1f);
        }

    }

    /**
     * benutzt ein item aus dem inventar
     */
    private void consumeItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            this.inventory.consume(this);
            MyGame.useItem.play(0.1f);
        }
    }

    /**
     * gibt das inventar auf der konsole aus
     */
    private void showInventory() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            if(!inventoryGui.getVisibility()){
                inventoryGui.initGui(this);
                inventoryGui.frame.setVisible(true);
                inventoryGui.setVisibility(true);
            }
            else{
                inventoryGui.frame.setVisible(false);
                inventoryGui.setVisibility(false);
            }
        }
    }

    /**
     * öffnet eine kiste
     */
    private void openChest() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == chests.get(0).getCurrentFloor()
                .getTileAt(chests.get(0).getPosition().toCoordinate()) && chests.get(0).getIsOnFloor()) {
                MyGame.openChest.play(0.1f);
                chests.get(0).setRemoveOrConsume(true);
                chests.get(1).setIsOnFloor(true);
                this.floorItems.add(chests.get(1));
            }

        }
    }

    /**
     * wird ausgelöstet wenn der held sich auf einer falle befindet
     */
    private void stepOnTrap() {
        for (Item traps : traps) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == traps.getCurrentFloor()
                .getTileAt(traps.getPosition().toCoordinate())) {
                traps.setIsOnFloor(true);
                trapCount++;
                if (trapCount % 5 == 0) {
                    MyGame.stepTraps.play(0.1f);
                }

                attributes.setCurrentHP(attributes.getCurrentHP() - 1);
            }
        }
    }

    /**
     * lässt den helden mit einem npc interagieren
     */
    private void talkToNpc() {
        for (FriendlyNPC npc : npcs) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == npc.getCurrentFloor()
                .getTileAt(npc.getPosition().toCoordinate())) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                    MyGame.talkNpc.play(0.1f);
                    for (int i = 0; i < floorItems.size(); i++) {
                        if (!floorItems.get(i).getPickUp()) {
                            if (inventory.addItem(floorItems.get(i))) {
                                floorItems.get(i).setIsOnFloor(false);
                                floorItems.get(i).setPickUp(true);
                                floorItems.get(i).setPosition(this.currentPosition);
                                npc.setPosition(this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate()
                                    .toPoint());
                                log.info("Item gebracht");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    /*
    private void talkToMinigameNpc() {
        for (MinigameNPC npc : minigameNPCS) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == npc.getCurrentFloor()
                .getTileAt(npc.getPosition().toCoordinate())) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                    new ShopGui(this);
                    //new DialogGui().initGui();
                }
            }
        }
    }*/

    /*
    private void talkToMinigameNpc() {
        for (MinigameNPC npc : minigameNPCS) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == npc.getCurrentFloor()
                .getTileAt(npc.getPosition().toCoordinate())) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                    MyGame.talkNpc.play(0.1f);
                    if (getMoney() < 30) {
                        log.warning("Zu wenig Geld, mindestens 30 Muenzen!");
                        return;
                    }
                    minigame();
                }
            }
        }
    }
    */


    public void minigame() {
        Scanner sc = new Scanner(System.in);
        log.info("\n[1] TicTacToe\n[2] Schere Stein Papier\n[3] Hangman");
        try {
            switch (sc.nextInt()) {
                case 1:
                    setMoney(getMoney() - 30);
                    t.setVisible(true);
                    break;
                case 2:
                    setMoney(getMoney() - 30);
                    rps.initRPS();
                    break;
                case 3:
                    setMoney(getMoney() - 30);
                    h.initHangman();
                    break;
            }
        } catch (InputMismatchException e) {
            minigame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateMinigame() {
        if (t.getWon()) {
            t.setWon(false);
            t.setVisible(false);
            log.info("Spiel gewonnen");
            setMoney(getMoney() + 50);
        }
        if (t.getLost()) {
            t.setLost(false);
            t.setVisible(false);
            log.info("Spiel verloren");
        }
        if (rps.getWon()) {
            setMoney(getMoney() + 40);
            rps.setWon(false);
        }
        if (h.getWon()) {
            setMoney(getMoney() + 40);
            h.setWon(false);
        }
    }

    /**
     * checkt ob der held noch am leben ist
     */
    private void updateDead() {
        if (this.attributes.getCurrentHP() <= 0) {
            this.heroDead = true;
            MyGame.death.play(0.4f);
        }
    }

    /**
     * verwaltet die verschiedenen skills
     */
    private void manageSkills() {
        rangedAttack();
        auraSkill();
        convertSkill();
        if(convertCooldown != 0){
            convertCD();
        }
        if (this.name.equals("Hunter")) {
            hunterConvertDuration();
        }
    }

    /**
     * startet eine ranged attacke
     */
    private void rangedAttack() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            if (this.attributes.getCurrentMana() >= 5) {
                this.rangedFight.startRangedAttack();
                this.attributes.setCurrentMana(this.attributes.getCurrentMana() - 5);
            } else {

            }
        }
    }

    /**
     * aktiviert den aura skill des heldens
     */
    private void auraSkill() {
        if(this.attributes.getLevel() < this.aura.getAuraAttributes().getAuraLevel()){
            this.auraStatus = "not learned";
        }else{
            if(this.auraStatus != "aura inactive" && this.auraStatus != "aura active"){
                this.auraStatus = "aura inactive";
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
                if (this.attributes.getLevel() < this.aura.getAuraAttributes().getAuraLevel()) {
                } else if (!this.aura.getAuraAttributes().getActive()) {
                    this.aura.activateAura(this);
                    this.auraStatus = "aura active";
                    this.aura.getAuraAttributes().setActive(!this.aura.getAuraAttributes().getActive());
                    System.out.println();
                } else {
                    this.aura.deactiveAura(this);
                    this.auraStatus = "aura inactive";
                    this.aura.getAuraAttributes().setActive(!this.aura.getAuraAttributes().getActive());
                }
            }
        }

    }

    /**
     * setzt den rangedfight
     *
     * @param rangedFight
     */
    public void setRangedFight(RangedFight rangedFight) {
        this.rangedFight = rangedFight;
    }

    /**
     * aktiviert den convert skill
     */
    private void convertSkill() {
        if(this.attributes.getLevel() < this.convert.getConvertAttributes().getSkillLevel()){
            this.convertStatus = "not learned";
        }else{
            if(this.convertStatus == "not learned"){
                if(!this.convert.enoughRessources(this)){
                    this.convertStatus = "not enough res";
                }else{
                    this.convertStatus = "ready";
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                if (!this.convert.enoughRessources(this)) {
                    log.warning("Nicht genügend Leben oder Mana vorhanden.");
                } else if (this.convertCooldown > 0) {
                    log.warning("Skill ist auf Cooldown.");
                } else {
                    this.convert.convert(this);
                    this.convertStatus = "on cd";
                    this.convertCooldown = this.convert.getConvertAttributes().getCooldown();
                    if (this.name.equals("Hunter")) {
                        this.convertDuration = this.convert.getConvertAttributes().getDuration();
                    }

                }
            }

        }



    }

    /**
     * verwaltet den cooldown des convert skills
     */
    private void convertCD() {
        if (this.convertCooldown > 0) {
            this.convertCooldown--;
        }
        if(this.convertCooldown == 0){
            if(this.convert.enoughRessources(this)){
                this.convertStatus = "ready";
            }else{
                this.convertStatus = "not enough res";
            }
        }
    }

    /**
     * verwaltet die duration des hunter convert skills
     */
    private void hunterConvertDuration() {
        if (this.convertDuration > 0) {
            this.convertDuration--;
            if (this.convertDuration == 0) {
                this.attributes.setMovementSpeed(this.attributes.getMovementSpeed() - this.convert.getConvertAttributes().getMovementSpeed());
            }
        }
    }

    /**
     * setter für die position
     *
     * @param newPosition neue position
     */
    public void setPosition(Point newPosition) {
        this.currentPosition = newPosition;
    }

    /**
     * setzt das level und den helden zum levelload auf den start tile
     *
     * @param currentFloor aktuelles level
     */
    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getStartTile().getCoordinate().toPoint();
    }

    public void setFloorItems(ArrayList<Item> floorItems) {
        this.floorItems = floorItems;
    }

    /**
     * setzt die traps
     *
     * @param floorTraps traps
     */
    public void setFloorTraps(ArrayList<Item> floorTraps) {
        this.traps = floorTraps;
    }

    /**
     * setzt die npcs
     *
     * @param npcs npc
     */
    public void setNpcs(ArrayList<FriendlyNPC> npcs) {
        this.npcs = npcs;
    }

    /**
     * setzt die npcs
     *
     * @param npcs npc
     */
    public void setMinigameNpcs(ArrayList<MinigameNPC> npcs) {
        this.minigameNPCS = npcs;
    }

    /**
     * setzt die kisten
     *
     * @param chests kisten
     */
    public void setFloorChests(ArrayList<Item> chests) {
        this.chests = chests;
    }

    /**
     * gibt den namen des heldens zurück
     *
     * @return name des heldens
     */
    public String getName() {
        return name;
    }

    /**
     * ändert den status des heldens
     *
     * @param dead true wenn der held tot ist, false wenn nicht
     */
    public void setHeroDead(boolean dead) {
        this.heroDead = dead;
    }

    /**
     * gibt die traps zurück
     *
     * @return traps
     */
    public ArrayList<Item> getTraps() {
        return this.traps;
    }

    /**
     * gibt den heldenstatus zurück
     *
     * @return true wenn er tot ist, sonst false
     */
    public boolean getHeroDead() {
        return this.heroDead;
    }

    /**
     * gibt die blickrichtung des heldens zurück
     *
     * @return true wenn er nach rechts, false wenn er nach links guckt
     */
    public boolean getViewDirection() {
        return this.viewDirection;
    }

    /**
     * setzt die monster in dem level
     *
     * @param monster monster
     */
    public void setMonster(ArrayList<Monster> monster) {
        this.monster = monster;
    }

    /**
     * gibt die monster in dem level zurück
     *
     * @return
     */
    public ArrayList<Monster> getMonster() {
        return monster;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public Animation getActiveAnimation() {
        return this.activeAnimation;
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public RangedFight getRangedFight(){
        return this.rangedFight;
    }

    public BasicSkill getConvert() {
        return convert;
    }

    public BasicSkill getAura() {
        return aura;
    }

    public String getFightStatus() {
        if(this.attributes.getCurrentMana() < 5){
            return this.fightStatus;
        }

        if(this.rangedFight.getIsInvis()){
            return "ready";
        }else{
            return "on cooldown";
        }
    }

    public String getAuraStatus() {
        return auraStatus;
    }

    public String getConvertStatus() {
        return convertStatus;
    }

}
