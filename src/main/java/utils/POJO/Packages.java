package utils.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Packages {

    private String packageCode;
    private String name;
    private String categoryCode;
    private MetaData metadata;
    private String searchRanking;

}
