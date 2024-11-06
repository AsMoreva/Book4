class Item {
    public Book data; // Данные
    public Item next; // Ссылка на следующий элемент

    public Item(Book data) {
        this.data = data;
        this.next = null;
    }
}