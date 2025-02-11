Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if place is being successfully added using ADDPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"
Examples:
    |name  |language  |address  |
    |Roys  |English   |Team321  |
#    |Brod  |French    |Joburg   |

  @DeletePlace
  Scenario: Verify if place added is being successfully deleted using DeletePlaceAPI
    Given Delete Place Payload
    When user calls "DeletePlaceAPI" with "DELETE" http request
    Then the API call got success with status code 200
    And "status" in response is "OK"
