package tw.waterball.cashflow.domain.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class AssetStatement {
    private Map<String, FinancialItem> stockMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> fundMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> cdMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> realEstateMap = new TreeMap<>(); // Map<Item ID, Financial Item>
    private Map<String, FinancialItem> businessMap = new TreeMap<>(); // Map<Item ID, Financial Item>

    /**
     * 增加股票項目。
     *
     * @param stockItem 股票 item
     */
    public void addStock(FinancialItem stockItem) {
        this.stockMap.put(stockItem.getId(), stockItem);
    }

    public Optional<FinancialItem> getStock(String id) {
        return Optional.ofNullable(this.stockMap.get(id));
    }

    /**
     * 增加 fund 項目。
     *
     * @param fundItem fund item
     */
    public void addFund(FinancialItem fundItem) {
        this.fundMap.put(fundItem.getId(), fundItem);
    }

    public Optional<FinancialItem> getFund(String id) {
        return Optional.ofNullable(this.fundMap.get(id));
    }

    /**
     * 增加 CD 項目。
     *
     * @param cdItem CD item
     * @see FinancialItemName#CD
     *
     */
    public void addCD(FinancialItem cdItem) {
        this.cdMap.put(cdItem.getId(), cdItem);
    }

    public Optional<FinancialItem> getCD(String id) {
        return Optional.ofNullable(this.cdMap.get(id));
    }

    /**
     * 增加房地產資產項目
     *
     * @param realEstateItem 房地產 item
     */
    public void addRealEstate(FinancialItem realEstateItem) {
        this.realEstateMap.put(realEstateItem.getId(), realEstateItem);
    }

    /**
     * 增加商業資產項目
     *
     * @param businessItem 商業 item
     */
    public void addBusiness(FinancialItem businessItem) {
        this.businessMap.put(businessItem.getId(), businessItem);
    }

    /**
     * @return 所有的股票資產項目
     */
    public Collection<FinancialItem> getAllStocks() {
        return Collections.unmodifiableCollection(this.stockMap.values());
    }

    /**
     * @return 所有的 fund 資產項目
     */
    public Collection<FinancialItem> getAllFunds() {
        return Collections.unmodifiableCollection(this.fundMap.values());
    }

    /**
     * @return 所有的 CD 資產項目
     */
    public Collection<FinancialItem> getAllCDs() {
        return Collections.unmodifiableCollection(this.cdMap.values());
    }

    /**
     * @return 所有的房地產資產項目
     */
    public Collection<FinancialItem> getAllRealEstates() {
        return Collections.unmodifiableCollection(this.realEstateMap.values());
    }

    /**
     * @return 所有的商業資產項目
     */
    public Collection<FinancialItem> getAllBusinesses() {
        return Collections.unmodifiableCollection(this.businessMap.values());
    }

    /**
     * 刪除指定的資產項目。
     *
     * @param id 資產 item ID
     */
    public void removeAsset(String id) {
        if (Objects.nonNull(this.stockMap.remove(id))) {
            return;
        }

        if (Objects.nonNull(this.fundMap.remove(id))) {
            return;
        }

        if (Objects.nonNull(this.cdMap.remove(id))) {
            return;
        }

        if (Objects.nonNull(this.realEstateMap.remove(id))) {
            return;
        }

        this.businessMap.remove(id);
    }

    @Override
    public String toString() {
        return "AssetStatement{" + "stocks=" + stockMap.values() + ", funds=" + fundMap.values() + ", CDs=" + cdMap.values() + ", realEstates=" + realEstateMap.values() + ", businesses=" + businessMap.values() + '}';
    }
}
