package utils.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperCategory {

    private String categoryCode;
    private  String name;
    private  String slug;
    private String  cityCode;
    private String searchRanking;
    private  MetaData metadata;


}
