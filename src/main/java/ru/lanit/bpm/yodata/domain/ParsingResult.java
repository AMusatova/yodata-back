package ru.lanit.bpm.yodata.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "parsing_results")
public class ParsingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_parsing_results_id")
    @SequenceGenerator(name = "sq_parsing_results_id", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;
    @Column (name = "parsing_date_time")
    private Instant parsingDateTime;
    private String result;
    private Boolean sent;

    public ParsingResult(Page page, String result) {
        this.page = page;
        this.result = result;
        this.sent = false;
        this.parsingDateTime = Instant.now();
    }
}
