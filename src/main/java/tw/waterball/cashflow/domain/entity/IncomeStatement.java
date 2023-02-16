package tw.waterball.cashflow.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

@RequiredArgsConstructor
public class IncomeStatement {
    @Getter
    private final FinancialItem salary;
    private Map<String, FinancialItem> interestMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> dividendMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> realEstateMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> businessMap = new TreeMap<>(); // Map<Item ID, Financial Item>

    /**
     * 增加利息收入項目
     *
     * @param interestItem 利息 item
     */
    public void addInterest(FinancialItem interestItem) {
        this.interestMap.put(interestItem.getId(), interestItem);
    }

    /**
     * @return 所有的利息收入項目
     */
    public Collection<FinancialItem> getAllInterests() {
        return Collections.unmodifiableCollection(this.interestMap.values());
    }

    /**
     * 增加分紅收入項目
     *
     * @param dividendItem 分紅 item
     */
    public void addDividend(FinancialItem dividendItem) {
        this.dividendMap.put(dividendItem.getId(), dividendItem);
    }

    /**
     * @return 所有的分紅收入項目
     */
    public Collection<FinancialItem> getAllDividends() {
        return Collections.unmodifiableCollection(this.dividendMap.values());
    }

    /**
     * 增加房地產收入項目
     *
     * @param realEstateItem 房地產 item
     */
    public void addRealEstate(FinancialItem realEstateItem) {
        this.realEstateMap.put(realEstateItem.getId(), realEstateItem);
    }

    public Optional<FinancialItem> getRealEstate(String id) {
        return Optional.ofNullable(this.realEstateMap.get(id));
    }

    /**
     * @return 所有的房地產收入項目
     */
    public Collection<FinancialItem> getAllRealEstates() {
        return Collections.unmodifiableCollection(this.realEstateMap.values());
    }

    /**
     * 增加商業收入項目
     *
     * @param businessItem 商業 item
     */
    public void addBusiness(FinancialItem businessItem) {
        this.businessMap.put(businessItem.getId(), businessItem);
    }

    public Optional<FinancialItem> getBusiness(String id) {
        return Optional.ofNullable(this.businessMap.get(id));
    }

    /**
     * @return 所有的商業收入項目
     */
    public Collection<FinancialItem> getAllBusinesses() {
        return Collections.unmodifiableCollection(this.businessMap.values());
    }

    /**
     * 刪除指定的收入項目。
     *
     * @param id 收入 item ID
     */
    public void removeIncome(String id, int count) {
        if (Objects.nonNull(this.interestMap.remove(id))) {
            return;
        }
        if (Objects.nonNull(this.realEstateMap.remove(id))) {
            return;
        }
        if (Objects.nonNull(this.businessMap.remove(id))) {
            return;
        }
        FinancialItem dividendItem = this.dividendMap.get(id);
        if (Objects.nonNull(dividendItem)) {
            int finalCount = dividendItem.getCount() - count;
            if(finalCount == 0){
                dividendMap.remove(id);
            }else {
                dividendItem.setCount(finalCount);
            }
        }


    }

    /**
     * @return 總被動收入金額
     */
    public BigDecimal getTotalPassiveIncomeAmount() {
        BigDecimal totalInterestAmount = interestMap.values().stream().map(FinancialItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDividendAmount = dividendMap.values().stream().map(FinancialItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalRealEstateAmount = realEstateMap.values().stream().map(FinancialItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalBusinessAmount = businessMap.values().stream().map(FinancialItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalInterestAmount.add(totalDividendAmount).add(totalRealEstateAmount).add(totalBusinessAmount);
    }

    public BigDecimal getTotalIncomeAmount() {
        return this.salary.getAmount().add(getTotalPassiveIncomeAmount());
    }

    @Override
    public String toString() {
        return "IncomeStatement{" + "salary=" + salary + ", interests=" + interestMap.values() + ", dividends=" + dividendMap.values() + ", realEstates=" + realEstateMap.values() + ", businesses=" + businessMap.values() + '}';
    }
}
