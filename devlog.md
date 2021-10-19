# Development process
1. Set up development environment
    * Java JDK 11
    * Maven 3.8.3
    * JetBrains IDEA (for familiarity)
2. Stage one - Creation and Data storage
   * Generate baseline code with Spring Initilizr (https://start.spring.io/)
   * Initialize git repository and commit baseline
   * Define JPA data models
     * Design decision: Data Integrity
   * Define JPA repository
   * Unit tests to exercise low level data elements
     * Playlist: Toby Mac _This Is Not A Test_
3. Stage two - API
   * Define controller
   * Define Unit Tests to exercise endpoints
   * Define Spring Web endpoints and document with Swagger
4. Stage three - User Interface

# Design Decisions
1. Data Integrity

   There are a handful of potential reactions we could give to the data model in response to being offered data that does not meet our expectations. One option is that the model object could test the data and throw an exception when expectations are not met. This puts the onus of action on the user interface. An alternative is for the model to simply massage the data offered into the formats it expects. This example is going to take the second path because it is simpler code.