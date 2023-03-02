package tw.waterball.cashflow.domain.entity;

public enum ActorState {
    /**
     * 無指定狀態
     */
    NONE,
    /**
     * 輪到我的回合，尚未擲骰子。
     */
    MY_TURN,
    /**
     * 已擲骰
     */
    DICE_ROLLED,
    /**
     * 失業中
     */
    UNEMPLOYMENT,
    /**
     * 已破展
     */
    BANKRUPTCY,
    /**
     * 慈善事件
     */
    CHARITY // TODO 似乎不需要這種狀態
}
