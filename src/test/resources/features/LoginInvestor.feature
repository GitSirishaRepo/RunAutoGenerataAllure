@UI
Feature:  Login Page Feature
 @smokeTest
  Scenario: Investor Validate Page Title
    Given user is on Investor Login page
    And wait for page to load
  Then page title should be "Log-in"

  @Regression
  Scenario Outline: Investor Login successful
    Given user is on Investor Login page
    And wait for page to load
    When enter username <string> and password <string2>
    When click on Sign in button
    Examples:
      | string                            | string2         |
      | "bhargavi.sponsor+qa26@gmail.com" | "Lsnworks@2022" |

  @Sanity
  Scenario: Investor Sign/Create  Successful

      Given user is on Investor Login page
     And wait for page to load
      When user clicks on Sign in link
      And waits for some time
      And enters email clicks send verification button
      When opt is received and enters otp
      And waits for some time
      And clicks verify button

 @Regression @smokeTest
  Scenario Outline: Investor Login successful upload
    Given user is on Investor Login page
    And wait for page to load
    When enter username <string> and password <string2>
    When click on Sign in button
    Then wait for Verify Identity
    And upload and VerifyIdentity


    Examples:
      | string                          | string2        |
      | "bhargavi.sponsor111@gmail.com" | "Lsnworks@2022" |
