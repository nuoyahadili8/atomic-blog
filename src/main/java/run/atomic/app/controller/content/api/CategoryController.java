package run.atomic.app.controller.content.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import run.atomic.app.model.dto.CategoryDTO;
import run.atomic.app.model.dto.post.BasePostSimpleDTO;
import run.atomic.app.model.entity.Category;
import run.atomic.app.model.entity.Post;
import run.atomic.app.model.enums.PostStatus;
import run.atomic.app.service.CategoryService;
import run.atomic.app.service.PostCategoryService;
import run.atomic.app.service.PostService;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Category portal controller.
 *
 * @author ryanwang
 * @date 6/9/19
 */
@RestController("ApiContentCategoryController")
@RequestMapping("/api/content/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final PostCategoryService postCategoryService;

    private final PostService postService;

    public CategoryController(CategoryService categoryService,
                              PostCategoryService postCategoryService,
                              PostService postService) {
        this.categoryService = categoryService;
        this.postCategoryService = postCategoryService;
        this.postService = postService;
    }

    @GetMapping
    @ApiOperation("Lists categories")
    public List<? extends CategoryDTO> listCategories(@SortDefault(sort = "updateTime", direction = DESC) Sort sort,
                                                      @RequestParam(name = "more", required = false, defaultValue = "false") Boolean more) {
        if (more) {
            return postCategoryService.listCategoryWithPostCountDto(sort);
        }
        return categoryService.convertTo(categoryService.listAll(sort));
    }

    @GetMapping("{slugName}/posts")
    @ApiOperation("Lists posts by category slug name")
    public Page<BasePostSimpleDTO> listPostsBy(@PathVariable("slugName") String slugName,
                                               @PageableDefault(sort = "updateTime", direction = DESC) Pageable pageable) {
        // Get category by slug name
        Category category = categoryService.getBySlugNameOfNonNull(slugName);

        Page<Post> postPage = postCategoryService.pagePostBy(category.getId(), PostStatus.PUBLISHED, pageable);
        return postService.convertToSimple(postPage);
    }
}
