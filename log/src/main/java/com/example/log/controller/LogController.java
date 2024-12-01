package com.example.log.controller;

import com.example.log.dto.LogRequest;
import com.example.log.dto.LogResponse;
import com.example.log.service.AuthService;
import com.example.log.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/log")
@CrossOrigin(
        methods = {
                RequestMethod.GET, RequestMethod.POST,
                RequestMethod.DELETE,RequestMethod.OPTIONS,
                RequestMethod.PUT},
        origins = "http://localhost:3000"
)

public class LogController {
    private final LogService logService;
    private final AuthService authService;

    @PostMapping
    public void saveClickLogs(@RequestBody LogRequest request, @RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);
        String userId = authService.validateUser(token);
        logService.saveLogs(request, UUID.fromString(userId));
    }

    @GetMapping
    public List<LogResponse> getLogs(@RequestHeader("Authorization") String authorization) {
//        String token = authorization.substring(7);
        String userId = authService.validateUser(authorization);
        return logService.getLogs(UUID.fromString(userId));
    }
}
