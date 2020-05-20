Feature: Collaborator End Point
  Background:
    * url 'http://localhost:9290'
    * header Accept = 'application/json'

  Scenario: Testing valid GET endpoint
    Given path 'collaborator/all'
    When method GET
    Then status 200
    * def first = response[0]
    And match first contains {mailAdresse:"adangote@sqli.com"}

  Scenario: Testing OK reponse GET Projects Collaborator by email
    Given  path '/collaborator/projects'
    And param email = 'hamza@sqli.com'
    When method GET
    Then status 200
    * def first = response[1]
    And match first contains {projectTitle:"AXA"}

  Scenario: Testing error response GET Projects Collaborator by email
    Given  path '/collaborator/projects'
    And param email = 'ko@sqli.com'
    When method GET
    Then status 404

  Scenario: Testing OK reponse GET Interviews collaborator by email
    Given  path '/collaborator/all/interview'
    And param email = 'yelouardi@sqli.com'
    When method GET
    Then status 200
    * def first = response[0]
    And match first contains {interviewTitle:"Meeting after Mission"}


  Scenario: Testing error response GET Interviews collaborator by email
    Given  path '/collaborator/all/interview'
    And param email = 'ko@sqli.com'
    When method GET
    Then status 404

  Scenario: Add new Collaborator KO response
    Given  path 'collaborator'
    And request { mailAdresse: 'yelouardi@sqli.com' }
    When method POST
    Then status 302
    And match $ == "This User is Founded"