package helper;

import tools.Point;


public class PointAndBoolean {
    boolean runDirection;
    boolean collision;
    Point point;

    public PointAndBoolean(boolean runDirection, boolean collision, Point p) {
        this.runDirection = runDirection;
        this.collision = collision;
        this.point = p;
    }

    public boolean getRunDirection() {
        return this.runDirection;
    }

    public boolean getCollision() {
        return this.collision;
    }

   

    public Point getPoint() {
        return this.point;
    }
}
