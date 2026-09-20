import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CuratorJournalApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<JournalRecord> journal = new ArrayList<>();

    private static final String NAME_REGEX = "^[А-Яа-яІіЇїЄєҐґA-Za-z\\-']{2,50}$";
    private static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$";
    private static final String PHONE_REGEX = "^\\+380\\d{9}$";
    private static final String NOT_EMPTY_REGEX = "^.+$";
    private static final String NUMBER_REGEX = "^\\d+[A-Za-zА-Яа-я]*$";

    static void main() {
        boolean running = true;
        while (running) {
            System.out.println("\nЖурнал куратора");
            System.out.println("1. Додати новий запис");
            System.out.println("2. Відобразити всі записи");
            System.out.println("3. Вийти");
            System.out.print("Оберіть дію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addRecord();
                    break;
                case "2":
                    displayRecords();
                    break;
                case "3":
                    running = false;
                    System.out.println("Програму завершено.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void addRecord() {
        System.out.println("\nСтворення нового запису");

        String lastName = getValidInput("Прізвище студента: ", NAME_REGEX,
                "Помилка: Прізвище повинно містити лише літери (мінімум 2).");

        String firstName = getValidInput("Ім'я студента: ", NAME_REGEX,
                "Помилка: Ім'я повинно містити лише літери (мінімум 2).");

        String birthDate = getValidInput("Дата народження (dd.mm.yyyy): ", DATE_REGEX,
                "Помилка: Невірний формат дати. Використовуйте dd.mm.yyyy.");

        String phone = getValidInput("Телефон (+380XXXXXXXXX): ", PHONE_REGEX,
                "Помилка: Невірний формат телефону. Очікується +380 та 9 цифр.");

        System.out.println("Введіть домашню адресу:");
        String street = getValidInput("Вулиця: ", NOT_EMPTY_REGEX, "Помилка: Поле не може бути порожнім.");
        String building = getValidInput("Будинок: ", NOT_EMPTY_REGEX, "Помилка: Поле не може бути порожнім.");
        String apartment = getValidInput("Квартира: ", NUMBER_REGEX, "Помилка: Номер квартири має містити цифри.");

        Address address = new Address(street, building, apartment);
        JournalRecord record = new JournalRecord(lastName, firstName, birthDate, phone, address);

        journal.add(record);
        System.out.println("Запис успішно додано до журналу");
    }

    private static void displayRecords() {
        System.out.println("\nВсі записи в журналі куратора:");
        if (journal.isEmpty()) {
            System.out.println("Журнал порожній.");
        } else {
            for (int i = 0; i < journal.size(); i++) {
                System.out.println((i + 1) + ". " + journal.get(i));
            }
        }
    }

    private static String getValidInput(String prompt, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (Pattern.matches(regex, input)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }
}