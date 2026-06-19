public class Product{
    private int id;
    private String name;
    private int quantity;
    private double price;
    Product(int id,String name,int quantity,double price){
        this.id=id;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }
    public int getId(){
        return id;
    }
    public void setName(String productName) {
        this.name = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID=" + id +
               ", Name=" + name +
               ", Quantity=" + quantity +
               ", Price=" + price;
    }

}