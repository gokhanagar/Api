package lombok;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPojo {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
