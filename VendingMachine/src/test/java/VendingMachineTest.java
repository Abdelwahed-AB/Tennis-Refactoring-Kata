import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VendingMachineTest {
    public VendingMachine vendingMachine = new VendingMachine();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    @DisplayName("Should accept coins of value 1, 2, 5 and 10 dirhams")
    public void should_accept_coins_of_value_1_2_5_10(int coin){
        Assertions.assertDoesNotThrow(()->
                vendingMachine.insertCoin(coin));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 6, 7})
    @DisplayName("Should throw when passed invalid coin value")
    public void should_throw_when_passed_invalid_coin_value(int coin){
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, ()->vendingMachine.insertCoin(coin));

        Assertions.assertEquals("Invalid coin", exception.getMessage());
    }

    @Test
    @DisplayName("Should allow user to select products based on their id")
    public void should_allow_user_to_select_products_based_on_id(){
        int productId = ProductsEnum.WATER.id;
        Assertions.assertDoesNotThrow(()->vendingMachine.selectProduct(productId));
    }

    @Test
    @DisplayName("Should throw when passed an invalid product id")
    public void should_throw_when_passed_invalid_product_id(){
        int invalidProductId = 99999;
        Assertions.assertThrows(RuntimeException.class, ()->vendingMachine.selectProduct(invalidProductId));
    }
}
