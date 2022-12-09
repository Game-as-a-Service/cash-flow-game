package tw.waterball.cashflow.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;

public class StartGameUserCaseTest {

    @Test
    void startGame_whenNoPlayer_thenCantStart()
    {
        //Given
        StartGameUseCase game = new StartGameUseCase();

        //When
        boolean gameStarted = game.start();

        //Then or assert
        Assertions.assertFalse(gameStarted);
    }

    void startGame_whenEnoughPlayers_thenStartGame()
    {
        //Given
        Actor p1 = new Actor("nickname_1");
        Actor p2 = new Actor("nickname_2");
        StartGameUseCase game = new StartGameUseCase();
        game.add(p1);
        game.add(p2);

        //When
        boolean gameStarted = game.start();

        //Then
        Assertions.assertTrue(gameStarted);
    }
}
