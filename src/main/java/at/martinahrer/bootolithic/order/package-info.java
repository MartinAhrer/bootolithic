@NullMarked
@BoundedContext("Order")
@ApplicationModule(allowedDependencies = {"catalog", "web", "service", "persistence"}, displayName = "Order bounded context")
package at.martinahrer.bootolithic.order;

import org.jmolecules.ddd.annotation.BoundedContext;
import org.jspecify.annotations.NullMarked;
import org.springframework.modulith.ApplicationModule;