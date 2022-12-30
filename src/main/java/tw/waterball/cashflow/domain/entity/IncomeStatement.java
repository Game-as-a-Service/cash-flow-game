package tw.waterball.cashflow.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
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

    /**
     * @return 所有的房地產收入項目
     */
    public Collection<FinancialItem> getAllRealEstates() {
        return Collections.unmodifiableCollection(this.dividendMap.values());
    }

    /**
     * 增加商業收入項目
     *
     * @param businessItem 商業 item
     */
    public void addBusiness(FinancialItem businessItem) {
        this.businessMap.put(businessItem.getId(), businessItem);
    }

    /**
     * @return 所有的商業收入項目
     */
    public Collection<FinancialItem> getAllBusinesses() {
        return Collections.unmodifiableCollection(this.businessMap.values());
    }

    /**
     * 根據 item ID 期除指定的收入項目。
     *
     * @param id 收入 item ID
     */
    public void removeIncome(String id) {
        if (Objects.nonNull(this.interestMap.remove(id))) {
            return;
        }

        if (Objects.nonNull(this.dividendMap.remove(id))) {
            return;
        }

        if (Objects.nonNull(this.realEstateMap.remove(id))) {
            return;
        }

        this.businessMap.remove(id);
    }

    /**
     * @return 總被動收入金額
     */
    public BigDecimal getTotalPassiveIncomeAmount() {
        BigDecimal totalInterestAmount = interestMap.values().stream().map(FinancialItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDividendAmount = dividendMap.values().stream().map(FinancialItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalRealEstateAmount = realEstateMap.values().stream().map(FinancialItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalBusinessAmount = businessMap.values().stream().map(FinancialItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalInterestAmount.add(totalDividendAmount).add(totalRealEstateAmount).add(totalBusinessAmount);
    }
}