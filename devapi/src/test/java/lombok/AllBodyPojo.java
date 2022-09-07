package lombok;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllBodyPojo {
    private DataPojo data;
    private SupportPojo support;
}

