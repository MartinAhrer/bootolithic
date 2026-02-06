package at.martinahrer.bootolithic.web;

import org.jilt.Builder;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;

abstract class PageMetadataFactory {
    @Builder(packageName = "at.martinahrer.bootolithic.web")
    public static PageMetadata create(Page<?> page) {
        return new PagedModel.PageMetadata(
            page.getSize(),
            page.getNumber(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }
}
