package tw.waterball.cashflow.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tw.waterball.cashflow.domain.entity.Actor;

class StartGameUserCaseTest {

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
//        //Given
//        Actor actor1 = new Actor("nickname_1", "dream_1");
//        Actor actor2 = new Actor("nickname_2", "dream_2");
//        StartGameUseCase game = new StartGameUseCase();
//        game.add(actor1, actor2);
//
//        //When
//        boolean gameStarted = game.start();
//
//        //Then
//        Assertions.assertTrue(gameStarted);
    }
}
