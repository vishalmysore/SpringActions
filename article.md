# Integrating AI Capabilities with Spring Boot: A2A and MCP Protocols

## Introduction to A2A Protocol
The Agent-to-Agent (A2A) protocol, developed by Google, enables seamless communication between AI agents and services. It provides a standardized way for AI agents to discover, understand, and interact with various service capabilities. By implementing A2A, your Spring Boot applications become AI-ready, allowing AI agents to naturally interact with your services.

## Understanding MCP (Model Context Protocol)
MCP (Model Context Protocol) is Anthropic's specification for enabling structured interactions between AI models and external tools/services. It provides a standardized way to define tool interfaces that can be called by AI models, making services discoverable and executable in a controlled manner. This protocol is particularly useful for creating safe and reliable integrations between AI models and external capabilities.

### Key Features of MCP:
- Structured tool definitions with clear input/output schemas
- Runtime type validation
- Standardized error handling
- Support for streaming responses
- Built-in security considerations

## Creating AI-Enabled Spring Boot Services

### Service Implementation Examples
The framework supports several types of services that can be exposed to AI agents:

1. **CompareCarService**: Compares features between two cars
```java
@Service
@Agent(groupName ="compareCar", groupDescription = "Provide 2 cars and compare them")
public class CompareCarService {
    @Action(description = "compare 2 cars")
    public String compareCar(String car1, String car2) {
        return car2 + " is better than " + car1;
    }
}
```

2. **WeatherService**: Provides weather information for cities
```java
@Service
@Agent(groupName = "getTemperature", groupDescription = "get weather for city")
public class WeatherService {
    @Action(description = "get temperature for city")
    public String getTemperature(String cityName) {
        // Weather API integration
        return "Temperature for " + cityName;
    }
}
```

3. **Customer Service Integration**: Handle customer support tickets
```java
@Service
@Agent(groupName ="raiseTicket", groupDescription = "Create a ticket for customer")
public class RaiseCustomerTicket {
    @Action(description = "Raise a ticket for customer")
    public String raiseTicket(String customerName) {
        return "ticket raised for " + customerName;
    }
}
```

## Protocol Integration

### A2A Agent Card
The A2A agent card describes your service capabilities to AI agents. It includes:
- Service metadata (name, description, version)
- Available skills
- Input/output modes
- Authentication requirements

### A2A Agent Card Example
When an AI agent discovers your service, it receives a detailed agent card in JSON format that describes all available capabilities. Here's what your service exposes:

```json
{
  "name": "Helpful Agent",
  "description": "This agent provides capabilities to raise tickets, compare cars, find out a person's favorite food, and get weather information.",
  "url": "http://localhost:7860",
  "provider": {
    "organization": "Example Corp",
    "url": "https://example.com"
  },
  "version": "1.0",
  "documentationUrl": "https://example.com/helpfulagent/docs",
  "capabilities": {
    "streaming": false,
    "pushNotifications": false,
    "stateTransitionHistory": false
  },
  "authentication": {
    "schemes": ["API Key"],
    "credentials": "API Key"
  },
  "defaultInputModes": ["Text", "Voice"],
  "defaultOutputModes": ["Text", "Voice"],
  "skills": [
    {
      "id": "raiseTicket",
      "name": "Raise Ticket",
      "description": "Create a ticket for a customer.",
      "tags": ["Ticket", "Support", "Customer Service"],
      "examples": [
        "Raise a ticket for John Doe",
        "Create a support ticket"
      ],
      "inputModes": ["Text", "Voice"],
      "outputModes": ["Text"]
    },
    {
      "id": "compareCar",
      "name": "Compare Cars",
      "description": "Compare two cars based on their features.",
      "tags": ["Car", "Comparison", "Automobile"],
      "examples": [
        "Compare Toyota Camry and Honda Accord",
        "Compare two cars"
      ],
      "inputModes": ["Text"],
      "outputModes": ["Text"]
    },
    {
      "id": "whatThisPersonFavFood",
      "name": "Find Favorite Food",
      "description": "Find out what a person likes to eat.",
      "tags": ["Food", "Preferences", "Person"],
      "examples": [
        "What does John like to eat?",
        "Find out Mary's favorite food"
      ],
      "inputModes": ["Text", "Voice"],
      "outputModes": ["Text"]
    },
    {
      "id": "getTemperature",
      "name": "Get Temperature",
      "description": "Get the current weather temperature for a city.",
      "tags": ["Weather", "Temperature", "City"],
      "examples": [
        "What is the weather in London?",
        "Get the temperature for New York"
      ],
      "inputModes": ["Text", "Voice"],
      "outputModes": ["Text"]
    }
  ]
}
```

