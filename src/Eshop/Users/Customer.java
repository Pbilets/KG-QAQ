package Eshop.Users;

public class Customer extends User {

   // User customer = new User("Test", "Customer", "1111", "1111", false);


    public void showAccountInfo(){
        System.out.println("Hi " + super.getName() + "" +
                "Your account data:" +
                "Name: " + super.getName() + "" +
                "Surname: " + super.getSurname() + "" +
                "Login: " + super.getLogin() + "" +
                "Password: " + super.getPassword() );
    }
}
