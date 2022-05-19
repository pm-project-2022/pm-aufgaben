package Helper;

import tools.Point;

/**
 * Hilsklasse um booleans und positions gleichzeitig zurückzugeben
 */

public class PointBooleanTransmitter {
    private boolean runDirection;
    private boolean collision;
    private boolean idle;
    private Point newPosition;

    public PointBooleanTransmitter(boolean runDirection, boolean collision, Point newPosition) {
        this.runDirection = runDirection;
        this.collision = collision;
        this.newPosition = newPosition;
        this.idle = false;
    }

    public PointBooleanTransmitter(boolean idle, Point newPosition) {
        this.idle = idle;
        this.newPosition = newPosition;
        this.collision = false;
    }

    

    public boolean getRunDirection() {
        return this.runDirection;
    }

    public boolean getCollision() {
        return this.collision;
    }

    public boolean getIdle() {
        return this.idle;
    }

    public Point getPoint() {
        return this.newPosition;
    }
    
}
