import java.util.ArrayList;

public class Player {

private int direction;
private boolean isWalking;
private boolean canWalk;
private boolean isHoldingObject;
private WorldObject currentHoldingObject;
protected static final int SIGHT_DISTANCE = 2; 
private boolean looking;
public ArrayList currentObj;

public Player() {
this.direction = 0;
this.isHoldingObject = false;
this.currentHoldingObject = null;
this.isWalking = false;
this.looking = false;
this.currentObj = new ArrayList<WorldObject>();
}

public void up() {
direction = 0;
isWalking = true;
}
public void down() {
direction = 2;
isWalking = true;
}
public void left() {
direction = 1;
isWalking = true;
}
public void right() {
direction = 3;
isWalking = true;
}

public void see() {
looking = true;
}
public String pickUp(String toTake) {
for (int a = 0; a < currentObj.size(); a++) {
if (currentObj.get(a).toString().equals(toTake)) {
isHoldingObject = true;
currentHoldingObject = (WorldObject)currentObj.get(a);
return toTake;
}
}
return "nothing";
}

/** Returns direction player wants to walk, if any. Otherwise returns -1. To be called by the parent world. **/
public int getWalkingIntent() {
if (isWalking == true) {
return direction;
}
return -1;
}

public void setCanWalk(boolean canWalk) {
this.canWalk = canWalk;
}

public String getHoldingObjectAsString() {
if (currentHoldingObject == null) {
return "nothing";
}
return currentHoldingObject.toString();
}

}
