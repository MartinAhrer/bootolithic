package at.martinahrer.bootolithic.catalog.internal;

import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;

@ValueObject
public record ShiftedMiddle(BigDecimal value) {
}