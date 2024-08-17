package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
@AllArgsConstructor
@Getter
public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;


    public static SpecialFeature getFeatureByValue(String featureValue) {
        if (featureValue == null || featureValue.isEmpty()) {
            return null;
        }

        return Arrays.stream(SpecialFeature.values())
                .filter(specialFeature -> Objects.equals(specialFeature.value, featureValue))
                .findFirst()
                .orElse(null);
    }


}
