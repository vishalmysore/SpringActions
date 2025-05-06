package io.github.vishalmysore;


import com.t4a.processor.AIProcessor;
import com.t4a.processor.OpenAiActionProcessor;
import io.github.vishalmysore.mcp.domain.*;
import io.github.vishalmysore.mcp.server.MCPToolsController;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Log
@RestController
@RequestMapping("/mcp")
public class MCPController extends MCPToolsController {

    OpenAiActionProcessor processor = new OpenAiActionProcessor();

    /**
     * By default it uses Gemini , here you can configure OpenAI processor and set the
     * key in tools4ai.prooperties or set as VM -DopenAiKey parameter
     * @return
     */
    @Override
    public AIProcessor getBaseProcessor() {
        return processor;
    }

    SseEmitter lastEmitter;
   // @GetMapping("/sse")
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter(0L); // no timeout
        log.info("Client connected to /sse");

        // Store the emitter if needed to push events later (optional)
        this.lastEmitter = emitter;

        // Send initialization message (e.g., list of tools)
        try {
            Map<String, Object> initMsg = new HashMap<>();
            initMsg.put("type", "tool_list");
            initMsg.put("tools", super.getToolsResult().getTools());

            emitter.send(SseEmitter.event()
                    .name("message")
                    .data(initMsg));
        } catch (Exception e) {
            log.warning("Error sending SSE event: " + e.getMessage());
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @GetMapping("/list-tools")
    public ResponseEntity<Map<String, List<Tool>>> listTools() {
        Map<String, List<Tool>> response = new HashMap<>();
        response.put("tools", super.getToolsResult().getTools());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/call-tool")
    public ResponseEntity<JSONRPCResponse> callTool(@RequestBody ToolCallRequest request) {
//        log.info("Received request: " + request);
//
//        // Construct the actual business response
//        CallToolResult result = new CallToolResult();
//        TextContent content = new TextContent();
//        content.setText("created");
//        content.setType("text");
//
//        List<Content> contentList = new ArrayList<>();
//        contentList.add(content);
//        result.setContent(contentList);
//
//        // Wrap in JSON-RPC envelope
//        JSONRPCResponse response = new JSONRPCResponse();
//        response.setId("133");
//        //response.setId(request.get);
//        response.setResult(result);
//
//        return ResponseEntity.ok(response);

         CallToolResult result = super.callTool(request, new CustomTaskCallback());
              log.info("Received result: " + result);
        JSONRPCResponse response = new JSONRPCResponse();
        response.setId("133");
        response.setResult(result);
        return ResponseEntity.ok(response);
       }

    @PostMapping("/cancel-notification")
    public ResponseEntity<CallToolResult> cancelNotification(@RequestBody CancelledNotification request) {
        log.info("Received cancel notification for: " + request);



        return ResponseEntity.ok(null);
    }
}
