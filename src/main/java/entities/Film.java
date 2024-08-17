package entities;

import dao.FilmDAO;
import dao.GenericDAO;
import dao.RatingConverter;
import dao.YearAttributeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Setter
@Getter
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Short id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", columnDefinition = "tinyint UNSIGNED not null")
    private Byte rentalDuration;

    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length", columnDefinition = "smallint UNSIGNED")
    private Short length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;


    @Column(columnDefinition = "enum('G', 'PG', PG-13, 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    private Rating rating;


    @Column(name = "special_features", columnDefinition = "SET('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actors;
    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;
    @Column(name = "release_year", columnDefinition = "year")
    @Convert(converter = YearAttributeConverter.class)
    private Year year;

    public Set<SpecialFeature> getSpecialFeatures() {
        // Возвращаем пустой набор вместо null
        if (isNull(specialFeatures) || specialFeatures.isEmpty()) {
            return Collections.emptySet();
        }
        // Используем HashSet для хранения уникальных значений
        Set<SpecialFeature> result = new HashSet<>();
        // Разделяем строку и обрабатываем специальные функции
        String[] features = specialFeatures.split(",");
        for (String feature : features) {
            SpecialFeature specialFeature = SpecialFeature.getFeatureByValue(feature.trim()); // удаление лишних пробелов
            if (specialFeature != null) {
                result.add(specialFeature); // Добавляем только непустые значения
            }
        }
        return result;
    }

    public void setSpecialFeatures(Set<SpecialFeature> features) {
        if (features == null || features.isEmpty()) {
            specialFeatures = null;
        } else {
            specialFeatures = features.stream()
                    .map(SpecialFeature::getValue)
                    .collect(Collectors.joining(","));
        }
    }






}