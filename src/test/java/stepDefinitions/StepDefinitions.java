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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class StepDefinitions {
    ResponseSpecification responseSpecification;
    RequestSpecification req;
    Response response;
    @Given("Add Place Payload")
    public void add_place_payload() {

        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        location.setLng(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage("French-IN");
        List<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
                .setBody(addPlace).build();
                 req = given().log().all().spec(requestSpecification);
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        response = req.when().post("/maps/api/place/add/json")
                .then().log().all().spec(responseSpecification).extract().response();
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @And("{string} in response is {string}")
    public void in_response_is(String string, String string2) {
        JsonPath js = new JsonPath(response.asString());
        Assert.assertEquals(string2,js.getString(string));

    }
}
