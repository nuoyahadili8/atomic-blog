package run.atomic.app.repository;

import org.springframework.lang.NonNull;
import run.atomic.app.model.entity.ThemeSetting;
import run.atomic.app.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Theme setting repository interface.
 *
 * @author johnniang
 * @date 4/8/19
 */
public interface ThemeSettingRepository extends BaseRepository<ThemeSetting, Integer> {

    /**
     * Finds all theme settings by theme id.
     *
     * @param themeId theme id must not be blank
     * @return a list of theme setting
     */
    @NonNull
    List<ThemeSetting> findAllByThemeId(@NonNull String themeId);

    /**
     * Deletes theme setting by theme id and setting key.
     *
     * @param themeId theme id must not be blank
     * @param key     setting key must not be blank
     * @return affected row(s)
     */
    long deleteByThemeIdAndKey(@NonNull String themeId, @NonNull String key);

    /**
     * Finds theme settings by theme id and setting key.
     *
     * @param themeId theme id must not be blank
     * @param key     setting key must not be blank
     * @return an optional theme setting
     */
    @NonNull
    Optional<ThemeSetting> findByThemeIdAndKey(@NonNull String themeId, @NonNull String key);
}
