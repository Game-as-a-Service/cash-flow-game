package tw.waterball.cashflow.application.usecase;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tw.waterball.cashflow.application.usecase.ChooseDreamUseCase;
import tw.waterball.cashflow.domain.entity.Actor;
import tw.waterball.cashflow.domain.entity.Career;
import tw.waterball.cashflow.application.usecase.ChooseDreamUseCase.Input;
import tw.waterball.cashflow.application.repository.ActorRepository;
import tw.waterball.cashflow.domain.entity.exception.ActorNotFound;

@ExtendWith(MockitoExtension.class)
class ChooseDreamUseCaseTest {
  @InjectMocks
  private ChooseDreamUseCase chooseDreamUseCase;
  @Mock
  ActorRepository actorRepository;


  @Test
  void givenNotExistActor_whenChooseDream_thenStartIsNotStarted() {
    //Given
    String nickname = "name_1";
    Actor actor = new Actor(nickname, Career.Engineer);
    Input input = new Input(actor, 1, "開快艇競速");

    Mockito.when(actorRepository.findGameByActorName(nickname)).thenReturn(Optional.empty());

    //When,Then
    Assertions.assertThrows(ActorNotFound.class, () -> chooseDreamUseCase.execute(input));
  }

  @Test
  void givenActorAndDream_whenChooseDream_thenUpdateActorInfo(){
    //Given
    String nickname = "name_1";
    String dream = "開快艇競速";
    Actor actor = new Actor(nickname, Career.Engineer);
    Input input = new Input(actor, 1, dream);

    Mockito.when(actorRepository.findGameByActorName(nickname)).thenReturn( Optional.of(actor));

    //When
    chooseDreamUseCase.execute(input);

    //Then
    Assertions.assertEquals(dream, actor.getDream());
    Mockito.verify(actorRepository, Mockito.times(1)).save(actor);
  }

}