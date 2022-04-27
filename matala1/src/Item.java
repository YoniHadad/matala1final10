public class Item {
    private String itemName;
    private int itemNumber;
    private int itemPrice;
    private boolean inStock;
    private int sumInStock;
    private int discountPercentages;

    public Item(String itemName, int itemNumber, int itemPrice, boolean inStock, int sumInStock, int discountPercentages) {
        this.itemName = itemName;
        this.itemNumber = itemNumber;
        this.itemPrice = itemPrice;
        this.inStock = inStock;
        this.sumInStock = sumInStock;
        this.discountPercentages = discountPercentages;
    }

    public int getDiscountPercentages() {
        return discountPercentages;
    }

    public void setDiscountPercentages(int discountPercentages) {
        this.discountPercentages = discountPercentages;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getSumInStock() {
        return sumInStock;
    }

    public void setSumInStock(int sumInStock) {
        this.sumInStock = sumInStock;
    }

    public String toString(){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("Item name: ").append(this.itemName).append(", Item serial number: ").append(itemNumber);
        if (this.inStock){
            stringBuilder.append(" , item in stock!").append("(").append(this.sumInStock).append(" available) ");
        }else {
            stringBuilder.append("sorry this item out of stock!");
        }
        return stringBuilder.toString();
    }
}