This agent card provides a complete description of your service's capabilities:

1. **Basic Information**: Name, description, version, and provider details
2. **Capabilities**: Supported features like streaming and notifications
3. **Authentication**: Required security mechanisms
4. **Input/Output Modes**: Supported interaction methods
5. **Skills**: Detailed list of available actions with:
   - Unique IDs
   - Human-readable names
   - Descriptions
   - Example usage
   - Supported input/output modes
   - Relevant tags

### MCP Tools Interface
MCP tools are exposed through a dedicated endpoint that provides:
- Tool definitions
- Parameter schemas
- Input validation rules
- Response formats

Example MCP tools endpoint: `http://localhost:7860/mcp/list-tools`

### MCP Tools Response Example
Similarly, when accessed through the MCP endpoint, your tools are described in a structured format:

```json
{
  "tools": [
    {
      "parameters": {
        "type": "object",
        "properties": {
          "provideAllValuesInPlainEnglish": {
            "type": "string",
            "description": "{\n    \"customerName\": \"String\"\n}"
          }
        },
        "required": ["provideAllValuesInPlainEnglish"],
        "additionalProperties": false
      },
      "inputSchema": {
        "type": "object",
        "properties": {
          "provideAllValuesInPlainEnglish": {
            "type": "string",
            "description": "{\n    \"customerName\": \"String\"\n}"
          }
        },
        "required": ["provideAllValuesInPlainEnglish"]
      },
      "description": "Raise a ticket for customer",
      "name": "raiseTicket"
    }
    // ... other tools definitions ...
  ]
}
```

## Controller Implementation

### A2A Controllers
1. **MyA2ACardController**: Manages the agent card endpoint
2. **MyA2ATaskController**: Handles task execution
3. **MyRpcController**: Processes JSON-RPC requests

### MCP Controller
The MCPController handles tool discovery and execution:
```java
@RestController
@RequestMapping("/mcp")
public class MCPController extends MCPToolsController {
    @GetMapping("/list-tools")
    public ResponseEntity<Map<String, List<Tool>>> listTools() {
        // Returns available tools
    }
    
    @PostMapping("/call-tool")
    public ResponseEntity<JSONRPCResponse> callTool(@RequestBody ToolCallRequest request) {
        // Executes tool calls
    }
}
```

## Making Services AI-Accessible
To expose your Spring services to AI agents:

1. Add the required dependencies:
```xml
<dependency>
    <groupId>io.github.vishalmysore</groupId>
    <artifactId>a2ajava</artifactId>
    <version>0.0.7.2</version>
</dependency>
<dependency>
    <groupId>io.github.vishalmysore</groupId>
    <artifactId>tools4ai</artifactId>
    <version>1.0.7.5</version>
</dependency>
```

2. Annotate your services with `@Agent` and `@Action`
3. Configure the application properties:
```properties
spring.application.name=spring-boot
server.port=7860
a2a.persistence=cache
```

## Testing AI Integration
You can test your AI-enabled services through:
1. Direct HTTP calls to service endpoints
2. A2A agent card verification
3. MCP tool discovery and execution
4. Natural language prompts through the action endpoint

Example test prompt:
```
GET http://localhost:7860/action?prompt="Compare Honda Civic and Toyota Corolla"
```

## Conclusion
By implementing both A2A and MCP protocols, your Spring Boot applications become fully AI-capable, allowing seamless integration with various AI agents and systems. This dual-protocol support ensures maximum compatibility and flexibility in the AI ecosystem.
