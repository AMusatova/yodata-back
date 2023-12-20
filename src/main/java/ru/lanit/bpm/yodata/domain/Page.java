package ru.lanit.bpm.yodata.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pages_id")
    @SequenceGenerator(name = "sq_pages_id", allocationSize = 1)
    private Long id;
    private String name;
    private String url;
    @Column(name = "parsing_x_path")
    private String parsingXPath;

    public Page(String name, String url, String parsingXPath) {
        this.name = name;
        this.url = url;
        this.parsingXPath = parsingXPath;
    }
}
