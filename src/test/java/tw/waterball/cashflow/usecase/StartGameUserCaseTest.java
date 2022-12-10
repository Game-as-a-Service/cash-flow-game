package tw.waterball.cashflow.usecase;

import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.domain.entity.income.Income;
import tw.waterball.cashflow.domain.entity.income.IncomeType;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StartGameUserCaseTest {

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
        Actor actor1 = new Actor("nickname_1", Career.Engineer);
        Actor actor2 = new Actor("nickname_2", Career.Teacher);
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
        Actor engineer = new Actor("nickname_1", Career.Engineer);
        Actor teacher = new Actor("nickname_2", Career.Teacher);

        StartGameUseCase game = new StartGameUseCase();
        game.add(engineer);
        game.add(teacher);

        //When
        game.start();

        //Then
        Optional<Income> engineerSalary = engineer.getFinancialStatement().getIncome(IncomeType.Salary);
        assertThat(engineerSalary).isNotEmpty();
        assertThat(engineerSalary.get().getAmount()).isEqualTo(2500);
        assertThat(engineer.getFinancialStatement().getTotalExpenseAmount()).isEqualTo(1590);
        assertThat(engineer.getFinancialStatement().getTotalAssetAmount()).isEqualTo(0);
        assertThat(engineer.getFinancialStatement().getTotalLiabilityAmount()).isEqualTo(45000);

        Optional<Income> teacherSalary = teacher.getFinancialStatement().getIncome(IncomeType.Salary);
        assertThat(teacherSalary).isNotEmpty();
        assertThat(teacherSalary.get().getAmount()).isEqualTo(1500);
        assertThat(teacher.getFinancialStatement().getTotalExpenseAmount()).isEqualTo(1000);
        assertThat(teacher.getFinancialStatement().getTotalAssetAmount()).isEqualTo(0);
        assertThat(teacher.getFinancialStatement().getTotalLiabilityAmount()).isEqualTo(25000);
    }
}
