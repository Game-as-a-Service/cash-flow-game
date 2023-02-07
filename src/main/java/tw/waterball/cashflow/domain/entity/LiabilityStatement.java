package tw.waterball.cashflow.domain.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class LiabilityStatement {
    private Map<String, FinancialItem> basicLiabilityMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> realEstateMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> businessMap = new TreeMap<>(); // Map<Item ID, Financial Item>

    /**
     * 增加除了房地產與商業以外的負債項目。
     *
     * @param liabilityItem 股票 item
     */
    public void addBasicLiability(FinancialItem liabilityItem) {
        this.basicLiabilityMap.put(liabilityItem.getId(), liabilityItem);
    }

    public Optional<FinancialItem> getBasicLiability(String id) {
        return Optional.ofNullable(this.basicLiabilityMap.get(id));
    }

    /**
     * 增加房地產負債項目
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
     * 增加商業負債項目
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
     * @return 所有的基本負債項目
     */
    public Collection<FinancialItem> getAllBasicLiabilities() {
        return Collections.unmodifiableCollection(this.basicLiabilityMap.values());
    }

    /**
     * @return 所有的房地產負債項目
     */
    public Collection<FinancialItem> getAllRealEstates() {
        return Collections.unmodifiableCollection(this.realEstateMap.values());
    }

    /**
     * @return 所有的商業負債項目
     */
    public Collection<FinancialItem> getAllBusinesses() {
        return Collections.unmodifiableCollection(this.businessMap.values());
    }

    /**
     * 刪除指定的負債項目。
     *
     * @param id item ID
     */
    public void removeLiability(String id) {
        if (Objects.nonNull(this.basicLiabilityMap.remove(id))) {
            return;
        }

        if (Objects.nonNull(this.realEstateMap.remove(id))) {
            return;
        }

        this.businessMap.remove(id);
    }

    /**
     * @return 總負債
     */
    public BigDecimal getTotalLiabilityAmount() {
        return basicLiabilityMap.values()
                                .stream()
                                .map(FinancialItem::getTotalAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .add(realEstateMap.values().stream().map(FinancialItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
                                .add(businessMap.values().stream().map(FinancialItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @Override
    public String toString() {
        return "LiabilityStatement{" + "basicLiabilities=" + basicLiabilityMap.values() + ", realEstates=" + realEstateMap.values() + ", businesses=" + businessMap.values() + '}';
    }
}
