class Book {
    private String title;  // Название книги
    private String author; // Автор книги
    private double price;  // Цена книги

    // Конструктор
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Получение названия книги
    public String getTitle() {
        return title;
    }

    // Установка названия книги
    public void setTitle(String title) {
        this.title = title;
    }

    // Получение автора книги
    public String getAuthor() {
        return author;
    }

    // Установка автора книги
    public void setAuthor(String author) {
        this.author = author;
    }

    // Получение цены книги
    public double getPrice() {
        return price;
    }

    // Установка цены книги
    public void setPrice(double price) {
        this.price = price;
    }

    // Переопределение метода toString() для удобного вывода информации о книге
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}