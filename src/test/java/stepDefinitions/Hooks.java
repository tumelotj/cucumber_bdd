package stepDefinitions;

import io.cucumber.java.Before;
import io.restassured.response.Response;

import java.io.IOException;

public class Hooks {

    Response response;
    @Before("@DeletePlace")
    public void test() throws IOException {



            StepDefinitions sd = new StepDefinitions();
        if (StepDefinitions.place_id ==null)
        {
            sd.add_place_payload_with("Arn","Sotho","Free-State");
            sd.user_calls_with_http_request("AddPlaceAPI","POST");
            sd.verify_place_id_created_maps_to_using("Arn","GetPlaceAPI");
        }

    }
}
