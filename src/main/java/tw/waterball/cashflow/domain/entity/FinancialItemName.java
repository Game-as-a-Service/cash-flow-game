package tw.waterball.cashflow.domain.entity;

public enum FinancialItemName {
    SALARY,
    /**
     * 可轉讓定存單
     * @see <a href="https://rich01.com/what-is-cds/">https://rich01.com/what-is-cds/</a>
     */
    CD,
    /**
     * 房地產 - 2 防 + 1 衛浴 CONDO
     */
    REAL_ESTATE_CONDO_2_BR_1_BA,

    /**
     * 房地產 - 3 防 + 2 衛浴 house
     */
    REAL_ESTATE_HOUSE_3_BR_2_BA,
    /**
     * 稅
     */
    TAX,
    /**
     * 房屋抵押
     */
    HOME_MORTGAGE,
    CHILD,
    CAR_LOAN,
    CREDIT_CARD,
    RETAIL,
    BOAT,
    LOAN,
    /**
     * 股票 - ON2U
     */
    STOCK_ON2U,
    /**
     * 股票 - OK4U
     */
    STOCK_OK4U,
    OTHER
}
