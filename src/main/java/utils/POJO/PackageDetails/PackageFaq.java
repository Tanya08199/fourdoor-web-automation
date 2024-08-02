package utils.POJO.PackageDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageFaq
{
    private String id;
    private String descr;
    private String title;
}
