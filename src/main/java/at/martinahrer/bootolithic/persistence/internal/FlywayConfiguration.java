package at.martinahrer.bootolithic.persistence.internal;

import org.springframework.boot.flyway.autoconfigure.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

//@Configuration
//@Profile("dev")
// TODO allow modulith to customize the migration strategy
class FlywayConfiguration {
    @Bean
    public FlywayMigrationStrategy cleanAndMigrateStrategy() {
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}

