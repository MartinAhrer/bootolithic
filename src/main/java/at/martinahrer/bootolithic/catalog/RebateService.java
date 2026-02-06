package at.martinahrer.bootolithic.catalog;

import at.martinahrer.bootolithic.catalog.internal.RebateMapper;
import at.martinahrer.bootolithic.catalog.internal.RebateRepository;
import at.martinahrer.bootolithic.service.AbstractCrudService;
import org.jmolecules.ddd.annotation.Service;

@Service

public class RebateService extends AbstractCrudService<Rebate, RebateIdentifier> {
    public RebateService(RebateRepository repository, RebateMapper mapper) {
        super(repository, mapper);
    }

}
