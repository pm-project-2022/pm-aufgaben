package EntityController;

import basiselements.Animatable;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public class MyHero extends Animatable {
    private Animation idleAnimation, idleMirrorAnimation, runAnimation, runMirrorAnimation;
    private boolean movementState = false, viewDirection = true;
    private Point position;
    private Level currentLevel;

    public MyHero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        idleAnimation();
        idleMirrorAnimation();
        runAnimation();
        runMirrorAnimation();
    }

    private void idleAnimation(){
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_idle_anim_f0.png");
        animation.add("character/knight/knight_m_idle_anim_f1.png");
        animation.add("character/knight/knight_m_idle_anim_f2.png");
        animation.add("character/knight/knight_m_idle_anim_f3.png");
        this.idleAnimation = new Animation(animation, 8);
    }

    private void idleMirrorAnimation(){
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_idle_anim_mirrored_f0.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f1.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f2.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f3.png");
        this.idleMirrorAnimation = new Animation(animation, 8);
    }

    private void runAnimation() {
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_run_anim_f0.png");
        animation.add("character/knight/knight_m_run_anim_f1.png");
        animation.add("character/knight/knight_m_run_anim_f2.png");
        animation.add("character/knight/knight_m_run_anim_f3.png");
        this.runAnimation = new Animation(animation, 8);
    }

    private void runMirrorAnimation(){
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_run_anim_mirrored_f0.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f1.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f2.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f3.png");
        this.runMirrorAnimation = new Animation(animation, 8);
    }


    public void setLevel(Level level) {
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
    }
    
    @Override
    public void update() {
        Point newPosition = new Point(this.position);
        float movementSpeed = 0.1f;

        // Wenn die Taste W gedrückt ist, bewege dich nach oben
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            newPosition.y += movementSpeed;
            this.movementState = true;
        }else{
            this.movementState = false;
        }
        // Wenn die Taste S gedrückt ist, bewege dich nach unten
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            newPosition.y -= movementSpeed;
            this.movementState = true;
        }else{
            this.movementState = false;
        }
        // Wenn die Taste D gedrückt ist, bewege dich nach rechts
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            newPosition.x += movementSpeed;
            this.movementState = true;
            this.viewDirection = true;
        }else{
            this.movementState = false;
        }
        // Wenn die Taste A gedrückt ist, bewege dich nach links
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            newPosition.x -= movementSpeed;
            this.movementState = true;
            this.viewDirection = false;
        }else{
            this.movementState = false;
        }
        // Wenn der übergebene Punkt betretbar ist, ist das nun die aktuelle Position
        if (currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
            this.position = newPosition;
        }
    }

    

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Animation getActiveAnimation() {
        if(this.movementState){
            if(this.viewDirection){
                return runAnimation;
            }else{
                return runMirrorAnimation;
            }
        }else{
            if(this.viewDirection){
                return idleAnimation;
            }else{
                return idleMirrorAnimation;
            }
        }
    }
    
}
