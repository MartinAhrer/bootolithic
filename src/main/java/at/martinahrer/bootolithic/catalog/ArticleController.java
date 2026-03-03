package at.martinahrer.bootolithic.catalog;

import at.martinahrer.bootolithic.web.AbstractCrudController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The naming of this class is intentional. MarketData is a quite meaning less name.
 * Further, it's not well-fitted to the common REST API naming conventions in terms if pluralization.
 *
 */
@RestController()
@RequestMapping(ArticleController.REQUEST_MAPPING_URI_PREFIX)
@Validated
public class ArticleController extends AbstractCrudController<Article, ArticleIdentifier> {
    public static final String REQUEST_MAPPING_URI_PREFIX = "/public/catalog/articles";

    public ArticleController(ArticleService articleService) {
        super(articleService);
    }
}
