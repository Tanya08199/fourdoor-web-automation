package utils.POJO.Standalone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandaloneData {

    private String name;
    private String slug;
    private String superCategorySlug;
    private MetaData metadata;
    private LearnMore learnMore;
}
