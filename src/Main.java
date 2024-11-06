import java.util.Optional;

class Main {
    public static void main(String[] args) {
        // Создаем массив книг для тестирования
        Book[] books1 = {
                new Book("Квантовая Физика", "Автор A", 50.0),
                new Book("Теория вероятности", "Автор B", 60.0)
        };

        Book[] books2 = {
                new Book("Астрофизика", "Автор C", 70.0),
                new Book("Космология", "Автор D", 80.0),
                new Book("Черные дыры", "Автор E", 90.0)
        };

        // Создаем залы с книгами
        ScientificLibraryHall hall1 = new ScientificLibraryHall("Физика", books1);
        ScientificLibraryHall hall2 = new ScientificLibraryHall("Астрономия", books2);

        // Создаем библиотеку
        ScientificLibrary library = new ScientificLibrary(new ScientificLibraryHall[]{hall1, hall2});

        // Печатаем информацию о залах
        System.out.println("Начальное состояние библиотеки:");
        library.printHallsInfo();

        // Добавляем новую книгу в первый зал
        library.addBook(0, new Book("Элементарная физика", "Автор F", 65.0));
        System.out.println("\nПосле добавления книги:");
        library.printHallsInfo();

        // Заменяем книгу
        library.replaceBook(1, new Book("Новая книга по физике", "Автор G", 100.0));
        System.out.println("\nПосле замены книги:");
        library.printHallsInfo();

        // Удаляем книгу
        library.removeBook(2);
        System.out.println("\nПосле удаления книги:");
        library.printHallsInfo();

        // Получаем и выводим самую дорогую книгу
        Optional<Book> bestBook = library.getBestBook();
        bestBook.ifPresent(book -> System.out.println("\nСамая дорогая книга: " + book.getTitle() + " с ценой " + book.getPrice() + " от автора " + book.getAuthor()));

        // Выводим книги в порядке убывания цены
        System.out.println("\nОтсортированные книги по цене:");
        sortAndPrintBooksByPrice(library);
    }

    private static void sortAndPrintBooksByPrice(ScientificLibrary library) {
        // Собираем все книги в один массив
        int totalBooks = library.getTotalBookCount();
        Book[] allBooks = new Book[totalBooks];
        int index = 0;
        for (int i = 0; i < library.getHallCount(); i++) {
            Optional<ScientificLibraryHall> hall = library.getHall(i);
            if (hall.isPresent()) {
                for (int j = 0; j < hall.get().getBookCount(); j++) {
                    Optional<Book> book = hall.get().getBook(j);
                    if (book.isPresent()) {
                        allBooks[index++] = book.get();
                    }
                }
            }
        }

        // Сортируем книги по убыванию цены
        for (int i = 0; i < allBooks.length - 1; i++) {
            for (int j = 0; j < allBooks.length - i - 1; j++) {
                if (allBooks[j].getPrice() < allBooks[j + 1].getPrice()) {
                    Book temp = allBooks[j];
                    allBooks[j] = allBooks[j + 1];
                    allBooks[j + 1] = temp;
                }
            }
        }

        // Выводим отсортированные книги
        for (Book book : allBooks) {
            System.out.println(book.getTitle() + " от автора " + book.getAuthor() + " - " + book.getPrice());
        }
    }
}