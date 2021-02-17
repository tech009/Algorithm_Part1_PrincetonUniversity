import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size = 100;
    private int count = 0;
    private int front;
    private int back;
    private Item[] arr;

    public Deque() {
        arr = (Item[]) new Object[size];
        front = (size / 2) - 1;
        back = (size / 2);
    }

    public boolean isEmpty() {
        if (count == 0)
            return true;
        return false;
    }

    public int size() {
        return count;
    }

    private void resize() {
        Item[] tmp = (Item[]) new Object[size * 2];
        int ff = ((size * 2) / 4) - 1;
        for (int i = (front + 1); i < back; i++) {
            tmp[ff++] = arr[i];
        }
        front = ((size * 2) / 4) - 2;
        size *= 2;
        arr = null;
        arr = tmp;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null has been passed in addFirst method !!");
        if (front == -1)
            resize();
        // Fill the space and then move back.
        arr[front--] = item;
        count++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null has been passed in addLast method !!");
        if (back == size)
            resize();
        // Fill the space and move ahead.
        arr[back++] = item;
        count++;
    }

    public Item removeFirst() {
        if ((front + 1) == back)
            throw new NoSuchElementException("Deque is Empty !!");
        count--;
        return arr[++front];
    }

    public Item removeLast() {
        if ((front + 1) == back)
            throw new NoSuchElementException("Deque is Empty !!");
        count--;
        return arr[--back];
    }

    public Iterator<Item> iterator() {
        Iterator<Item> it = new Iterator<Item>() {

            int ff = front + 1;
            int bb = back - 1;

            public Item next() {
                if (ff <= bb) {
                    return arr[ff++];
                }
                throw new NoSuchElementException("No more items to return !!");
            }

            public boolean hasNext() {
                if (ff <= bb)
                    return true;
                return false;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    public static void main(String[] args) {

    }
}
