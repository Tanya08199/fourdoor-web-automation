package utils.POJO.Standalone;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetStandalone {

    private StandaloneData data;
}
