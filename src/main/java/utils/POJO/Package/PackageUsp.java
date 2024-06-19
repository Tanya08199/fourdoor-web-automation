package utils.POJO.Package;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageUsp {
    private String id;
    private String label;

}
