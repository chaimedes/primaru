import java.util.ArrayList;

public class Queue<T extends WorldObject> {
private ArrayList list;
private final int capacity = 4;
public Queue() {
list = new ArrayList<T>();
}
public void enqueue(T o) {
list.add(0,o);
if (size() > capacity) {
dequeue();
}
}
public T dequeue() {
if (list.size() == 0) {
return null;
}
T temp = (T)list.get(list.size()-1);
list.remove(list.size()-1);
return temp;
}
public T peek() {
if (list.size() == 0) {
return null;
}
return (T)list.get(list.size()-1);
}
public int size() {
return list.size();
}

}