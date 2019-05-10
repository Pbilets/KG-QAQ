package Eshop.Goods;

public enum GoodType {

    BOOK, MAGAZINE, COMIX;


    public static GoodType getGoodType(String type){
        switch (type){
            case "книга":{
                return BOOK;
            }
            case "журнал":{
                return MAGAZINE;
            }
            case "комікс":{
                return COMIX;
            }
            default:{
                throw new RuntimeException("Хибний тип товару");
            }
        }
    }
}
