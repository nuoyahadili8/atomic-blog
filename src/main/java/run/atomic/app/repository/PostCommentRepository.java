package run.atomic.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.atomic.app.model.entity.PostComment;
import run.atomic.app.model.projection.CommentChildrenCountProjection;
import run.atomic.app.model.projection.CommentCountProjection;
import run.atomic.app.repository.base.BaseCommentRepository;

import java.util.List;

/**
 * PostComment repository.
 *
 * @author johnniang
 * @date 3/21/19
 */
public interface PostCommentRepository extends BaseCommentRepository<PostComment> {

    @Query("select new run.atomic.app.model.projection.CommentCountProjection(count(comment.id), comment.postId) " +
            "from PostComment comment " +
            "where comment.postId in ?1 group by comment.postId")
    @NonNull
    @Override
    List<CommentCountProjection> countByPostIds(@NonNull Iterable<Integer> postIds);

    @Query("select new run.atomic.app.model.projection.CommentChildrenCountProjection(count(comment.id), comment.parentId) " +
            "from PostComment comment " +
            "where comment.parentId in ?1 " +
            "group by comment.parentId")
    @NonNull
    @Override
    List<CommentChildrenCountProjection> findDirectChildrenCount(@NonNull Iterable<Long> commentIds);
}
