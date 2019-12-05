package run.atomic.app.model.params;

import lombok.Data;
import run.atomic.app.model.enums.AttachmentType;

/**
 * Attachment query params.
 *
 * @author ryanwang
 * @date : 2019/04/18
 */
@Data
public class AttachmentQuery {

    /**
     * Keyword.
     */
    private String keyword;

    private String mediaType;

    private AttachmentType attachmentType;
}
