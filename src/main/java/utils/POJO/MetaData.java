package utils.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData {

    private String h1;
    private  String title;
    private  String description;
    private  Icon icon;
    private  HeroImage heroImage;
}
