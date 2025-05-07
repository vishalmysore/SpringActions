package io.github.vishalmysore;


import io.github.vishalmysore.a2a.domain.AgentCard;
import io.github.vishalmysore.a2a.server.RealTimeAgentCardController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known/")
public class MyA2ACardController extends RealTimeAgentCardController {


    @GetMapping(value = "/agent.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentCard> getAgentCardForMyApp() {
        AgentCard card = getCachedAgentCard();

        card.setUrl("http://localhost:7860"); //  Replace with actual URL
        return ResponseEntity.ok(card);

    }

}
