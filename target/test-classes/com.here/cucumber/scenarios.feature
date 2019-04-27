Feature: scenarios
  Replication of scenarios from documentation

  Scenario: Success
    Given Driving Mode is Normal
    When Input Event is Speed Limit 50
    Then We should see the current speed as 50

  Scenario: Ignore
    Given Driving Mode is Sport
    When Set Input Event followed consecutively same set Input Event
    Then We should see no change in Speed when the second set Event was applied

  Scenario: Invalid
    Given Driving Mode is Safe
    When InputEvent is greater than 100
    Then We should see Invalid output

