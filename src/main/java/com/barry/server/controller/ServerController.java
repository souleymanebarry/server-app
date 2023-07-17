package com.barry.server.controller;


import com.barry.server.model.Server;
import com.barry.server.utils.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Tag(name = "Server endpoint")
@RequestMapping("/api/v1")
public interface ServerController {

    @GetMapping("/servers")
    ResponseEntity<Response> getServers();


    @GetMapping("/servers/ping/{ipAddress}")
    ResponseEntity<Response> pingServer(@PathVariable(name = "ipAddress") String ipAddress) throws IOException;


    @PostMapping("/servers")
    ResponseEntity<Response> saveServer(@RequestBody @Valid Server server);


    @GetMapping("/servers/{id}")
    ResponseEntity<Response> getServer(@PathVariable(name = "id") Long id);


    @DeleteMapping("/servers/{id}")
    ResponseEntity<Response> deleteServer(@PathVariable(name = "id") Long id);


    @GetMapping(value = "/servers/images/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE})
    byte[] getServerImage(@PathVariable(name = "fileName") String fileName) throws IOException;

}
