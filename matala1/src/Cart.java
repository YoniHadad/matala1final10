public class Cart {
    private User user;
    private double sumOfPurchase;
    private double numberOfPurchase;
    private Item item;

    public Cart(User user, double sumOfPurchase, double numberOfPurchase, Item item) {
        this.user = user;
        this.sumOfPurchase = sumOfPurchase;
        this.numberOfPurchase = numberOfPurchase;
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getSumOfPurchase() {
        return sumOfPurchase;
    }

    public void setSumOfPurchase(double sumOfPurchase) {
        this.sumOfPurchase = sumOfPurchase;
    }

    public double getNumberOfPurchase() {
        return numberOfPurchase;
    }

    public void setNumberOfPurchase(double numberOfPurchase) {
        this.numberOfPurchase = numberOfPurchase;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


}
