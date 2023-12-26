package ru.lanit.bpm.yodata.app.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lanit.bpm.yodata.domain.Page;
import ru.lanit.bpm.yodata.domain.ParsingResult;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParsingResultRepository extends JpaRepository<ParsingResult, Long>, ParsingResultRepositoryCustom {

    List<ParsingResult> findAllBySent(boolean sent);

    @Query("select pr from ParsingResult pr where pr.sent = true and pr.page.id=:pageId order by pr.parsingDateTime desc")
    List<ParsingResult> findAllSentByPageOrderByParsingDateTimeDesc(Long pageId);
}
