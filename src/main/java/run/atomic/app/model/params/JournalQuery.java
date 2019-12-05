package run.atomic.app.model.params;

import lombok.Data;
import run.atomic.app.model.enums.JournalType;

/**
 * Journal query params.
 *
 * @author ryanwang
 * @date : 2019/04/26
 */
@Data
public class JournalQuery {

    /**
     * Keyword.
     */
    private String keyword;

    private JournalType type;
}
