package Eshop.Goods;

public class Good {
    // Поля классу
    private int id;
    private String name;
    private double price;
    private GoodType type;

    // Конструктоp
    public Good(int id, String name, double price, GoodType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public GoodType getType() {
        return type;
    }

    public void setType(GoodType type) {
        this.type = type;
    }


    // інфо про продукт
    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}