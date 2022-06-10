package team.f10.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String content;

    private Timestamp creationDate;

    @Setter
    private Timestamp lastEditedDate;

    @ManyToOne
    private User author;

    @Setter
    @ManyToOne
    private User lastEditor;

    @Setter
    @OneToOne
    private Photo mainImage;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "article_tags"
    )
    private Set<Tag> tags = new HashSet<>();

}
