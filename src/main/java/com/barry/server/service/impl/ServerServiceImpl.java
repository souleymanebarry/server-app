package com.barry.server.service.impl;

import com.barry.server.model.Server;
import com.barry.server.repositories.ServerRepository;
import com.barry.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Random;

import static com.barry.server.utils.Status.SERVER_DOWN;
import static com.barry.server.utils.Status.SERVER_UP;


@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private static final Logger LOGGER = LogManager.getLogger(ServerServiceImpl.class);

    @Override
    public Server createServer(Server server) {
        LOGGER.info("saving new server :{}", server.getName());
        server.setImageUrl(setServerImageUlr());
        return serverRepository.save(server);
    }


    @Override
    public Server pingServer(String ipAddress) throws IOException {
        LOGGER.info("Pinging server IP:{}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? SERVER_UP: SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public List<Server> getServers(int limit) {
        LOGGER.info("Fetching all server");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server getServerByID(Long id) {
        LOGGER.info("Fetching server by id: {}", id);
        return serverRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found"));
    }

    @Override
    public Server updateServer(Server server) {
        LOGGER.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean deleteServer(Long id) {
        serverRepository.deleteById(id);
        LOGGER.info("Deleting server by  ID: {}", id);
        return Boolean.TRUE;
    }

    private String setServerImageUlr() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/"+imageNames[new Random().nextInt(4)]).toUriString();
    }
}
