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
public class FilmActorId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4544912032532624134L;
    @Column(name = "actor_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer actorId;

    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer filmId;


}