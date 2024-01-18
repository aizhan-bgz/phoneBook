import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
    private Contact[] contacts;
    private Integer size;

    public ContactManager() {
        contacts = new Contact[100];
        size = 0;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    //Методы для выполнения операций CRUD
    public void addContact(Contact contact) {
        contacts[size] = contact;
        size++;
    }
    public void viewAllContacts() {
        if (size == 0) System.out.println("Телефонная книжка пуста!");
        else {
            System.out.println("Список контактов: ");
            for (int i = 0; i < size; i++) {
                if(contacts[i] != null)
                    System.out.println((i + 1) + ". " + contacts[i].getFirstName());
            }
        }
    }
    public void updateContact(Integer contactNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Какое поле контакта вы хотите изменить?\n" +
                "1 - Имя\n" +
                "2 - Фамилию\n" +
                "3 - Номер телефона\n" +
                "4 - Адрес электронной почты");
        int i = scanner.nextInt();
        String newData;
        scanner.nextLine();
        switch (i) {
            case 1:
                System.out.print("Введите новое имя контакта: ");
                newData = scanner.nextLine();
                contacts[contactNumber - 1].setFirstName(newData);
                break;
            case 2:
                System.out.print("Введите новую фамилию: ");
                newData = scanner.nextLine();
                contacts[contactNumber - 1].setLastName(newData);
                break;
            case 3:
                System.out.print("Введите новый номер контакта: ");
                newData = scanner.nextLine();
                contacts[contactNumber - 1].setPhoneNumber(newData);
                break;
            case 4:
                System.out.print("Введите новый адрес электронной почты: ");
                newData = scanner.nextLine();
                contacts[contactNumber - 1].setEmail(newData);
                break;
            default:
                System.out.println("Некорректный ввод ");
                return;
        } System.out.println("Контакт успешно обновлен!");
    }

    public void deleteContact(Integer contactNumber) {
        for (int i = contactNumber - 1; i < size; i++) {
            contacts[i] = null;
            contacts[i] = contacts[i + 1];
        } size--;
    }

    public void searchContactsByName(String word){
        List<Contact> searchResult = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if(contacts[i].getFirstName().equals(word)) searchResult.add(contacts[i]);
        }
        if(!searchResult.isEmpty()) {
            System.out.println("Найдено по запросу:");
            for (Contact c: searchResult) {
                System.out.println(c);
            }
        } else System.out.println("По вашему запросу ничего не найдено");
    }

    public void searchContactsByPhoneNumber (String number){
        List<Contact> searchResult = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if(contacts[i].getPhoneNumber().equals(number)) searchResult.add(contacts[i]);
        }
        if(!searchResult.isEmpty()) {
            System.out.println("Найдено по запросу:");
            for (Contact c: searchResult) {
                System.out.println(c);
            }
        } else System.out.println("По вашему запросу ничего не найдено");
    }

    public void saveContactsToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("contacts.txt", true);
        for (int i = 0; i < size; i++) {
            fileWriter.write(contacts[i].getFirstName() + ", " +
                    contacts[i].getLastName() + ", " +
                    contacts[i].getPhoneNumber() + ", " +
                    contacts[i].getEmail() + "\n");
        }
        fileWriter.close();
        System.out.println("Контакты успешно сохранены в файл");
    }

    public void loadContactsFromFile () throws IOException {
        FileReader fileReader = new FileReader("contacts.txt");
        Scanner scanner = new Scanner(fileReader);

        String[] contactData;
         while (scanner.hasNext()) {
             String contact = scanner.nextLine();
             contactData = contact.split(", ");
             addContact(new Contact(contactData[0], contactData[1], contactData[2], contactData[3]));
         }
         fileReader.close();
        System.out.println("Контакты успешно загружены из файла");
    }


}
