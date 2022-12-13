package tw.waterball.cashflow.usecase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class ChooseDreamUseCaseTest {


  @BeforeEach
  void setUp() {
    // Given
    ChooseDreamUseCase chooseDreamUseCase = new ChooseDreamUseCase();
//        遊戲已啟動
    // When
    chooseDreamUseCase.execute(1,"開快艇競速");
    // Then
//    系統記錄所選夢想，畫面釘選所選夢想的格子。
    // Player 放哪??

  }

}