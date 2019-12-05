package run.atomic.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import run.atomic.app.model.entity.PostMeta;
import run.atomic.app.service.PostMetaService;
import run.atomic.app.exception.NotFoundException;
import run.atomic.app.repository.PostMetaRepository;
import run.atomic.app.repository.PostRepository;

/**
 * Post meta service implementation class.
 *
 * @author ryanwang
 * @author ikaisec
 * @date 2019-08-04
 */
@Slf4j
@Service
public class PostMetaServiceImpl extends BaseMetaServiceImpl<PostMeta> implements PostMetaService {

    private final PostMetaRepository postMetaRepository;

    private final PostRepository postRepository;

    public PostMetaServiceImpl(PostMetaRepository postMetaRepository,
                               PostRepository postRepository) {
        super(postMetaRepository);
        this.postMetaRepository = postMetaRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void validateTarget(Integer postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("查询不到该文章的信息").setErrorData(postId));
    }
}
