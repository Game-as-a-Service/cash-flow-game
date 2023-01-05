package tw.waterball.cashflow.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class FinancialItemTest {
    @Test
    void testCreateItem()
    {
        String id = String.valueOf(System.currentTimeMillis());
        FinancialItemName itemName = FinancialItemName.CD;
        BigDecimal amount = BigDecimal.TEN;
        FinancialItem item = FinancialItem.builder(id, itemName, amount).build();

        assertThat(item.getId()).isEqualTo(id);
        assertThat(item.getName()).isEqualTo(FinancialItemName.CD);
        assertThat(item.getAmount()).isEqualTo(BigDecimal.TEN);
    }
}
