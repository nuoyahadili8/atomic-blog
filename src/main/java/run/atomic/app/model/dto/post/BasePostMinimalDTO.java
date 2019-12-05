package run.atomic.app.model.dto.post;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.atomic.app.model.dto.base.OutputConverter;
import run.atomic.app.model.entity.BasePost;
import run.atomic.app.model.enums.PostStatus;

import java.util.Date;

/**
 * Base post minimal output dto.
 *
 * @author johnniang
 */
@Data
@ToString
@EqualsAndHashCode
public class BasePostMinimalDTO implements OutputConverter<BasePostMinimalDTO, BasePost> {

    private Integer id;

    private String title;

    private PostStatus status;

    private String url;

    private Date updateTime;

    private Date createTime;

    private Date editTime;
}
