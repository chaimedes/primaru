public class Coordinate {

private int x;
private int y;
private boolean free;

public Coordinate(int x, int y, boolean free) {
this.x = x;
this.y = y;
this.free = free;
}
public Coordinate(int x, int y) {
this(x,y,true);
}
public Coordinate() {
this(0,0);
}

public int getX() {
return x;
}
public int getY() {
return y;
}

public boolean equals(Object o) {
Coordinate c = (Coordinate)o;
if (c.getX() == x && c.getY() == y) {
return true;
}
return false;
}

public boolean isFree() {
return free;
}
public void setFree(boolean free) {
this.free = free;
}

public String toString() {
return "(" + x + ", " + y + ")";
}

}