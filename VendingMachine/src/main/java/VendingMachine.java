import java.util.Arrays;

public class VendingMachine {
    private int userBalance;
    private final int[] ALLOWED_COINS = {1, 2, 5, 10};

    public int insertCoin(int coin) {
        if(Arrays.stream(ALLOWED_COINS).allMatch((item)-> item != coin))
            throw new RuntimeException("Invalid coin");
        this.userBalance += coin;

        return userBalance;
    }
}
