public class HoldingObject extends WorldObject {

private boolean isHeld;

public HoldingObject(boolean isHeld, Coordinate location) {
super(location);
this.isHeld = isHeld;
}
public HoldingObject(boolean isHeld) {
this(isHeld, new Coordinate(0,0));
}
public HoldingObject(Coordinate location) {
this(false, location);
}
public HoldingObject() {
this(false);
}
public String toString() {
return "holdable object";
}

}