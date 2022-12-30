package tw.waterball.cashflow.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Optional;

@Builder
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FinancialItem {
    /**
     * Item ID。<br>
     * 可使用相同的 ID 建立關聯，例如收入的 item 與資產的 item 使用相同的 ID 來表示這兩個是有關聯的。
     */
    @Getter
    private final String id;

    @Getter
    private final FinancialItemName name;

    /**
     * 數量，選填，例如薪資就沒有數量。
     */
    private Integer count;

    /**
     * 單價。
     */
    @Getter
    @Setter
    private final BigDecimal amount;

    /**
     * @return 數量
     */
    public Optional<Integer> getCount()
    {
        return Optional.ofNullable(this.count);
    }

    /**
     * @param count 大於 0 的數量
     * @throws IllegalArgumentException if count <= 0
     */
    public void setCount(int count)
    {
        if(count <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0.");
        }

        this.count = count;
    }

    public static FinancialItemBuilder builder(String id, FinancialItemName name, BigDecimal amount)
    {
        return new FinancialItemBuilder().id(id).name(name).amount(amount);
    }
}
