package run.atomic.app.model.freemarker.tag;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import run.atomic.app.model.support.HaloConst;
import run.atomic.app.service.CategoryService;
import run.atomic.app.service.PostCategoryService;

import java.io.IOException;
import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Freemarker custom tag of category.
 *
 * @author ryanwang
 * @date : 2019/3/22
 */
@Component
public class CategoryTagDirective implements TemplateDirectiveModel {

    private final CategoryService categoryService;

    private final PostCategoryService postCategoryService;

    public CategoryTagDirective(Configuration configuration, CategoryService categoryService, PostCategoryService postCategoryService) {
        this.categoryService = categoryService;
        this.postCategoryService = postCategoryService;
        configuration.setSharedVariable("categoryTag", this);
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        final DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);

        if (params.containsKey(HaloConst.METHOD_KEY)) {
            String method = params.get(HaloConst.METHOD_KEY).toString();
            switch (method) {
                case "list":
                    env.setVariable("categories", builder.build().wrap(postCategoryService.listCategoryWithPostCountDto(Sort.by(DESC, "createTime"))));
                    break;
                case "listByPostId":
                    Integer postId = Integer.parseInt(params.get("postId").toString());
                    env.setVariable("categories", builder.build().wrap(postCategoryService.listCategoriesBy(postId)));
                    break;
                case "count":
                    env.setVariable("count", builder.build().wrap(categoryService.count()));
                    break;
                default:
                    break;
            }
        }
        body.render(env.getOut());
    }
}
