package tw.waterball.cashflow.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Player p1 = new Player("nickname_1", "dream_1");
        Player p2 = new Player("nickname_2", "dream_2");
        StartGameUseCase game = new StartGameUseCase();
        game.add(p1, p2);

        //When
        boolean gameStarted = game.start();

        //Then
        Assertions.assertTrue(gameStarted);
    }
}
