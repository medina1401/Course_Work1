import java.util.HashMap;
import java.util.Scanner;

public class Main{
    static HashMap<String, String> users = new HashMap<>();
    static HashMap<String, String> userCourses = new HashMap<>();
    static String currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // lol

            if (choice == 1) {
                System.out.print("Введите имя пользователя: ");
                String username = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String password = scanner.nextLine();

                if (users.containsKey(username)) {
                    System.out.println("Пользователь уже существует.");
                } else {
                    users.put(username, password);
                    System.out.println("Регистрация успешна!");
                }
            } else if (choice == 2) {
                System.out.print("Введите имя пользователя: ");
                String username = scanner.nextLine();
                System.out.print("Введите пароль: ");
                String password = scanner.nextLine();

                if (users.containsKey(username) && users.get(username).equals(password)) {
                    System.out.println("Вход выполнен успешно!");
                    currentUser = username;
                    userMenu(scanner);
                } else {
                    System.out.println("Неверное имя пользователя или пароль.");
                }
            } else if (choice == 3) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Неверный выбор.");
            }
        }
    }

    static void userMenu(Scanner scan) {
        while (true) {
            String currentCourse = userCourses.getOrDefault(currentUser, null);
            System.out.println("\nДобро пожаловать, " + currentUser + "!");
            System.out.println("1. Зарегистрироваться на курс");
            System.out.println("2. Отказаться от курса");
            System.out.println("3. Обновить курс");
            System.out.println("4. Показать текущий курс");
            System.out.println("5. Выйти из аккаунта");
            System.out.println("6. Изменить имя пользователя");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    if (currentCourse == null) {
                        System.out.print("Введите курс: ");
                        currentCourse = scan.nextLine();
                        userCourses.put(currentUser, currentCourse);
                        System.out.println("Вы зарегистрированы на курс: " + currentCourse);
                    } else {
                        System.out.println("Вы уже зарегистрированы на курс: " + currentCourse);
                    }
                    break;
                case 2:
                    if (currentCourse != null) {
                        System.out.println("Вы отказались от курса: " + currentCourse);
                        userCourses.put(currentUser, null);
                    } else {
                        System.out.println("Вы не зарегистрированы на курс.");
                    }
                    break;
                case 3:
                    if (currentCourse != null) {
                        System.out.print("Введите новый курс: ");
                        currentCourse = scan.nextLine();
                        userCourses.put(currentUser, currentCourse);
                        System.out.println("Курс обновлён на: " + currentCourse);
                    } else {
                        System.out.println("Сначала зарегистрируйтесь на курс.");
                    }
                    break;
                case 4:
                    if (currentCourse != null) {
                        System.out.println("Текущий курс: " + currentCourse);
                    } else {
                        System.out.println("Вы не зарегистрированы на курс.");
                    }
                    break;
                case 5:
                    currentUser = null;
                    System.out.println("Вы вышли из аккаунта.");
                    return;

                case 6:
                    System.out.print("Введите новое имя пользователя: ");
                    String newUsername = scan.nextLine();
                    if (users.containsKey(newUsername)) {
                        System.out.println("Имя уже занято.");
                    } else {
                        String password = users.get(currentUser);
                        String course = userCourses.get(currentUser);
                        users.remove(currentUser);
                        userCourses.remove(currentUser);

                        users.put(newUsername, password);
                        userCourses.put(newUsername, course);
                        currentUser = newUsername;
                        System.out.println("Имя пользователя изменено успешно на: " + currentUser);
                    }
                    break;

                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}
