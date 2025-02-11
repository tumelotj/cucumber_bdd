package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class StepDefinitions extends Utils {
    ResponseSpecification responseSpecification;
    RequestSpecification req;
    Response response;
    static String place_id;
    TestDataBuild data = new TestDataBuild();
  /*  @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }*/

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
                 req = given().log().all().spec(requestSpecBuilder()).body(data.addPlacePayload(name,language,address));
        //responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
    }
//.body(data.addPlacePayload())

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        if(method.equalsIgnoreCase("POST"))
            response = req.when().post(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response = req.when().get(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("DELETE"))
            response = req.when().delete(resourceAPI.getResource());


        // .then().log().all().spec(responseSpecBuilder()).extract().response();
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @Then("{string} in response is {string}")
    public void in_response_is(String key, String strValue) {
        String test = response.asString();
        JsonPath js = new JsonPath(test);
        String result = js.getString(key);
        Assert.assertEquals(strValue,result);
    }


    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        place_id = getJsonPath(response,"place_id");
        req=given().spec(requestSpecBuilder()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName= getJsonPath(response,"name");
        Assert.assertEquals(expectedName,actualName);

    }
    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        req=given().spec(requestSpecBuilder()).body(data.deletePlacePayload(place_id));
    }
}