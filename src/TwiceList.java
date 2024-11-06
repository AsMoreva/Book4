import java.util.Optional;

class TwiceList {
    private Item2 head; // Голова списка
    private int size; // Количество элементов

    public TwiceList() {
        this.head = new Item2(null); // Голова не хранит данных
        this.head.next = this.head.prev = this.head;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public Optional<ScientificLibraryHall> getItem(int index) {
        if (index < 0 || index >= size) {
            return Optional.empty();
        }
        Item2 current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return Optional.of(current.data);
    }

    public boolean addItem(int index, ScientificLibraryHall hall) {
        if (index < 0 || index > size) {
            return false;
        }
        Item2 newItem = new Item2(hall);
        Item2 current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        newItem.next = current.next;
        newItem.prev = current;
        current.next.prev = newItem;
        current.next = newItem;
        size++;
        return true;
    }

    public boolean removeItem(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        Item2 current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        current.next.prev = current;
        size--;
        return true;
    }
}