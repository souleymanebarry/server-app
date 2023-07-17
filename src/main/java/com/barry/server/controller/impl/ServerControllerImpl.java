package com.barry.server.controller.impl;

import com.barry.server.controller.ServerController;
import com.barry.server.model.Server;
import com.barry.server.service.ServerService;
import com.barry.server.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static com.barry.server.utils.Status.SERVER_UP;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
public class ServerControllerImpl implements ServerController {

    private final ServerService serverService;

    public ServerControllerImpl(ServerService serverService) {
        this.serverService = serverService;
    }

    @Override
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers: ", serverService.getServers(3)))
                        .message("servers retrieved")
                        .status(OK)
                        .code(OK.value())
                        .build());
    }


    @Override
    public ResponseEntity<Response> pingServer(String ipAddress) throws IOException {
        Server server = serverService.pingServer(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers: ", server))
                        .message(SERVER_UP.equals(server.getStatus())? "Ping success": "Ping failed")
                        .status(OK)
                        .code(OK.value())
                        .build());
    }

    @Override
    public ResponseEntity<Response> saveServer(Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.createServer(server)))
                        .message("Server created")
                        .status(CREATED)
                        .code(CREATED.value())
                        .build());
    }


    @Override
    public ResponseEntity<Response> getServer(Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.getServerByID(id)))
                        .message("Server retrieved")
                        .status(CREATED)
                        .code(CREATED.value())
                        .build());
    }

    @Override
    public ResponseEntity<Response> deleteServer(Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted servers", serverService.deleteServer(id)))
                        .message("Server retrieved")
                        .status(CREATED)
                        .code(CREATED.value())
                        .build());
    }


    @Override
    public byte[] getServerImage(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Downloads/images/"+fileName));
    }


}
