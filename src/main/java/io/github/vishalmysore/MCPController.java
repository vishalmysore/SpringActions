package io.github.vishalmysore;


import com.t4a.processor.AIProcessor;
import com.t4a.processor.OpenAiActionProcessor;
import io.github.vishalmysore.common.MCPActionCallback;
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
@RequestMapping("/v1")
public class MCPController extends MCPToolsController {





    @GetMapping("/tools")
    public ResponseEntity<Map<String, List<Tool>>> listTools() {
       return super.listTools();
    }

    @PostMapping("/tools/call")
    public ResponseEntity<JSONRPCResponse> callTool(@RequestBody ToolCallRequest request) {
        return super.callTool(request, new MCPActionCallback());

    }


}
