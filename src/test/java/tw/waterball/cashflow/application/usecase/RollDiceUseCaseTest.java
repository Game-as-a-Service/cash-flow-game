package tw.waterball.cashflow.application.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.ActorState;
import tw.waterball.cashflow.domain.entity.Career;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RollDiceUseCaseTest {
    @InjectMocks
    RollDiceUseCase useCase;

    @Mock
    ActorRepository actorRepository;

    /**
     * Given<br>
     * 玩家處於正常狀態，輪到玩家的回合且尚未擲骰過。<br>
     * When<br>
     * 玩家選擇要擲骰。<br>
     * Then<br>
     * 玩家可以擲骰。玩家可以在棋盤上前進擲出的數值。
     */
    @Test
    void giveActorWithNormalStateAndMyTurn_whenChooseToRollDice_thenActorMovedOnChessBoard() {
        // Given
        Actor actor = new Actor("actor", Career.ENGINEER);
        String actorId = actor.getActorId();
        int oldPosition = actor.getPosition();
        actor.setState(ActorState.MY_TURN);
        when(actorRepository.findByActorId(anyString())).thenReturn(Optional.of(actor));

        // When
        useCase.rollDice(actorId);

        // Then
        assertThat(oldPosition).isNotEqualTo(actor.getPosition());
        assertThat(actor.getState()).isEqualTo(ActorState.DICE_ROLLED);
    }

    /**
     * Given<br>
     * 玩家處於失業狀態，輪到玩家的回合。<br>
     * When<br>
     * 玩家選擇要擲骰。<br>
     * Then<br>
     * 什麼都不會發生。
     */
    @Test
    void giveActorWithUnemploymentStateAndMyTurn_whenChooseToRollDice_thenNothingHappened() {
        // Given
        Actor actor = new Actor("actor", Career.ENGINEER);
        String actorId = actor.getActorId();
        int oldPosition = actor.getPosition();
        actor.setState(ActorState.UNEMPLOYMENT);
        when(actorRepository.findByActorId(anyString())).thenReturn(Optional.of(actor));

        // When
        useCase.rollDice(actorId);

        // Then
        assertThat(oldPosition).isEqualTo(actor.getPosition()); // 棋盤仍停留原本位置
        assertThat(actor.getState()).isEqualTo(ActorState.UNEMPLOYMENT);
    }

    /**
     * Given<br>
     * 玩家處於破產狀態，輪到玩家的回合。<br>
     * When<br>
     * 玩家選擇要擲骰。<br>
     * Then<br>
     * 什麼都不會發生。
     */
    @Test
    void giveActorWithBankruptcyStateAndMyTurn_whenChooseToRollDice_thenNothingHappened() {
        // Given
        Actor actor = new Actor("actor", Career.ENGINEER);
        String actorId = actor.getActorId();
        int oldPosition = actor.getPosition();
        actor.setState(ActorState.BANKRUPTCY);
        when(actorRepository.findByActorId(anyString())).thenReturn(Optional.of(actor));

        // When
        useCase.rollDice(actorId);

        // Then
        assertThat(oldPosition).isEqualTo(actor.getPosition()); // 棋盤仍停留原本位置
        assertThat(actor.getState()).isEqualTo(ActorState.BANKRUPTCY);
    }
}
