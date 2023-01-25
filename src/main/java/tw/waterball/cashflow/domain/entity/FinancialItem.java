package tw.waterball.cashflow.domain.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@ToString
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
     * 單價。
     */
    @Getter
    private BigDecimal amount;

    /**
     * 數量。
     */
    @Getter
    private int count;

    private FinancialItem(String id, FinancialItemName name, BigDecimal amount, int count) {
        this.id = id;
        this.name = name;
        setAmount(amount);
        setCount(count);
    }

    /**
     * @param amount 大於 0 的單價金額
     * @throws IllegalArgumentException if amount < 1
     */
    public void setAmount(BigDecimal amount) {
        if(Objects.isNull(amount)) {
            throw new IllegalArgumentException("Amount can't be null.");
        }
        this.amount = amount;
    }

    /**
     * @param count 大於 0 的數量
     * @throws IllegalArgumentException if count < 1
     */
    public void setCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count must > 0.");
        }
        this.count = count;
    }

    /**
     * @return 總金額 = 單價 * 數量
     *
     * @see #getAmount()
     * @see #getCount()
     */
    public BigDecimal getTotalAmount() {
        return this.amount.multiply(BigDecimal.valueOf(this.count));
    }

    public static FinancialItemBuilder builder(@NonNull String id, @NonNull FinancialItemName name, @NonNull BigDecimal amount) {
        return new FinancialItemBuilder(id, name, amount);
    }

    public static final class FinancialItemBuilder {
        private String id;
        private FinancialItemName name;
        private BigDecimal amount;
        private int count = 1;

        private FinancialItemBuilder(String id, FinancialItemName name, BigDecimal amount) {
            this.id = id;
            this.name = name;
            this.amount = amount;
        }

        public FinancialItemBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * 設定數量，預設 1。
         * @param count 數量
         * @return builder
         */
        public FinancialItemBuilder count(int count) {
            this.count = count;
            return this;
        }

        public FinancialItem build() {
            return new FinancialItem(id, name, amount, count);
        }
    }
}
