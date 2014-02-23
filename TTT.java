import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;

public class TTT {

public char [][] map = { {'-','-','-'},{'-','-','-'},{'-','-','-'} };
private boolean done;
private int [][][] priority = {
 { {1,2},{0,2},{2,2},{0,1},{0,0},{1,1},{2,0},{2,1},{1,0} },
 { {0,0},{2,2},{0,2},{2,0},{1,1},{1,2},{0,1},{1,0},{2,1} },
 { {0,0},{1,1},{2,2},{2,0},{0,2},{1,0},{0,1},{2,1},{1,2} },
 { {1,2},{1,0},{0,2},{0,1},{1,1},{2,2},{0,0},{2,1},{2,0} },
 { {0,0},{1,2},{2,0},{1,1},{0,2},{2,2},{1,0},{1,2},{2,1} }
 };
private int [][] curPri;
private static final int ANIMATION_SPEED = 100; //ms
private static final char NEUTRAL_CHAR = '-';
private Scanner fileIn;

public TTT() {
this.done = false;
this.fileIn = new Scanner(System.in);
try {
File f = new File("score.bin");
f.createNewFile();
if (!f.exists()) {
writeScore(0,0);
}
}
catch (IOException ie) {
ie.printStackTrace();
}
}

public void printMap() {
for (int i = 0; i < 80; i++) {
System.out.println("");
}
for (int a = 0; a < 3; a++) {
for (int b = 0; b < 3; b++) {
System.out.print("  " + map[a][b] + "  ");
}
System.out.println("");
System.out.println("");
}
}

public int [] readScore() throws FileNotFoundException, IOException {
int [] score = new int[2];
DataInputStream dis = new DataInputStream(new FileInputStream("score.bin"));
score[0] = dis.readInt(); // wins
dis.readChar();
score[1] = dis.readInt(); // losses
dis.close();
return score;
}

public void writeScore(int wins, int losses) throws FileNotFoundException, IOException {
DataOutputStream dos = new DataOutputStream(new FileOutputStream("score.bin"));
dos.writeInt(wins);
dos.writeChar('\t');
dos.writeInt(losses);
dos.close();
}

public void addWin() {
try {
int [] score = readScore();
score[0] += 1;
writeScore(score[0],score[1]);
}
catch (FileNotFoundException fe) {
fe.printStackTrace();
}
catch (IOException ie) {
ie.printStackTrace();
}
}

public void addLoss() {
try {
int [] score = readScore();
score[1] += 1;
writeScore(score[0],score[1]);
}
catch (FileNotFoundException fe) {
fe.printStackTrace();
}
catch (IOException ie) {
ie.printStackTrace();
}
}

private int [] getRandoms() {
Random r = new Random();
int [] numr = new int[2];
numr[0] = r.nextInt(3);
numr[1] = r.nextInt(3);
return numr;
}

private void shuffle() {
/*
Random r = new Random();
int [][] temp = new int[priority.length][2];
for (int a = 0; a < priority.length; a++) {
temp[a] = priority[r.nextInt(priority.length)];
}
priority = temp;
*/
Random r = new Random();
curPri = priority[r.nextInt(priority.length-1)];
}

public void cpuAction(int start) {
int a;
if (start >= curPri.length) {
start = 0;
}
//int [] sqr = getRandoms();
int [] sqr = new int[2];
for (a = start; a < curPri.length; a++) {
if (map[curPri[a][0]][curPri[a][1]] == NEUTRAL_CHAR) {
sqr[0] = curPri[a][0];
sqr[1] = curPri[a][1];
break;
}
}
if (map[sqr[0]][sqr[1]] == NEUTRAL_CHAR) {
//map[sqr[0]][sqr[1]] = 'O';
replace(sqr[0],sqr[1],'O');
}
else {
cpuAction(a+2);
}
}

public void cpuAction() {
cpuAction(0);
}

private String getNewDirective() {
String directive = "";
directive = fileIn.nextLine();
directive = directive.toLowerCase();
return directive;
}
private String getNewDirective(boolean isReselect) {
System.out.println("That spot is already taken -- please select another space. ]:");
String directive = getNewDirective();
return directive;
}

private void replace(int x, int y, char z) {
map[x][y] = '.';
printMap();
try {
Thread.sleep(ANIMATION_SPEED);
}
catch (Exception e) {
e.printStackTrace();
}
map[x][y] = '.';
printMap();
try {
Thread.sleep(ANIMATION_SPEED);
}
catch (Exception e) {
e.printStackTrace();
}
map[x][y] = ' ';
printMap();
try {
Thread.sleep(ANIMATION_SPEED);
}
catch (Exception e) {
e.printStackTrace();
}
if (z == 'X') {
map[x][y] = '.';
printMap();
try {
Thread.sleep(ANIMATION_SPEED);
}
catch (Exception e) {
e.printStackTrace();
}
map[x][y] = 'x';
printMap();
try {
Thread.sleep(ANIMATION_SPEED);
}
catch (Exception e) {
e.printStackTrace();
}
map[x][y] = 'X';
printMap();
}
else {
map[x][y] = '.';
printMap();
try {
Thread.sleep(ANIMATION_SPEED);
}
catch (Exception e) {
e.printStackTrace();
}
map[x][y] = 'o';
printMap();
try {
Thread.sleep(ANIMATION_SPEED);
}
catch (Exception e) {
e.printStackTrace();
}
map[x][y] = 'O';
printMap();
}

}

public void playerAction(String directive) {
if ("exit".equals(directive)) {
done = true;
}
if (directive.matches("[1-9]")) {
switch (Integer.parseInt(directive)) {
case 7: 
if (map[0][0] == NEUTRAL_CHAR) {
//map[0][0] = 'X';
replace(0,0,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 4: 
if (map[1][0] == NEUTRAL_CHAR) {
//map[1][0] = 'X';
replace(1,0,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 1: 
if (map[2][0] == NEUTRAL_CHAR) {
//map[2][0] = 'X';
replace(2,0,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 8: 
if (map[0][1] == NEUTRAL_CHAR) {
//map[0][1] = 'X';
replace(0,1,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 5: 
if (map[1][1] == NEUTRAL_CHAR) {
//map[1][1] = 'X';
replace(1,1,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 2: 
if (map[2][1] == NEUTRAL_CHAR) {
//map[2][1] = 'X';
replace(2,1,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 9: 
if (map[0][2] == NEUTRAL_CHAR) {
//map[0][2] = 'X';
replace(0,2,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 6: 
if (map[1][2] == NEUTRAL_CHAR) {
//map[1][2] = 'X';
replace(1,2,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
case 3: 
if (map[2][2] == NEUTRAL_CHAR) {
//map[2][2] = 'X';
replace(2,2,'X');
}
else {
playerAction(getNewDirective(true));
}
break;
default: 
break;
}
}
}

public boolean checkFull() {
boolean full = true;
for (int a = 0; a < 3; a++) {
for (int b = 0; b < 3; b++) {
if (map[a][b] == NEUTRAL_CHAR) {
full = false;
}
}
}
return full;
}

public boolean getDone() {
return done;
}
public void setDone(boolean done) {
this.done = done;
}

public String checkWin() {
String won = "";
char [] go = {'X','O'};
for (int z = 0; z < 2; z++) {
char curCheck = go[z];
for (int a = 0; a <  3; a++) {
if (map[a][0] == curCheck && map[a][1] == curCheck && map[a][2] == curCheck) {
won = (curCheck=='X')?"You":"Computer";
}
else if (map[0][a] == curCheck && map[1][a] == curCheck && map[2][a] == curCheck) {
won = (curCheck=='X')?"You":"Computer";
}
}
if (map[0][0] == curCheck && map[1][1] == curCheck && map[2][2] == curCheck) {
won = (curCheck=='X')?"You":"Computer";
}
else if (map[0][2] == curCheck && map[1][1] == curCheck && map[2][0] == curCheck) {
won = (curCheck=='X')?"You":"Computer";
}
}
return won;
}

public static void main(String [] args ) { 
TTT t = new TTT();
t.shuffle();
do {
if (t.checkFull() == true) {
t.addWin();
t.addLoss();
t.printMap();
System.out.println("Draw!");
t.setDone(true);
}
else {
t.printMap();
t.playerAction(t.getNewDirective());
String win = t.checkWin();
if (!"".equals(win)) {
if ("You".equals(win)) {
t.addWin();
}
else {
t.addLoss();
}
t.printMap();
System.out.println("Winner: " + win + "!");
t.setDone(true);
}
else {
if (t.checkFull() == true) {
t.addWin();
t.addLoss();
t.printMap();
System.out.println("Draw!");
t.setDone(true);
}
else {
t.cpuAction();
win = t.checkWin();
if (!"".equals(win)) {
if ("You".equals(win)) {
t.addWin();
}
else {
t.addLoss();
}
t.printMap();
System.out.println("Winner: " + win + "!");
t.setDone(true);
}
}
}
}
} while (!t.getDone());
try {
int [] score = t.readScore();
System.out.println("Wins: " + score[0] + "  Losses: " + score[1]);
}
catch (FileNotFoundException fe) {
fe.printStackTrace();
}
catch (IOException ie) {
ie.printStackTrace();
}
t.fileIn.close();
}

}