import java.util.HashMap;
public class InventoryManager{
    private HashMap<Integer,Product> inventory=new HashMap<>();
    public void addProduct(Product product){
     inventory.put(product.getId(),product);
             System.out.println("Product Added");
    }
    public void updateProduct(int id,String name,int quantity,double price){
     Product product =inventory.get(id);
     if(product!=null){
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
             System.out.println("Product Updated");

     }
     else{
        System.out.println("Product Not Found");
     }
    }
    public void deleteProduct(int id){
        if(inventory.get(id)!=null){
            inventory.remove(id);
            System.out.println("Product deleted");
        }
        else{
        System.out.println("Product Not Found");
     }
    }
    public void displayProducts(){
        for(Product product:inventory.values()){
                        System.out.println(product);
        }
    }
}