package Eshop;

import Eshop.Goods.Good;
import Eshop.Goods.GoodType;
import Eshop.Users.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws WrongInputDataException {

        List<User> users = new ArrayList<>();
        List<Good> goods = new ArrayList<>();
        List<Good> basket = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
//        User testCustomer = new User("Test", "Customer", "Admin", "1111", false);
//        User testAdmin = new User("Test", "Admin", "testUser", "1", true);

        users.add(new User("Simple", "User", "1", "1", false));
        users.add(new User("Admin", "Admin", "2", "2", true));
        users.add(new User("Petro", "Petrenko", "3", "3", false));
        users.add(new User("Ivan", "Ivanenko", "4", "4", false));

        goods.add(new Good(1, "Hobbit", 15.5, GoodType.BOOK));
        goods.add(new Good(2, "Marvel", 10, GoodType.COMIX));

        //////////////////////////////////////////////////////////////////

        boolean log = false;
        boolean stat = true;
        boolean isAdmin = false;
        boolean successfulLogin = false;
        boolean successfulDeletedUser = false;
        boolean successfulDeletedGood = false;
        boolean quit = false;
        String login = "";

        while (stat) {
            while (log != true) {
                System.out.println("Hola amigo!\nЩоб продовжити роботу, необхідно увійти в аккаунт\n" +
                        "Якщо немає акаунта - створіть новий" +
                        "\n\nНатисніть:\n1 - Вхід в акаунт\n2 - Реєстрація\n3 - Завершити роботу\n");

                Integer choose = sc.nextInt();
                switch (choose) {
                    case 1: {
                        while (successfulLogin != true) {
                            System.out.println("**Вхід**");
                            System.out.println("1. Введіть логін: ");
                            String inputLogin = sc.next();
                            login = inputLogin;
                            System.out.println("2. Введіть пароль: ");
                            String inputPassword = sc.next();
                            isAdmin = login(sc, users, inputLogin, inputPassword).isAdmin();
                            successfulLogin = login(sc, users, inputLogin, inputPassword).isSuccessfulLogin();
                            log = true;

                            if(successfulLogin == false){
                                System.out.println("\n1 - Спробувати знову");
                               // System.out.println("2 - Реєстрація");
                                System.out.println("2 - Завершити роботу\n");
                                Integer errorloginChoise = sc.nextInt();
                                switch (errorloginChoise){
                                    case 1:{
                                        break;
                                    }

                                    case 2:{
                                        System.out.println("**Кінець роботи!**");
                                        successfulLogin = true;
                                        stat = true;
                                        break;
                                    }
                                }
                                if (errorloginChoise != 1 || errorloginChoise != 2){
                                    System.out.println("Неправильний ввід");
                                    continue;
                                }
                            }
                        }
                        break;
                    }

                    case 2: {
                        registration(sc, users, isAdmin);
                        break;
                    }
                    case 3: {
                        System.out.println("**Кінець роботи!**\nТак швидко??");
                        log = true;
                        stat = false;
                        quit = true;
                        break;
                    }
                    default:{
                        log = false;
                        System.out.println("Неправильний ввід");
                        System.out.println("########################################\n");
                        break;
                    }
                }
            }

            if(isAdmin && !quit) {
                System.out.println("\nОберіть пункт меню:" +
                        "\n1 - Показати список користувачів" +
                        "\n2 - Додати нового користувача" +
                        "\n3 - Видалити користувача" +
                        "\n4 - Показати список товарів" +
                        "\n5 - Додати новий товар(без перевірок)" +
                        "\n6 - Видалити товар" +
                        "\n7 - Завершити програму");
                int adminKey = sc.nextInt();

                switch (adminKey) {
                    case 1: {
                        for (int i = 0; i < users.size(); i++) {
                            User user = users.get(i);
                            System.out.println("Користувач № " + (i + 1));
                            System.out.println("Ім'я : " + user.getName() + "\nПрізвище : " + user.getSurname() + "" +
                                    "\nЛогін : " + user.getLogin() + "   пароль : " + user.getPassword() + "\n\n");
                        }
                        continue;
                    }

                    case 2: {
                        System.out.println("Встановити користувача адміністратором (true - так/false - ні) ");
                        boolean newUserStatus = sc.nextBoolean();
                        registration(sc, users, newUserStatus);
                        continue;
                    }

                    case 3: {
                        while (successfulDeletedUser != true) {
                            System.out.println("Введіть логін користувача, якого потрібно видалити: ");
                            String deleteLogin = sc.next();
                            successfulDeletedUser = deletingAccount(deleteLogin, users);
                            if (successfulDeletedUser == false){
                                System.out.println("1 - Спробувати знову?");
                                System.out.println("2 - Вийти!");
                                Integer userDeleting = sc.nextInt();
                                switch (userDeleting){
                                    case 1:{
                                        continue;
                                    }
                                    case 2:{
                                        successfulDeletedUser = true;
                                        break;
                                    }
                                    default:{
                                        System.out.println("Неправильний ввід");
                                        successfulDeletedUser = false;
                                        continue;
                                    }
                                }
                            }
                            break;
                        }
                        continue;
                    }

                    case 4: {
                        for (int i = 0; i < goods.size(); i++) {
                            Good good = goods.get(i);
                            System.out.println("Продукт № " + (i + 1));
                            System.out.println("\nID : " + good.getId() + "\nНазва : " + good.getName() + "" +
                                    "\nЦіна : " + good.getPrice() + "\nКатегорія : " + good.getType());
                            System.out.println(good.toString());
                        }
                        continue;
                    }

                    case 5: {
                        addNewGood(sc, goods);
                        continue;
                    }

                    case 6: {
                        while (successfulDeletedGood != true){
                            System.out.println("Введіть ID товару, який потрібно видалити: ");
                            Integer deleteId = sc.nextInt();
                            successfulDeletedGood = deletingGood(deleteId, goods);
                            if (successfulDeletedGood == false) {
                                System.out.println("1 - Спробувати знову?");
                                System.out.println("2 - Вийти!");
                                Integer goodDeleting = sc.nextInt();
                                switch (goodDeleting) {
                                    case 1: {
                                        continue;
                                    }
                                    case 2: {
                                        successfulDeletedGood = true;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Неправильний ввід");
                                        successfulDeletedGood = false;
                                        continue;
                                    }
                                }
                            }
                        }
                        continue;
                    }

                    case 7: {
                        System.out.println("Захаті, дарахой!");
                        stat = true;
                        break;
                    }

                    default: {
                        stat = true;
                        System.out.println("Неправильний ввід");
                        System.out.println("########################################\n");
                        continue;
                    }
                }
                break;
            }else if (quit == false) {
            System.out.println("Оберіть пункт меню:" +
                    "\n1 - Показати список товарів" +
                    "\n2 - Додати товар у корзину" +
                    "\n3 - Показати корзину" +
                    "\n4 - Видалити товар з корзини(не працює - можна зробити так само, як у адміна)" +
                    "\n5 - Показати деталі акаунту(не працює)" +
                    "\n6 - Налаштування акаунту(не працює)" +
                    "\n7 - Вийти з акаунту(не працює)" +
                    "\n8 - Завершити програму");
            int key = sc.nextInt();

            switch (key) {

                case 1: {
                    for (int i = 0; i < goods.size(); i++) {
                        Good good = goods.get(i);
                        System.out.println("Продукт № " + (i + 1));
                        System.out.println("\nID : " + good.getId() + "\nНазва : " + good.getName() + "" +
                                "\nЦіна : " + good.getPrice() + "\nКатегорія : " + good.getType());
                        System.out.println("\n" + good.toString());
                    }
                    boolean displaying = true;
                    while (displaying) {
                        System.out.println("1 - Пошук товару (по назві)" +
                                "\n2 - Відсортувати по ціні (по зростанню) не працює)" +
                                "\n3 - Фільтрувати (по ціні)" +
                                "\n4 - Вихід\n");
                        Integer displChoose = sc.nextInt();
                        switch (displChoose){
                            case 1: {
                                search(sc, goods);
                                break;
                            }

                            case 2: {
                               // sorting(sc, goods);
                                break;
                            }

                            case 3: {
                                filtering(sc, goods);
                            }

                            case 4: {
                                displaying = false;
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }

                case 2: {
                    addGoodToBasket(sc, goods, users, basket, login);
                    break;
                }

                case 3: {
                    displayBasket(sc, users, login);
                    continue;
                }

                case 8: {
                    stat = false;
                    System.out.println("Ти ета, захаді, єслі чо");
                    break;
                }

                default: {
                    stat = true;
                    System.out.println("Неправильний ввід");
                    System.out.println("########################################\n");
                    break;
                }
            }

            }
        }
    }

    private static LoginResult login(Scanner sc, List<User> users, String login, String password) throws WrongInputDataException {
        boolean status = true;
        boolean adminStatus = false;
        boolean test = true;
        boolean successfulLogin = true;

        while (status) {
            String inputLogin = login;
            String inputPassword = password;
            User currentUser = null;
            Iterator<User> iterator = users.iterator();

            while (iterator.hasNext()) {
                User userFromIterator = iterator.next();
                if (userFromIterator.getLogin().equals(inputLogin) && userFromIterator.getPassword().equals(inputPassword)) {
                    currentUser = userFromIterator;
                    adminStatus = currentUser.isAdminStatus();
                    status = false;
                    break;
                }
            }
            if (currentUser == null) {
                System.out.println("**Хибний логін або пароль!**");
                successfulLogin = false;
                status = false;
                break;
            }
        }
        return new LoginResult(successfulLogin, adminStatus);
    }

    private static void registration(Scanner sc, List<User> users, Boolean status){
        boolean test = true;
        String login = "";
        System.out.println("**Реєстрація**");
        System.out.println("1. Введіть ім'я: ");
        String newUserName = sc.next();
        System.out.println("2. Введіть прізвище: ");
        String newUserSurname = sc.next();
        while (test) {
            System.out.println("3. Введіть логін: ");
            String newUserLogin = sc.next();

            User currentUserLogin = null;
            Iterator<User> iteratorUsers = users.iterator();
            while (iteratorUsers.hasNext()) {
                User userFromIterator = iteratorUsers.next();
                if (userFromIterator.getLogin().equals(newUserLogin)) {
                    System.out.println("Такий логін вже зареєстровано! спробуйте новий");
                    test = true;
                    continue;
                }else {
                    test = false;
                    login = newUserLogin;
                }
            }
        }
        System.out.println("4. Введіть пароль: ");
        String newUserPassword = sc.next();
        boolean isAdmin = false;
        isAdmin = status;

        users.add(new User(newUserName, newUserSurname, login, newUserPassword, isAdmin));
        System.out.println("\n**Реєстрація успішна!**");
        System.out.println("Користувач " + newUserName + ' ' + newUserSurname + " був створений!");
        System.out.println("##################################################################\n");
    }

    private static boolean deletingAccount(String loginToDelete, List<User> users) throws WrongInputDataException {
        String inputLogin = loginToDelete;
        User currentPerson = null;
        boolean successfulDeleted = true;
        boolean isAdmin = false;
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User personFromIterator = iterator.next();
            if (personFromIterator.getLogin().equals(inputLogin)) {
                currentPerson = personFromIterator;
                isAdmin = currentPerson.isAdminStatus();
                break;
            }
        }
        if (currentPerson == null){
            //throw new RuntimeException("Такого користувача не знайдено");
            System.out.println("Такого користувача не знайдено");
            successfulDeleted = false;
        }else if(currentPerson != null && isAdmin == true){
            System.out.println("Ви не можете видалити Адміністратора (Паша зробить то руцями;-)");
            successfulDeleted = false;
        } else if(currentPerson != null && isAdmin == false) {
            users.remove(currentPerson);
            System.out.println("Користувача " + currentPerson.getName() + ' ' + currentPerson.getSurname() + " успішно видалено!");
            successfulDeleted = true;
        }
        return successfulDeleted;
    }

    private static boolean deletingGood(Integer idToDelete, List<Good> goods) throws WrongInputDataException {
        Integer inputId = idToDelete;
        Good currentGood = null;
        boolean successfulDeleted = true;
       // boolean isAdmin = false;
        Iterator<Good> iterator = goods.iterator();
        while (iterator.hasNext()) {
            Good goodFromIterator = iterator.next();
            if (goodFromIterator.getId() == inputId) {
                currentGood = goodFromIterator;
                //isAdmin = currentPerson.isAdminStatus();
                break;
            }
        }
        if (currentGood == null){
            //throw new RuntimeException("Такого користувача не знайдено");
            System.out.println("Такого товару не знайдено");
            successfulDeleted = false;
        }else {
            goods.remove(currentGood);
            System.out.println("Товар" + currentGood.getName() + " успішно видалено!");
            successfulDeleted = true;
        }
        return successfulDeleted;
    }

    private static void addNewGood (Scanner sc, List <Good> goods){
        Integer id = -1;
        Good currentGood = null;
        Iterator<Good> iteratorGoods = goods.iterator();
        while (iteratorGoods.hasNext()) {
            Good goodFromIterator = iteratorGoods.next();
                currentGood = goodFromIterator;
                id = (currentGood.getId()) + 2;
                break;
        }
        System.out.println("Введіть назву товару: ");
        String inputGoodName = sc.next();
        System.out.println("Ведіть ціну товару: ");
        double inputGoodPrice = sc.nextDouble();
        System.out.println("Введіть тип товару (малими буквами): ");
        GoodType newGoodType = GoodType.getGoodType(sc.next());
        goods.add(new Good(id, inputGoodName, inputGoodPrice, newGoodType));
        System.out.println("Товар успішно додано!!))");

    }

    private static void addGoodToBasket(Scanner sc, List<Good> goods, List<User> users, List<Good> basket, String user){
        System.out.println("Введіть ID товару, який необхідно додати: ");
        Integer goodId = sc.nextInt();
        System.out.println("Введіть кількість одиниць товару, що необхідно додати: ");
        Integer quantity = sc.nextInt();

        String userLogin = user;
        User currentUser = null;
        Iterator<User> iteratorUsers = users.iterator();

        while (iteratorUsers.hasNext()) {
            User userFromIterator = iteratorUsers.next();
            if (userFromIterator.getLogin().equals(userLogin)) {
                currentUser = userFromIterator;
                break;
            }
        }

        Good currentGood = null;
        Iterator<Good> iteratorGoods = goods.iterator();
        while (iteratorGoods.hasNext()) {
            Good goodFromIterator = iteratorGoods.next();
            if (goodFromIterator.getId() == goodId) {
                currentGood = goodFromIterator;
                break;
            }
        }

        if (currentGood != null) {
            currentUser.addToBasket(currentGood);
            System.out.println("Товар " + currentGood.getName() + " успішно додано!!");
        } else {
            throw new RuntimeException("Товар не знайдено");
        }
    }

    private static void displayBasket(Scanner sc, List<User> users, String login){
        String userLogin = login;
        User currentUser = null;
        Iterator<User> iteratorUsers = users.iterator();

        while (iteratorUsers.hasNext()) {
            User userFromIterator = iteratorUsers.next();
            if (userFromIterator.getLogin().equals(userLogin)) {
                currentUser = userFromIterator;
                break;
            }
        }

        if(currentUser != null){
            for (int i = 0; i < currentUser.getBasket().size(); i++) {
                Good good = currentUser.getBasket().get(i);
                System.out.println("Продукт № " + (i + 1));
                System.out.println("\nID : " + good.getId() + "\nНазва : " + good.getName() + "" +
                        "\nЦіна : " + good.getPrice() + "\nКатегорія : " + good.getType());
                System.out.println("\n" + good.toString());
            }
        }
    }

    private static void search(Scanner sc, List<Good> goods){
        System.out.println("Введіть пошукову фразу: ");
        String searchPhrase = sc.next();

        Integer count = 0;
        Good currentGood = null;
        Iterator<Good> iteratorGoods = goods.iterator();
        while (iteratorGoods.hasNext()) {
            Good goodFromIterator = iteratorGoods.next();
            currentGood = goodFromIterator;
            if (currentGood.getName().toLowerCase().contains(searchPhrase.toLowerCase())){
                System.out.println(currentGood.toString());
                count += 1;
            }
        }
        System.out.println("Знайдено " + count + " товарів!");
    }

    private static void filtering(Scanner sc, List<Good> goods){
        System.out.println("Мінімальна ціна : ");
        Double minPrice = sc.nextDouble();
        System.out.println("Макcимальна: ");
        Double maxPrice = sc.nextDouble();

        Integer count = 0;
        Good currentGood = null;
        Iterator<Good> iteratorGoods = goods.iterator();
        while (iteratorGoods.hasNext()) {
            Good goodFromIterator = iteratorGoods.next();
            currentGood = goodFromIterator;
            if (currentGood.getPrice() >= minPrice && currentGood.getPrice() <= maxPrice){
                System.out.println(currentGood.toString());
                count += 1;
            }
        }
        System.out.println("Знайдено " + count + " товарів!");
    }

}

