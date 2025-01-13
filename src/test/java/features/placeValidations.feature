Feature: Validating Place API's

  Scenario: Verify if place is beingg successfully added using ADDPlaceAPI
    Given Add Pleace Payload
    When user calls "AddPlaceAPI" with Post http request
    Then the API call got success with status code 200
    And status response is OK