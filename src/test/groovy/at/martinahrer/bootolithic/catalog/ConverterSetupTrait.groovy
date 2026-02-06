package at.martinahrer.bootolithic.catalog

import at.martinahrer.bootolithic.catalog.internal.StringToArticleIdentifierConverter
import at.martinahrer.bootolithic.catalog.internal.StringToRebateIdentifierConverter
import at.martinahrer.bootolithic.catalog.internal.StringToSizeIdentifierConverter
import org.springframework.format.support.FormattingConversionService

trait ConverterSetupTrait {
    def setupConversionService(FormattingConversionService formattingConversionService) {
        formattingConversionService.addConverter(new StringToSizeIdentifierConverter())
        formattingConversionService.addConverter(new StringToRebateIdentifierConverter())
        formattingConversionService.addConverter(new StringToArticleIdentifierConverter())
    }
}
