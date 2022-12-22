package tw.waterball.cashflow.domain.entity;

import tw.waterball.cashflow.domain.entity.event.EventType;

import static tw.waterball.cashflow.domain.entity.event.EventType.*;

public class ChessBoard {
    private static final EventType[] IN_BOARD = {DEAL_OPPORTUNITY, EXTRA_PAYMENT,
                           DEAL_OPPORTUNITY, CHARITY,
            DEAL_OPPORTUNITY, SETTLEMENT_DATE,
            DEAL_OPPORTUNITY, EXTRA_PAYMENT,
            DEAL_OPPORTUNITY, EXTRA_PAYMENT,
            DEAL_OPPORTUNITY, CHILD,
            DEAL_OPPORTUNITY, SETTLEMENT_DATE,
            DEAL_OPPORTUNITY, EXTRA_PAYMENT,
            DEAL_OPPORTUNITY, EXTRA_PAYMENT,
            DEAL_OPPORTUNITY, UNEMPLOYMENT,
            DEAL_OPPORTUNITY, SETTLEMENT_DATE,
            DEAL_OPPORTUNITY, EXTRA_PAYMENT};

    public static EventType[] getInBoard() {
        return IN_BOARD;
    }
}
