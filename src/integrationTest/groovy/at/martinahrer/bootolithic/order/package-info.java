@ApplicationModule(allowedDependencies = {"catalog", "web", "service", "persistence", "test", "integrationtest", "integrationtest::persistence"})
package at.martinahrer.bootolithic.order;

import org.springframework.modulith.ApplicationModule;