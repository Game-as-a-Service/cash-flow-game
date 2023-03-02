package tw.waterball.cashflow.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.waterball.cashflow.application.usecase.StartGameUseCase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class CashFlowGameController {

    private final StartGameUseCase startGameUseCase;

    @PostMapping("/start")
    public void start(@RequestBody Request request) {
        System.out.println("request = " + request);
//        var presenter = new PlayCardPresenter();
//        startGameUseCase.execute(request.toRequest(gameId), presenter);
//
//        return presenter.getViewModel()
//                        .map(ResponseEntity::ok)
//                        .orElseGet(noContent()::build);
    }

    @Value
    public static class Request {
        String roomId;
        List<String> actor;

//        public PlayCardRequest toRequest(String gameId) {
//            return new PlayCardRequest(gameId, playerId, handIndex, targetPlayerId, destinationCardIndex,
//                                       row, col, requireNonNullElse(flipped, false));
//        }
    }
}
