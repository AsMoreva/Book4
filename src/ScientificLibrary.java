import java.util.Optional;

class ScientificLibrary {
    private TwiceList halls; // список залов в библиотеке

    public ScientificLibrary(int hallCount, int[] bookCounts) {
        this.halls = new TwiceList(); // инициализация списка залов
        for (int i = 0; i < hallCount; i++) {
            halls.addItem(i, new ScientificLibraryHall("Зал " + (i + 1), bookCounts[i])); // добавление залов с заданным количеством книг
        }
    }

    public ScientificLibrary(ScientificLibraryHall[] hallArray) {
        this.halls = new TwiceList(); // инициализация списка залов
        for (int i = 0; i < hallArray.length; i++) {
            halls.addItem(i, hallArray[i]); // добавление залов из массива
        }
    }

    public int getHallCount() {
        return halls.getSize(); // возвращает количество залов в библиотеке
    }

    public int getTotalBookCount() {
        int total = 0;
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                total += hall.get().getBookCount(); // суммирует количество книг во всех залах
            }
        }
        return total; // возвращает общее количество книг
    }

    public double getTotalCost() {
        double total = 0;
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                total += hall.get().getTotalCost(); // суммирует общую стоимость книг во всех залах
            }
        }
        return total; // возвращает общую стоимость всех книг
    }

    public Optional<ScientificLibraryHall> getHall(int index) {
        return halls.getItem(index); // возвращает зал по индексу
    }

    public Optional<Book> getBook(int index) {
        int count = 0;
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                int hallBookCount = hall.get().getBookCount();
                if (count + hallBookCount > index) {
                    return hall.get().getBook(index - count); // возвращает книгу по глобальному индексу
                }
                count += hallBookCount;
            }
        }
        return Optional.empty(); // возвращает пустое значение, если книга не найдена
    }

    public boolean replaceHall(int index, ScientificLibraryHall newHall) {
        if (index < 0 || index >= halls.getSize()) {
            return false; // проверка корректности индекса
        }
        halls.removeItem(index); // удаляет старый зал
        return halls.addItem(index, newHall); // добавляет новый зал на то же место
    }

    public boolean replaceBook(int index, Book newBook) {
        int count = 0;
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                int hallBookCount = hall.get().getBookCount();
                if (count + hallBookCount > index) {
                    return hall.get().changeBook(index - count, newBook); // заменяет книгу по глобальному индексу
                }
                count += hallBookCount;
            }
        }
        return false; // возвращает false, если книга не найдена
    }

    public boolean addBook(int index, Book newBook) {
        int count = 0;
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                ScientificLibraryHall currentHall = hall.get();
                int hallBookCount = currentHall.getBookCount();
                if (count + hallBookCount >= index) {
                    // проверка необходимости увеличения объема зала
                    if (hallBookCount == currentHall.getCapacity()) {
                        currentHall.increaseCapacity(); // увеличиваем объем зала
                    }
                    return currentHall.addBook(index - count, newBook); // добавляет книгу по глобальному индексу
                }
                count += hallBookCount;
            }
        }
        return false; // возвращает false, если книга не добавлена
    }

    public boolean removeBook(int index) {
        int count = 0;
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                int hallBookCount = hall.get().getBookCount();
                if (count + hallBookCount > index) {
                    return hall.get().removeBook(index - count); // удаляет книгу по глобальному индексу
                }
                count += hallBookCount;
            }
        }
        return false; // возвращает false, если книга не найдена
    }

    public Optional<Book> getBestBook() {
        Optional<Book> bestBook = Optional.empty();
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                Optional<Book> currentBest = hall.get().getBestBook();
                if (bestBook.isEmpty() || (currentBest.isPresent() && currentBest.get().getPrice() > bestBook.get().getPrice())) {
                    bestBook = currentBest; // обновляет самую дорогую книгу, если найдена более дорогая
                }
            }
        }
        return bestBook; // возвращает самую дорогую книгу
    }

    public void printHallsInfo() {
        for (int i = 0; i < halls.getSize(); i++) {
            Optional<ScientificLibraryHall> hall = halls.getItem(i);
            if (hall.isPresent()) {
                System.out.println(hall.get().hallName + ": " + hall.get().getBookCount() + " книги."); // выводит информацию о залах
            }
        }
    }
}