package machine;
 enum CoffeeType {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350,75,20,7),
    CAPPUCCINO(200, 100, 12, 6);

    private int amountOfWatter;
    private int amountOfMilk;
    private int amountOfBeans;
    private int price;

    CoffeeType(int water, int milk, int beans, int price) {
        this.amountOfWatter = water;
        this.amountOfMilk = milk;
        this.amountOfBeans = beans;
        this.price = price;
    }

     public int getAmountOfWatter() {
         return amountOfWatter;
     }

     public int getAmountOfMilk() {
         return amountOfMilk;
     }

     public int getAmountOfBeans() {
         return amountOfBeans;
     }

     public int getPrice() {
         return price;
     }
 }
