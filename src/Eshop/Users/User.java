package Eshop.Users;

import Eshop.Goods.Good;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String surname;

    private String login;
    private String password;
    private boolean adminStatus;

    List<Customer> customers = new ArrayList<>();
    List<Admin> admins = new ArrayList<>();
    List<Good> basket = new ArrayList<>();


    public User() {
    }

    public User(String name, String surname, String login, String password, boolean adminStatus) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.adminStatus = adminStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Good> getBasket() {
        return basket;
    }

    public void setBasket(List<Good> basket) {
        this.basket = basket;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);
    }

    public void addAdmin(Admin admin){
        admins.add(admin);
    }

    public void removeAdmin(Admin admin){
        admins.remove(admin);
    }

    public void addToBasket(Good good){
        basket.add(good);
    }

    public void removeFromBasket(Good good){
        basket.remove(good);
    }

}


