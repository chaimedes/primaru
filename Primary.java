import java.io.*;
import java.util.Scanner;

public class Primary {

public static void main(String [] args) {
World w = new World();
w.populate();
Scanner fileIn = new Scanner(System.in);
String directive = "";
do {
System.out.print("]: ");
directive = fileIn.nextLine();
directive = directive.toLowerCase();
if (directive.startsWith("go")) {
w.walk(directive);
}
if (directive.equals("teleport to edge")) {
w.teleportPlayerToStartingEdge();
}
if (directive.matches("l[op]{1,3}k [as]r[po][ui]nd")) {
w.getPlayer().see();
String seen = w.doSee();
if ("nothing".equals(seen)) {
System.out.println("I don't see anything interesting nearby.");
}
else {
System.out.println("I see a " + seen + ".");
}
}
if (directive.matches("pick up .+") || directive.matches("take .+")) {
String toTake = directive.substring(5);
if (directive.matches("pick up .+")) {
toTake = directive.substring(8);
}
String pickedUp = w.getPlayer().pickUp(toTake);
if ("nothing".equals(pickedUp)) {
System.out.println("I see nothing like that to pick up.");
}
else {
System.out.println("I have picked up the " + pickedUp + ".");
}
}
if (directive.matches("wha[t] are (y){1,3}ou holding(?){1}")) {
System.out.println("I am holding a " + w.getPlayer().getHoldingObjectAsString() + ".");
}
if (directive.matches("use .+ on .+")) {
String [] temp = directive.split("on");
}
} while (!"exit".equals(directive));
fileIn.close();
}

}
