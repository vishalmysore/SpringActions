package io.github.vishalmysore;


import io.github.vishalmysore.a2a.domain.AgentCard;
import io.github.vishalmysore.a2a.server.RealTimeAgentCardController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RealTimeAgentCardController.WELL_KNOWN_PATH)
public class MyA2ACardController extends RealTimeAgentCardController {


    @GetMapping(value = RealTimeAgentCardController.AGENT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentCard> getAgentCardForMyApp() {
        AgentCard card = getCachedAgentCard();
        return ResponseEntity.ok(card);

    }

}
