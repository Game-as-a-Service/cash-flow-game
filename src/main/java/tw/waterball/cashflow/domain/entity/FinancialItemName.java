package tw.waterball.cashflow.domain.entity;

public enum FinancialItemName {
    SALARY_TEACHER_K_12,
    /**
     * 可轉讓定存單
     * @see <a href="https://rich01.com/what-is-cds/">https://rich01.com/what-is-cds/</a>
     */
    CD,
    /**
     * 房地產 - 2/1 CONDO
     */
    REAL_ESTATE_CONDO_2_BEDS_1_BATH,
    /**
     * 稅
     */
    TAXES,
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
