import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  throws IOException {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        boolean isWorking = true;

        while (isWorking) {
            try {
            printMenu();
            int code = scanner.nextInt();
            scanner.nextLine();

            switch (code) {
                case 1:
                    contactManager.viewAllContacts();
                    if (contactManager.getSize() > 0) {
                        System.out.print("Выберите контакт для просмотра доступных данных: ");
                        int i = scanner.nextInt() - 1;
                        System.out.println((contactManager.getContacts())[i]);
                    }
                    break;
                case 2:
                    System.out.println("========== Создание нового контакта ==========");
                    if (contactManager.getSize() < 100) {
                        System.out.print("Введите имя: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Введите фамилию: ");
                        String lastName = scanner.nextLine();

                        System.out.print("Введите номер телефона: ");
                        String phoneNumber = scanner.nextLine();

                        System.out.print("Введите адрес электронной почты: ");
                        String email = scanner.nextLine();

                        contactManager.addContact(new Contact(firstName, lastName, phoneNumber, email));
                        System.out.println("Контакт успешно создан!");
                    } else
                        System.out.println("Телефонная книжка заполнена, невозможно добавить новый контакт");
                    break;
                case 3:
                    System.out.println("========== Обновление контакта ==========");
                    contactManager.viewAllContacts();
                    if (contactManager.getSize() > 0) {
                        System.out.print("Выберите контакт для обновления: ");
                        int contactNumber = scanner.nextInt();

                        if (contactNumber <= 0 || contactNumber > contactManager.getSize()) {
                            System.out.println("Недопустимый номер, попробуйте еще раз");
                            break;
                        } else {
                            contactManager.updateContact(contactNumber);
                        }
                    }
                    break;
                case 4:
                    System.out.println("========== Удаление контакта ==========");
                    contactManager.viewAllContacts();
                    if (contactManager.getSize() > 0) {
                        System.out.print("Выберите контакт для удаления: ");
                        int contactNumber = scanner.nextInt();

                        if (contactNumber <=0 || contactNumber > contactManager.getSize()) {
                            System.out.println("Недопустимый номер, попробуйте еще раз");
                            break;
                        } else {
                            contactManager.deleteContact(contactNumber);
                            System.out.println("Контакт успешно удален!");
                        }
                    }
                    break;
                case 5:
                    if (contactManager.getSize() == 0) System.out.println("Телефонная книжка пуста!");
                    else {
                        System.out.println("========== Поиск контакта по имени ==========");
                        System.out.print("Ваш запрос: ");
                        String word = scanner.nextLine();
                        contactManager.searchContactsByName(word);
                    }
                    break;
                case 6:
                    if (contactManager.getSize() == 0) System.out.println("Телефонная книжка пуста!");
                    else {
                        System.out.println("========== Поиск контакта по номеру телефона ==========");
                        System.out.print("Ваш запрос: ");
                        String number = scanner.nextLine();
                        contactManager.searchContactsByPhoneNumber(number);
                    }
                    break;
                case 7:
                    contactManager.saveContactsToFile();
                    break;
                case 8:
                    contactManager.loadContactsFromFile();
                    break;
                case 0: isWorking = false;
                break;
                default:
                    System.out.println("Неверный ввод, попробуйте еще раз");
            }
        } catch (InputMismatchException e) {
                System.err.println("Недопустимый символ! Пожалуйста, введите корректное значение");
                scanner.nextLine();
                System.out.println();
            }
        }
    }
    public static void printMenu() {
        System.out.print("========== Телефонная книжка ==========" +
                "\n1. Просмотреть контакты" +
                "\n2. Создать новый контакт" +
                "\n3. Обновить контакт" +
                "\n4. Удалить контакт" +
                "\n5. Поиск контактов по имени" +
                "\n6. Поиск контактов по номеру телефона" +
                "\n7. Сохранить контакты в файл" +
                "\n8. Загрузить контакты из файла" +
                "\n0. Выход" +
                "\nВыберите действие: "
        );
    }
}
