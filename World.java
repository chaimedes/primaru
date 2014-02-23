public class World {

private int width;
private int height;
private Player player;
private Coordinate playerLocation;
private Coordinate [][] map;
private WorldObject [] stuff;

public World(int height, int width) {
this.height = height;
this.width = width;
player = new Player();
map = new Coordinate[width][height];
for (int i = 0; i < width; i++) {
for (int j = 0; j < height; j++) {
map[i][j] = new Coordinate(i,j);
}
}
playerLocation = map[width/2][height/2];
stuff = new WorldObject[10];
}
public World() {
this(100,100);
}

public void populate() {
stuff[0] = new PickAxe(map[5][5]);
}

public Player getPlayer() {
return player;
}

public void walk(String directive) {
if("go north".equals(directive)) {
getPlayer().up();
doWalk(0);
}
if ("go south".equals(directive)) {
getPlayer().down();
doWalk(2);
}
if ("go east".equals(directive)) {
getPlayer().right();
doWalk(3);
}
if ("go west".equals(directive)) {
getPlayer().left();
doWalk(1);
}
}

public void doWalk(int direction) {
Coordinate c = new Coordinate(0,0);
switch (direction) {
case 0:
c = new Coordinate(playerLocation.getX(),playerLocation.getY()-1);
break;
case 1:
c = new Coordinate(playerLocation.getX()-1,playerLocation.getY());
break;
case 2:
c = new Coordinate(playerLocation.getX(),playerLocation.getY()+1);
break;
case 3:
c = new Coordinate(playerLocation.getX()+1,playerLocation.getY());
break;
}
if (c.isFree() && inBounds(c)) {
getPlayer().setCanWalk(true);
playerLocation = c;
}
else {
System.out.println("I can't go there.");
}
System.out.println("Player is at " + playerLocation.toString());
}

public String doSee() {
String seeString = "";
int startx = playerLocation.getX();
int starty = playerLocation.getY();
for (int a = startx - getPlayer().SIGHT_DISTANCE; a < startx + getPlayer().SIGHT_DISTANCE; a++) {
for (int b = starty - getPlayer().SIGHT_DISTANCE; b < starty + getPlayer().SIGHT_DISTANCE; b++) {
if (a > 0 && a < width && b > 0 && b < height) {
if (!map[a][b].isFree()) {
seeString = getObjectAtLocationAsString(map[a][b]).concat("("+a+", "+b+")");
getPlayer().currentObj.add(getObjectAtLocation(map[a][b]));
}
}
}
}
if ("".equals(seeString)) {
return "nothing";
}
return seeString;
}


public boolean inBounds(Coordinate c) {
if (c.getX() < 0 || c.getX() > width-1 || c.getY() < 0 || c.getY() > height-1) {
return false;
}
return true;
} 

public String getObjectAtLocationAsString(Coordinate c) {
for (int a = 0; a < stuff.length; a++) {
if (stuff[a] != null) {
if (stuff[a].getLocation().equals(c)) {
return stuff[a].toString();
}
}
}
return "nothing";
}

public WorldObject getObjectAtLocation(Coordinate c) {
for (int a = 0; a < stuff.length; a++) {
if (stuff[a] != null) {
if (stuff[a].getLocation().equals(c)) {
return stuff[a];
}
}
}
return null;
}

public void teleportPlayerToStartingEdge() {
playerLocation = new Coordinate(0,0);
System.out.println("Player is at " + playerLocation.toString());
}

}