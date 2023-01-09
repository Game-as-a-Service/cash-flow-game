package tw.waterball.cashflow.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static tw.waterball.cashflow.domain.entity.Career.ENGINEER;

class ChessBoardTest {
    @Test
    void givenPlayerPosition1_whenMove3_thenReturn4() {
        // Given
        Actor actor = new Actor("玩家A", ENGINEER);
        actor.setPosition(1);

        // When
        ChessBoard.move(actor, 3);

        // Then
        Assertions.assertEquals(4, actor.getPosition());
    }

    @Test
    void givenPlayerPosition20_whenMove6_thenReturn2() {
        // Given 玩家A
        Actor actor = new Actor("玩家A", ENGINEER);
        actor.setPosition(20);

        // When 玩家A擲骰子，並走到銀行結算日格子
        ChessBoard.move(actor, 6);

        // Then 領取
        Assertions.assertEquals(2, actor.getPosition());
    }

}
