package helper;

import tools.Point;


public class PointAndBoolean {
    boolean bool;
    Point point;

    public PointAndBoolean(boolean bool, Point p) {
        this.bool = bool;
        this.point = p;
    }

    public boolean getBoolean() {
        return this.bool;
    }

   

    public Point getPoint() {
        return this.point;
    }
}
