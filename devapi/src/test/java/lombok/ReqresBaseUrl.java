package lombok;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class ReqresBaseUrl {
    protected RequestSpecification specRegres;

    @Before
    public void SetUp() {
        specRegres = new RequestSpecBuilder().setBaseUri("https://reqres.in/api/").build();
    }
}
