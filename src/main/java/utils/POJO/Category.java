package utils.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

    private String categoryCode;
    private  String name;
    private List<Packages> packages;
}
