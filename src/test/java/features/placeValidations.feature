Feature: Validating Place API's

  Scenario: Verify if place is being successfully added using ADDPlaceAPI
    Given Add Place Payload
    When user calls "/maps/api/place/add/json" with Post http request
    Then the API call got success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
