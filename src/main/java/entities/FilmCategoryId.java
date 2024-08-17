package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
@Getter
@Setter
@Embeddable
public class FilmCategoryId implements Serializable {
    @Serial
    private static final long serialVersionUID = -1577040463817298972L;
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer filmId;

    @Column(name = "category_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short categoryId;
}