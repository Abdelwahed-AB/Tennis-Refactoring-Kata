public enum ProductsEnum {
    WATER(1, 5),
    COCA(2, 7),
    TWIX(3, 10),
    BUENO(4, 12);

    int id;
    int price;
    ProductsEnum(int id, int price){
        this.id = id;
        this.price = price;
    }
}
