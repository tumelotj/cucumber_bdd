package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utils {
    RequestSpecification requ;
    ResponseSpecification resp;
    public RequestSpecification requestSpecBuilder() throws FileNotFoundException {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        requ = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return requ;
    }

    public ResponseSpecification responseSpecBuilder()
    {
        return resp = new ResponseSpecBuilder().expectStatusCode(200).build();
    }
}
