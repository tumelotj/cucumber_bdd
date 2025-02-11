package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    ResponseSpecification resp;
    public RequestSpecification requestSpecBuilder() throws IOException {

        if(req==null)
        {
            //PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key","qaclick123")
                    //.addFilter(RequestLoggingFilter.logRequestTo(log))
                    //.addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public ResponseSpecification responseSpecBuilder()
    {
        return resp = new ResponseSpecBuilder().expectStatusCode(200).build();
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\tumel\\IdeaProjects\\Project2\\APIFramework\\src\\test\\java\\resource\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }

    public String getJsonPath(Response res, String key)
    {
        JsonPath js = new JsonPath(res.asString());
        return js.get(key);

    }
}
