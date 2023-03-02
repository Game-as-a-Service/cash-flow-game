package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.FinancialStatementV2;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StartGameUseCaseTest {

    @Test
    void givenNoPlayer_whenStartGame_thenStartIsNotStarted() {
        //Given
        StartGameUseCase game = new StartGameUseCase();

        //When
        boolean gameStarted = game.start();

        //Then or assert
        assertThat(gameStarted).isFalse();
    }

    @Test
    void givenEqualOrGreaterOnePlayer_whenStartGame_thenGameStarted() {
        //Given
        Actor actor1 = new Actor("name_1", Career.ENGINEER);
        Actor actor2 = new Actor("name_2", Career.TEACHER);
        StartGameUseCase game = new StartGameUseCase();
        game.add(actor1);
        game.add(actor2);

        //When
        boolean gameStarted = game.start();

        //Then
        assertThat(gameStarted).isTrue();
    }

    @Test
    void givenEngineerPlayerAndTeacherPlayer_whenStartGame_thenInitializeEachFinancialStatement() {
        //Given
        Actor engineer = new Actor("name_1", Career.ENGINEER);
        Actor teacher = new Actor("name_2", Career.TEACHER);

        StartGameUseCase game = new StartGameUseCase();
        game.add(engineer);
        game.add(teacher);

        //When
        game.start();

        //Then
        FinancialStatementV2 engineerFinancialStatement = engineer.getFinancialStatementV2();
        assertEquals(engineerFinancialStatement.getIncome().getTotalIncomeAmount(), BigDecimal.valueOf(4900));
        assertEquals(engineerFinancialStatement.getExpense().getTotalExpenseAmount(), BigDecimal.valueOf(3150));
        assertEquals(engineerFinancialStatement.getAsset().getTotalAssetAmount(), BigDecimal.valueOf(0));
        assertEquals(engineerFinancialStatement.getLiability().getTotalLiabilityAmount(), BigDecimal.valueOf(87000));

        FinancialStatementV2 teacherFinancialStatement = teacher.getFinancialStatementV2();
        assertEquals(teacherFinancialStatement.getIncome().getTotalIncomeAmount(), BigDecimal.valueOf(3300));
        assertEquals(teacherFinancialStatement.getExpense().getTotalExpenseAmount(), BigDecimal.valueOf(2130));
        assertEquals(teacherFinancialStatement.getAsset().getTotalAssetAmount(), BigDecimal.valueOf(0));
        assertEquals(teacherFinancialStatement.getLiability().getTotalLiabilityAmount(), BigDecimal.valueOf(59000));
    }
}