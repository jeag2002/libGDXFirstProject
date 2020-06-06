package com.mygdx.game.logic.map.procedural;

import java.util.Objects;

public class Point {
    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(Point p) { 
        this.x = p.x;
        this.y = p.y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return (p.x == x) && (p.y == y);
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return "[" + x + "," + y + "]";
    }

    public int getX() {
        return x;
    }
    
    public int getY() { 
        return y;
    }


    public boolean valid(int minx, int maxx, int miny, int maxy) {
        return x >= minx && x < maxx &&
               y >= miny && y < maxy;
    }

    public static Point subtract(Point a, Point b) { 
        return a.subtract(b);
    }
    
    public static Point add(Point a, Point b) { 
        return new Point(a.x + b.x, a.y + b.y);
    }

    public Point subtract(Point p) {
        return new Point(x - p.x, y - p.y);
    }
    
    public Point abs() { 
        return new Point(Math.abs(x), Math.abs(y));
    }
    
    
    public Point wrap() {
        if (x > 1) x = 1;
        if (x < -1) x = -1;
        
        if (y > 1) y = 1;
        if (y < -1) y = 1;
        return this;
    }
}
