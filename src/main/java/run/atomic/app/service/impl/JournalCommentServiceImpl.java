package run.atomic.app.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import run.atomic.app.model.dto.JournalDTO;
import run.atomic.app.model.entity.Journal;
import run.atomic.app.model.entity.JournalComment;
import run.atomic.app.model.vo.JournalCommentWithJournalVO;
import run.atomic.app.service.JournalCommentService;
import run.atomic.app.service.OptionService;
import run.atomic.app.service.UserService;
import run.atomic.app.utils.ServiceUtils;
import run.atomic.app.exception.NotFoundException;
import run.atomic.app.repository.JournalCommentRepository;
import run.atomic.app.repository.JournalRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Journal comment service implementation.
 *
 * @author johnniang
 * @date 2019-04-25
 */
@Service
public class JournalCommentServiceImpl extends BaseCommentServiceImpl<JournalComment> implements JournalCommentService {

    private final JournalCommentRepository journalCommentRepository;

    private final JournalRepository journalRepository;

    public JournalCommentServiceImpl(JournalCommentRepository journalCommentRepository,
                                     OptionService optionService,
                                     UserService userService,
                                     ApplicationEventPublisher eventPublisher, JournalRepository journalRepository) {
        super(journalCommentRepository, optionService, userService, eventPublisher);
        this.journalCommentRepository = journalCommentRepository;
        this.journalRepository = journalRepository;
    }

    @Override
    public void validateTarget(Integer journalId) {
        if (!journalRepository.existsById(journalId)) {
            throw new NotFoundException("查询不到该日志信息").setErrorData(journalId);
        }
    }

    @Override
    public List<JournalCommentWithJournalVO> convertToWithJournalVo(List<JournalComment> journalComments) {

        if (CollectionUtil.isEmpty(journalComments)) {
            return Collections.emptyList();
        }

        Set<Integer> journalIds = ServiceUtils.fetchProperty(journalComments, JournalComment::getPostId);

        // Get all journals
        List<Journal> journals = journalRepository.findAllById(journalIds);

        Map<Integer, Journal> journalMap = ServiceUtils.convertToMap(journals, Journal::getId);

        return journalComments.stream()
                .filter(journalComment -> journalMap.containsKey(journalComment.getPostId()))
                .map(journalComment -> {
                    JournalCommentWithJournalVO journalCmtWithJournalVo = new JournalCommentWithJournalVO().convertFrom(journalComment);
                    journalCmtWithJournalVo.setJournal(new JournalDTO().convertFrom(journalMap.get(journalComment.getPostId())));
                    return journalCmtWithJournalVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<JournalCommentWithJournalVO> convertToWithJournalVo(Page<JournalComment> journalCommentPage) {
        Assert.notNull(journalCommentPage, "Journal comment page must not be null");

        // Convert the list
        List<JournalCommentWithJournalVO> journalCmtWithJournalVOS = convertToWithJournalVo(journalCommentPage.getContent());

        // Build and return
        return new PageImpl<>(journalCmtWithJournalVOS, journalCommentPage.getPageable(), journalCommentPage.getTotalElements());
    }
}
