class Item2 {
    public ScientificLibraryHall data; // Данные
    public Item2 prev; // Ссылка на предыдущий элемент
    public Item2 next; // Ссылка на следующий элемент

    public Item2(ScientificLibraryHall data) {
        this.data = data;
        this.prev = this.next = null;
    }
}