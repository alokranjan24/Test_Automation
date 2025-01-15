@SmokeTest
  Feature: Get Token Status

    @GetTokenStatus
    Scenario: SoapUI Services for Get Token Status
      Given Customer Enter Token Details
      When Participant Call Get Token Status
      Then Shared Directory Return the Token Status