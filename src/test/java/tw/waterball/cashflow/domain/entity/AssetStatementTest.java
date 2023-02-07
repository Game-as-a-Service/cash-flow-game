package tw.waterball.cashflow.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AssetStatementTest {
    @Test
    void givenHouse_whenCheckExistence_thenReturnTrue() {
        // Given 一個房子資產
        AssetStatement asset = new AssetStatement();
        asset.addRealEstate(FinancialItem.builder("myID",
                                                  FinancialItemName.REAL_ESTATE_CONDO_2_BR_1_BA,
                                                  BigDecimal.valueOf(50000)).build());

        // When 檢查房子是否存在
        boolean existed = asset.realEstateExists(FinancialItemName.REAL_ESTATE_CONDO_2_BR_1_BA);

        // Then 房子存在
        Assertions.assertThat(existed).isTrue();
    }

    @Test
    void givenCondo_whenCheckHouseExistence_thenReturnFalse() {
        // Given 一個房子資產
        AssetStatement asset = new AssetStatement();
        asset.addRealEstate(FinancialItem.builder("myID",
                                                  FinancialItemName.REAL_ESTATE_CONDO_2_BR_1_BA,
                                                  BigDecimal.valueOf(50000)).build());

        // When 檢查房子是否存在
        boolean existed = asset.realEstateExists(FinancialItemName.REAL_ESTATE_HOUSE_3_BR_2_BA);

        // Then 房子不存在
        Assertions.assertThat(existed).isFalse();
    }
}
