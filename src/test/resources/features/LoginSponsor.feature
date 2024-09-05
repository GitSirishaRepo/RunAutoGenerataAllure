@UI
Feature:  Login Page Feature
#@smoke
  Scenario: Sponsor Validate Page Title
    Given user is on Investor Login page
    And wait for page to load
  Then page title should be "Sign in"

 @smokeTest
  Scenario Outline: Sponsor Login successful
    Given user is on Investor Login page
    And wait for page to load
    When enter username <string> and password <string2>
    When click on Sign in button
    Examples:
      | string                            | string2         |
      | "bhargavi.sponsor+qa26@gmail.com" | "Lsnworks@2022" |

  @smokeTest
  Scenario: Sponsor Sign/Create  Successful

      Given user is on Investor Login page
      And wait for page to load
      When user clicks on Sign in link
      And waits for some time
      And enters email clicks send verification button
      When opt is received and enters otp
      And waits for some time
      And clicks verify button

  @smokeTest
  Scenario Outline: Sponsor Login successful upload
    Given user is on Investor Login page
    And wait for page to load
    When enter username <string> and password <string2>
    When click on Sign in button
    Then wait for Verify Identity
    And upload and VerifyIdentity

    Examples:
      | string                          | string2 |
      | "bhargavi.sponsor111@gmail.com" | "Lsnworks@2022"      |
