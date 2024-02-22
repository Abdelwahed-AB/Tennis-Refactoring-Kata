import java.util.*;

public class VendingMachine {
    private final int[] ALLOWED_COINS = {1, 2, 5, 10};
    private Map<Integer, Product> products;

    private int userBalance;
    private List<Product> userSelectedProducts;
    public VendingMachine(){
        products = new HashMap<>();

        for (ProductsEnum prod : ProductsEnum.values()){
            products.put(prod.id, new Product(prod.name(), prod.price, prod.id));
        }
    }

    public int insertCoin(int coin) {
        if(Arrays.stream(ALLOWED_COINS).allMatch((item)-> item != coin))
            throw new RuntimeException("Invalid coin");
        this.userBalance += coin;

        return userBalance;
    }

    public void selectProduct(int productId) {
        if(userSelectedProducts == null){
            userSelectedProducts = new ArrayList<>();
        }

        Product selectedProduct = products.get(productId);
        if(selectedProduct == null){
            throw new RuntimeException("Invalid product id");
        }

        this.userSelectedProducts.add(selectedProduct);
    }
}
