package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.Rebate;
import at.martinahrer.bootolithic.catalog.RebateIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RebateRepository extends JpaRepository<Rebate, RebateIdentifier> {
}