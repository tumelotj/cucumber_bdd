package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.runner.Request;
import org.testng.asserts.Assertion;
import pojo.AddPlace;
import pojo.Location;
import resource.TestDataBuild;
import resource.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class StepDefinitions extends Utils {
    ResponseSpecification responseSpecification;
    RequestSpecification req;
    Response response;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload")
    public void add_place_payload() throws FileNotFoundException {
                 req = given().log().all().spec(requestSpecBuilder());
        //responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
    }
//.body(data.addPlacePayload())

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String AddPlaceAPI) {
        response = req.when().body(data.addPlacePayload()).post(AddPlaceAPI)
                .then().log().all().spec(responseSpecBuilder()).extract().response();
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

}