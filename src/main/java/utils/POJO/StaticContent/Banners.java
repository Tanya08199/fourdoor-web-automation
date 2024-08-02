package utils.POJO.StaticContent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Banners {

    private List<Msite> msite;
    private List<Desktop> desktop;

}
