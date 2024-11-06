import java.util.Optional;

class ScientificLibraryHall {
    public String hallName; // имя зала
    private Book[] books; // массив книг в зале
    private int bookCount; // количество книг в зале

    public ScientificLibraryHall(String hallName, int initialCapacity) {
        this.hallName = hallName;
        this.books = new Book[initialCapacity]; // инициализация массива книг с заданной начальной вместимостью
        this.bookCount = 0; // начальное количество книг равно нулю
    }

    // новый конструктор, который принимает массив книг
    public ScientificLibraryHall(String hallName, Book[] initialBooks) {
        this.hallName = hallName;
        this.books = new Book[initialBooks.length]; // инициализация массива книг размером, соответствующим переданному массиву
        System.arraycopy(initialBooks, 0, this.books, 0, initialBooks.length); // копирование книг в массив
        this.bookCount = initialBooks.length; // установка количества книг
    }

    public int getBookCount() {
        return bookCount; // возвращает текущее количество книг
    }

    public int getCapacity() {
        return books.length; // возвращает текущую вместимость массива книг
    }

    public void increaseCapacity() {
        Book[] newBooks = new Book[books.length + 10]; // создаем новый массив увеличенного размера (на 10)
        System.arraycopy(books, 0, newBooks, 0, books.length); // копируем существующие книги в новый массив
        books = newBooks; // переназначаем массив книг
    }

    public boolean addBook(int index, Book newBook) {
        if (index < 0 || index > bookCount) { // проверяем корректность индекса
            return false;
        }
        if (bookCount == books.length) { // проверяем, нужно ли увеличить вместимость
            increaseCapacity();
        }
        System.arraycopy(books, index, books, index + 1, bookCount - index); // сдвигаем элементы для вставки новой книги
        books[index] = newBook; // вставляем новую книгу
        bookCount++; // увеличиваем количество книг
        return true;
    }

    public boolean removeBook(int index) {
        if (index < 0 || index >= bookCount) { // проверяем корректность индекса
            return false;
        }
        System.arraycopy(books, index + 1, books, index, bookCount - index - 1); // сдвигаем элементы, чтобы заполнить пробел
        books[--bookCount] = null; // освобождаем последний элемент и уменьшаем счетчик книг
        return true;
    }

    public Optional<Book> getBook(int index) {
        if (index < 0 || index >= bookCount) { // проверяем корректность индекса
            return Optional.empty();
        }
        return Optional.ofNullable(books[index]); // возвращаем книгу, если она существует
    }

    public Optional<Book> getBestBook() {
        if (bookCount == 0) return Optional.empty(); // если книг нет, возвращаем пустой Optional
        Book bestBook = books[0]; // начинаем с первой книги как самой дорогой
        for (int i = 1; i < bookCount; i++) {
            if (books[i].getPrice() > bestBook.getPrice()) { // ищем книгу с наибольшей ценой
                bestBook = books[i];
            }
        }
        return Optional.of(bestBook); // возвращаем самую дорогую книгу
    }

    public double getTotalCost() {
        double total = 0;
        for (int i = 0; i < bookCount; i++) {
            total += books[i].getPrice(); // суммируем цены всех книг
        }
        return total; // возвращаем общую стоимость
    }

    public boolean changeBook(int index, Book newBook) {
        if (index < 0 || index >= bookCount) { // проверяем корректность индекса
            return false;
        }
        books[index] = newBook; // заменяем книгу на новую
        return true;
    }
}