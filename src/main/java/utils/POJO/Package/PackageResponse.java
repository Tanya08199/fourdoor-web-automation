package utils.POJO.Package;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageResponse {

    private String categoryCode;
    private String packageCode;
    private String name;
    private String slug;
    private String searchRanking;
    private packageMetaData metadata;

}

