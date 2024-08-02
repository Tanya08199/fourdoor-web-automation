package utils.POJO.Package;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageImages {
    private String key;
    private String name;
    private String path;


}
