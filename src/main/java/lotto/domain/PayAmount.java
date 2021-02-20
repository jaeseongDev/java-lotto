package lotto.domain;

public class PayAmount {

    private static final int LOTTO_PAY = 1000;
    private static final int MIN_PAY_AMOUNT = 0;
    private static final int ZeroRemainder = 0;

    private final int payAmount;

    private PayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public static PayAmount from(String payAmount) {
        if (!isInteger(payAmount)) {
            throw new IllegalArgumentException();
        }

        int pay = Integer.parseInt(payAmount);
        if (!isZeroOrPositive(pay) || !isMultipleOfLottoPay(pay)) {
            throw new IllegalArgumentException();
        }

        return new PayAmount(pay);
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private static boolean isZeroOrPositive(int value) {
        return value >= MIN_PAY_AMOUNT;
    }

    private static boolean isMultipleOfLottoPay(int value) {
        return value % LOTTO_PAY == ZeroRemainder;
    }

    public LottoCount getLottoCount() {
        return new LottoCount(payAmount / LOTTO_PAY);
    }
}
