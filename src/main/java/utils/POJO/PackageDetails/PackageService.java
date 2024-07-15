package utils.POJO.PackageDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageService {

    private String serviceCode;
    private String name;
    private MetaData metadata;
}
