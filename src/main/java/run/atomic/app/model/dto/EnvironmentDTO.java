package run.atomic.app.model.dto;

import lombok.Data;
import run.atomic.app.model.enums.Mode;

/**
 * Theme controller.
 *
 * @author ryanwang
 * @date : 2019/5/4
 */
@Data
public class EnvironmentDTO {

    private String database;

    private long startTime;

    private String version;

    private Mode mode;
}
