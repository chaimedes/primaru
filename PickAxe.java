public class PickAxe extends Tool { 

public PickAxe(boolean isHeld) {
super(isHeld);
}
public PickAxe(Coordinate location) {
super(location);
}
public PickAxe() {
super();
}
public String toString() {
return "pick-axe";
}

}