# Development process
1. Set up development environment
    * Java JDK 11 (https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
    * Maven 3.8.3 (https://maven.apache.org/download.cgi)
    * JetBrains IDEA
2. Stage one - Creation and Data storage
   * Generate baseline code with Spring Initilizr (https://start.spring.io/)
   * Initialize git repository and commit baseline
   * Define JPA data models
   * Create unit tests to exercise low level data elements
   * Define JPA repository
   * Unit tests to exercise repository function
3. Stage two - API
   * Define controller and endpoints
   * Define Unit Tests to exercise endpoints
   * Set up Swagger documentation
4. Stage three - User Interface
   * Install nvm-windows
   * Install npm/node
   * Create base app using starter
   * Review tutorials ... this is going to take longer than I would hope