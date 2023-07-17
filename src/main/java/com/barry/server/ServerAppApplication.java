package com.barry.server;

import com.barry.server.model.Server;
import com.barry.server.repositories.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.barry.server.utils.Status.SERVER_DOWN;
import static com.barry.server.utils.Status.SERVER_UP;

@SpringBootApplication
public class ServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAppApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ServerRepository serverRepository) {
		return args->{
			serverRepository.save(new Server(
					null,
					"44.87.30.203",
					"Ubuntu Linux",
					"16 GB",
					"Personal PC",
					"http://localhost:8080/server/image/server1.png",
					SERVER_UP));

			serverRepository.save(new Server(
					null,
					"154.72.26.61",
					"Fedora Linux",
					"16 GB",
					"Dell Tower",
					"http://localhost:8080/server/image/server2.png",
					SERVER_DOWN));

			serverRepository.save(new Server(
					null,
					"55.105.152.196",
					"MS 2008",
					"32 GB",
					"Web Server",
					"http://localhost:8080/server/image/server3.png",
					SERVER_UP));

			serverRepository.save(new Server(
					null,
					"71.78.210.153",
					"RED Hat Enterprise Linux",
					"64 GB",
					"Mail Server",
					"http://localhost:8080/server/image/server4.png",
					SERVER_DOWN));
		};
	}

}
