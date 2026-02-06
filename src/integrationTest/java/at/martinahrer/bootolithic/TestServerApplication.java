package at.martinahrer.bootolithic;

import org.springframework.boot.SpringApplication;

public class TestServerApplication {

    static void main(String[] args) {
        SpringApplication.from(ServerApplication::main)
                .with(TestcontainersConfiguration.class).run(args);
    }
}
