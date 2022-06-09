package team.f10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.f10.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
