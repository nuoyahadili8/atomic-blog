package run.atomic.app.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import run.atomic.app.model.dto.CategoryDTO;
import run.atomic.app.model.dto.TagDTO;
import run.atomic.app.model.dto.post.BasePostSimpleDTO;

import java.util.List;

/**
 * Post list vo.
 *
 * @author johnniang
 * @date 3/19/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostListVO extends BasePostSimpleDTO {

    private Long commentCount;

    private List<TagDTO> tags;

    private List<CategoryDTO> categories;

}
