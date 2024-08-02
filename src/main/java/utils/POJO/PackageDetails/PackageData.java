package utils.POJO.PackageDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackageData {

    private String packageCode;
    private String name;
    private List<PackageFaq> faq;
    private List<PackageService> services;

}
