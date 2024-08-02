package utils.POJO.Standalone;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData {

    private List<Images> images;
    //private List<Highlights> highlights;


}
