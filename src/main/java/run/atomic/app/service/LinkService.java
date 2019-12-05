package run.atomic.app.service;

import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import run.atomic.app.model.dto.LinkDTO;
import run.atomic.app.model.entity.Link;
import run.atomic.app.model.params.LinkParam;
import run.atomic.app.model.vo.LinkTeamVO;
import run.atomic.app.service.base.CrudService;

import java.util.List;

/**
 * Link service interface.
 *
 * @author johnniang
 * @date 2019-03-14
 */
public interface LinkService extends CrudService<Link, Integer> {

    /**
     * List link dtos.
     *
     * @param sort sort
     * @return all links
     */
    @NonNull
    List<LinkDTO> listDtos(@NonNull Sort sort);

    /**
     * Lists link team vos.
     *
     * @param sort must not be null
     * @return a list of link team vo
     */
    @NonNull
    List<LinkTeamVO> listTeamVos(@NonNull Sort sort);

    /**
     * Creates link by link param.
     *
     * @param linkParam must not be null
     * @return create link
     */
    @NonNull
    Link createBy(@NonNull LinkParam linkParam);

    /**
     * Exists by link name.
     *
     * @param name must not be blank
     * @return true if exists; false otherwise
     */
    boolean existByName(String name);
}
