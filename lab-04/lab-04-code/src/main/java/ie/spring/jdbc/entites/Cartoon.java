package ie.spring.jdbc.entites;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cartoon {
    private int cartoonId;
    private String name;
    private int firstAppearanceYear;
}