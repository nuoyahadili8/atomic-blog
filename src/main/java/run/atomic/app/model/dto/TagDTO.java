package run.atomic.app.model.dto;

import lombok.Data;
import run.atomic.app.model.dto.base.OutputConverter;
import run.atomic.app.model.entity.Tag;

import java.util.Date;

/**
 * Tag output dto.
 *
 * @author johnniang
 * @date 3/19/19
 */
@Data
public class TagDTO implements OutputConverter<TagDTO, Tag> {

    private Integer id;

    private String name;

    private String slugName;

    private Date createTime;
}
