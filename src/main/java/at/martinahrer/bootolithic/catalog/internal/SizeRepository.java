package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.Size;
import at.martinahrer.bootolithic.catalog.SizeIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, SizeIdentifier> {
}
