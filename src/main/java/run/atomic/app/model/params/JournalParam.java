package run.atomic.app.model.params;

import lombok.Data;
import run.atomic.app.model.dto.base.InputConverter;
import run.atomic.app.model.entity.Journal;
import run.atomic.app.model.enums.JournalType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Journal param.
 *
 * @author johnniang
 * @author ryanwang
 * @date 19-4-25
 */
@Data
public class JournalParam implements InputConverter<Journal> {

    @NotBlank(message = "内容不能为空")
    @Size(max = 511, message = "内容的字符长度不能超过 {max}")
    private String content;

    private JournalType type = JournalType.PUBLIC;
}
