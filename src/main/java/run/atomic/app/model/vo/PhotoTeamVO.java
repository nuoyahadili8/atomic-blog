package run.atomic.app.model.vo;

import lombok.Data;
import lombok.ToString;
import run.atomic.app.model.dto.PhotoDTO;

import java.util.List;

/**
 * Link team vo.
 *
 * @author ryanwang
 * @date : 2019/3/22
 */
@Data
@ToString
public class PhotoTeamVO {

    private String team;

    private List<PhotoDTO> photos;
}
