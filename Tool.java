public class Tool extends HoldingObject {

public Tool(boolean isHeld) {
super(isHeld);
}
public Tool(Coordinate location) {
super(location);
}
public Tool() {
super();
}
public String toString() {
return "tool";
}

}