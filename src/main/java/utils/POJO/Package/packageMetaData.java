package utils.POJO.Package;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class packageMetaData {
    private String h1;
    private String title;
    private  String description;
    private List<PackageImages> images;
    private List<PackageUsp> uspIds;


}
