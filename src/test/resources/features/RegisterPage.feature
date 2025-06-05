Feature: User Registration

  Background:
    Given the user navigates to the registration page

  @regression @register @smoke
  Scenario Outline: Registering a new user with valid data
    When the user enters "<firstName>", "<lastName>", "<telephone>", "<password>" and subscribes "<subscribe>"
    Then the user registration should be successful

    Examples:
      | firstName | lastName | telephone   | password  | subscribe |
      | vishal    | mehta    | 9876543211  | vishal@123| yes       |
      | jyothi    | sharma   | 9876543212  | jyothi@123| no        |
      | Archana   | verma    | 9876543209  | arch@123  | yes       |
