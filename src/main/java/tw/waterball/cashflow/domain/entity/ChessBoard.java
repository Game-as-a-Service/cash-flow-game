package tw.waterball.cashflow.domain.entity;

import tw.waterball.cashflow.domain.entity.event.EventType;
import tw.waterball.cashflow.domain.entity.exception.BoardSpaceNotExist;

import static tw.waterball.cashflow.domain.entity.event.EventType.*;

public final class ChessBoard {
    private ChessBoard() {
        throw new IllegalStateException("Utility class");
    }

    private static final EventType[] IN_BOARD = {DEAL_OPPORTUNITY, EXTRA_PAYMENT, DEAL_OPPORTUNITY, CHARITY, DEAL_OPPORTUNITY, SETTLEMENT_DATE, DEAL_OPPORTUNITY, MARKET, DEAL_OPPORTUNITY, EXTRA_PAYMENT, DEAL_OPPORTUNITY, CHILD, DEAL_OPPORTUNITY, SETTLEMENT_DATE, DEAL_OPPORTUNITY, MARKET, DEAL_OPPORTUNITY, EXTRA_PAYMENT, DEAL_OPPORTUNITY, UNEMPLOYMENT, DEAL_OPPORTUNITY, SETTLEMENT_DATE, DEAL_OPPORTUNITY, MARKET};


    public static void move(Actor actor, int diceNumber) {
        int spaceNumber = actor.getPosition() + diceNumber;
        if (spaceNumber >= IN_BOARD.length) {
            // 繞一圈
            spaceNumber = spaceNumber - IN_BOARD.length;
        }
        actor.setPosition(spaceNumber);
    }

    public static EventType getBoardEvent(int spaceNumber) {
        if (spaceNumber > IN_BOARD.length) {
            throw new BoardSpaceNotExist("格子數不存在，" + spaceNumber + " is over " + IN_BOARD.length);
        }
        return IN_BOARD[spaceNumber];
    }
}
