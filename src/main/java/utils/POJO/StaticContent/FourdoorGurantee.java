package utils.POJO.StaticContent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FourdoorGurantee {
    private Icon icon;
}
