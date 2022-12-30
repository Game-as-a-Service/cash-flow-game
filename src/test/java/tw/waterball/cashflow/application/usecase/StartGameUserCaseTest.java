package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.income.Income;
import tw.waterball.cashflow.domain.entity.income.IncomeType;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StartGameUserCaseTest {

    @Test
    void givenNoPlayer_whenStartGame_thenStartIsNotStarted()
    {
        //Given
        StartGameUseCase game = new StartGameUseCase();

        //When
        boolean gameStarted = game.start();

        //Then or assert
        assertThat(gameStarted).isFalse();
    }

    @Test
    void givenEqualOrGreaterOnePlayer_whenStartGame_thenGameStarted()
    {
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
    void givenEngineerPlayerAndTeacherPlayer_whenStartGame_thenInitializeEachFinancialStatement()
    {
        //Given
        Actor engineer = new Actor("name_1", Career.ENGINEER);
        Actor teacher = new Actor("name_2", Career.TEACHER);

        StartGameUseCase game = new StartGameUseCase();
        game.add(engineer);
        game.add(teacher);

        //When
        game.start();

        //Then
        Optional<Income> engineerSalary = engineer.getFinancialStatement().getIncome(IncomeType.SALARY);
        assertThat(engineerSalary).isNotEmpty();
        assertThat(engineerSalary.get().getAmount()).isEqualTo(BigDecimal.valueOf(2500));
        assertThat(engineer.getFinancialStatement().getTotalExpenseAmount()).isEqualTo(BigDecimal.valueOf(1590));
        assertThat(engineer.getFinancialStatement().getTotalAssetAmount()).isEqualTo(BigDecimal.valueOf(0));
        assertThat(engineer.getFinancialStatement().getTotalLiabilityAmount()).isEqualTo(BigDecimal.valueOf(45000));

        Optional<Income> teacherSalary = teacher.getFinancialStatement().getIncome(IncomeType.SALARY);
        assertThat(teacherSalary).isNotEmpty();
        assertThat(teacherSalary.get().getAmount()).isEqualTo(BigDecimal.valueOf(1500));
        assertThat(teacher.getFinancialStatement().getTotalExpenseAmount()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(teacher.getFinancialStatement().getTotalAssetAmount()).isEqualTo(BigDecimal.valueOf(0));
        assertThat(teacher.getFinancialStatement().getTotalLiabilityAmount()).isEqualTo(BigDecimal.valueOf(25000));
    }
}