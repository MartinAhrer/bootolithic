package at.martinahrer.bootolithic.catalog;

import at.martinahrer.bootolithic.catalog.internal.SizeMapper;
import at.martinahrer.bootolithic.catalog.internal.SizeRepository;
import at.martinahrer.bootolithic.service.AbstractCrudService;
import org.jmolecules.ddd.annotation.Service;

@Service
public class SizeService extends AbstractCrudService<Size, SizeIdentifier> {
    public SizeService(SizeRepository repository, SizeMapper mapper) {
        super(repository,mapper);
    }
}
