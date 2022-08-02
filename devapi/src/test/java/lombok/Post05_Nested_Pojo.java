package lombok;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Post05_Nested_Pojo extends ReqresBaseUrl {
    /*
    Given
         https://reqres.in/api/users

    And
          {
          "data": {
              "id": 23,
              "email": "xyz234@gmail.com",
              "first_name": "Muhammed",
              "last_name": "Yılmaz",
              "avatar": "https://reqres.in/img/faces/1-image.jpg"
          },
          "support": {
              "url": "https://reqres.in/#support-heading",
              "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
          }
      }
      When
          I send Post Request to the Url
      Then
          status code is 201
      And
         Respons body is like {
    "data": {
        "id": 23,
        "email": "xyz234@gmail.com",
        "first_name": "Muhammed",
        "last_name": "Yılmaz",
        "avatar": "https://reqres.in/img/faces/1-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
     */

    @Test
    public void Post01() {
        specRegres.pathParam("pp1", "users");
        DataPojo dataPojo = new DataPojo(23, "xyz234@gmail.com", "Muhammed", "Yılmaz", "https://reqres.in/img/faces/1-image.jpg");
        SupportPojo supportPojo = new SupportPojo("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
        AllBodyPojo expectedData = new AllBodyPojo(dataPojo, supportPojo);
        Response response=given().spec(specRegres).contentType(ContentType.JSON).body(expectedData).when().post("/{pp1}");
        AllBodyPojo actualData= JsonUtil.convertJsonToJavaObject(response.asString(),AllBodyPojo.class);
        // softAssert
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),201);
        softAssert.assertEquals(actualData.getData().getId(),expectedData.getData().getId());
        softAssert.assertEquals(actualData.getData().getEmail(),expectedData.getData().getEmail());
        softAssert.assertEquals(actualData.getData().getFirst_name(),expectedData.getData().getFirst_name());
        softAssert.assertEquals(actualData.getData().getLast_name(),expectedData.getData().getLast_name());
        softAssert.assertEquals(actualData.getData().getAvatar(),expectedData.getData().getAvatar());
        softAssert.assertEquals(actualData.getSupport().getUrl(),expectedData.getSupport().getUrl());
        softAssert.assertEquals(actualData.getSupport().getText(),expectedData.getSupport().getText());

        // all Class Assert
        // System.out.println("expectedData = " + expectedData);
        // System.out.println("actualData = " + actualData);
        softAssert.assertEquals(expectedData,actualData);
        softAssert.assertAll();

    }
}
