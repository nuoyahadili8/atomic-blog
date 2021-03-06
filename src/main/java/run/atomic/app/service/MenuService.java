package run.atomic.app.service;

import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import run.atomic.app.model.dto.MenuDTO;
import run.atomic.app.model.entity.Menu;
import run.atomic.app.model.params.MenuParam;
import run.atomic.app.model.vo.MenuTeamVO;
import run.atomic.app.model.vo.MenuVO;
import run.atomic.app.service.base.CrudService;

import java.util.List;

/**
 * Menu service interface.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-03-14
 */
public interface MenuService extends CrudService<Menu, Integer> {

    /**
     * Lists all menu dtos.
     *
     * @param sort must not be null
     * @return a list of menu output dto
     */
    @NonNull
    List<MenuDTO> listDtos(@NonNull Sort sort);

    /**
     * Lists menu team vos.
     *
     * @param sort must not be null
     * @return a list of menu team vo
     */
    @NonNull
    List<MenuTeamVO> listTeamVos(@NonNull Sort sort);

    /**
     * List menus by team.
     *
     * @param team team
     * @param sort sort
     * @return list of menus
     */
    List<MenuDTO> listByTeam(@NonNull String team, Sort sort);

    /**
     * Creates a menu.
     *
     * @param menuParam must not be null
     * @return created menu
     */
    @NonNull
    Menu createBy(@NonNull MenuParam menuParam);

    /**
     * Lists as menu tree.
     *
     * @param sort sort info must not be null
     * @return a menu tree
     */
    List<MenuVO> listAsTree(Sort sort);

    /**
     * Lists menu by parent id.
     *
     * @param id id
     * @return a list of menu
     */
    List<Menu> listByParentId(@NonNull Integer id);
}
