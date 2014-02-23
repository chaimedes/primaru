public class WorldObject {

private Coordinate location;

public WorldObject(Coordinate location) {
this.location = location;
this.location.setFree(false);
}
public WorldObject() {
this(new Coordinate(0,0));
}

public Coordinate getLocation() {
return location;
}
public String toString() {
return "object";
}

}