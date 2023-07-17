package com.barry.server.service;

import com.barry.server.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public interface ServerService {

    Server createServer(Server server);

    Server pingServer(String ipAddress) throws IOException;
    List<Server> getServers(int limit);
    Server getServerByID(Long id);
    Server updateServer(Server server);
    Boolean deleteServer(Long id);

}
