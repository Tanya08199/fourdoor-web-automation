package utils.POJO.StaticContent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaticContentData {
    private List<Banners> banners;
    private List<FourdoorGurantee> fourDoorGuarantee;
    private List<PriceComparison> priceComparison;
    private  List<Testimonial> testimonials;
}
