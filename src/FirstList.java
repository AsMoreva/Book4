import java.util.Optional;

class FirstList {
    private Item top; // Голова списка
    private int size; // Количество элементов

    public FirstList() {
        this.top = new Item(null); // Голова не хранит данных
        this.top.next = this.top; // Циклический список
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public Optional<Book> getItem(int index) {
        if (index < 0 || index >= size) {
            return Optional.empty();
        }
        Item current = top.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return Optional.of(current.data);
    }

    public boolean addItem(int index, Book book) {
        if (index < 0 || index > size) {
            return false;
        }
        Item newItem = new Item(book);
        Item current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        newItem.next = current.next;
        current.next = newItem;
        size++;
        return true;
    }

    public boolean removeItem(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        Item current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
        return true;
    }
}