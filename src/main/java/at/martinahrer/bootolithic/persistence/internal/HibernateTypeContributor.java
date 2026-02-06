package at.martinahrer.bootolithic.persistence.internal;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

public final class HibernateTypeContributor implements TypeContributor {
    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeType(new MonetaryAmountType());
    }
}