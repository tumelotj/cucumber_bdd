package resources;

import pojo.AddPlace;
import pojo.Location;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String lang, String address)
    {
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        List<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(lang);
        return addPlace;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
    }
}
