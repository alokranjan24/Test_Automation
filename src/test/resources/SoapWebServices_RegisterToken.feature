@SmokeTest
  Feature: Register New Token

    @RegisterToken
    Scenario: SoapUI Services for Registering New Token
      Given Customer Enter Register Token Details
      When Participant Call Register Token
      Then Shared Directory Return the Token Status a Active with Token details
      And User verifies token status in database
      And User verifies Citrix Server logs