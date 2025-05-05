package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
@Getter
public class Dorama {
    Integer id;
    String title;
    int releaseYear;
    double score;
}
