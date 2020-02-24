package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.model.YieldMoney.countYieldMoney;
import static org.assertj.core.api.Assertions.assertThat;

public class YieldTest {

    @Test
    @DisplayName("수익률 확인")
    void yieldCorrectTest() {
        Payment payment = new Payment(14000);
        assertThat(countYieldMoney(payment, 5000)).isEqualTo(35);
    }
}
