# Synopsis
The API accepts 3 parameters to query the twitter API and serve the results as json. API used GET request with 3 parameters, which are mentioned above with their name, tyep and required or optional.

1. Name = query, Type = string, Required = true <br>
    This the query text to be searched on twitter.
2. Name = num, Type = Integer, Required = false<br>
    Number of results to serve. By default serves 20 results in case this parameter is missing.
3. Name = exact, Type = Boolean, Required = false<br>
    Should the result return only phrases which matches the exact search or in case of false return like result.

# Code
Browser testing url: http://localhost:9000/query?query="mars%20mission"&exact=false&num=10
<br>One can also test it using webservice testing tools like Postman which is easily available as Chrome plugin. 
<br><br>
Technology stack used: <br> 
1. Spring Boot <br> 
2. Spring Rest Services <br> 
3. Spring social<br>
3. Java 8<br>
4. Maven
<br><br>
It has TwitterController which is the entry point for this service. It serves the request with path = "/query" means whenever request is made using /query it will be processed by this controller.
Request is then forwarded to TwitterSearchEngine which has methods to serve the purpose. You can visit to the class and check the comments or code which is simple and easily understanble.

<br>
It has different DTOs to hold the input and output data. valid input is checked and passed to the TwitterSearchEngine class and mapped to the SearchInput for further processing. 

<br>
Output is mapped to the SearchOutput and then returned which is automatically transformed as json from inbuild jackson in spring.

<br>
It logs the debugging data to the twitter.log file initially but it ca be changed in application.properties file by using property logging.path property.

<br>
GlobalExceptionHandler is used as ControllerAdvice which process all the exceptions thrown in the application. It has some specific handler methods to process the exception and one method with Exception in case no handler is present for the exception thrown.

<br>
Note: To connect to twitter you need 4 values which are defined above and added to the application.properies file. These values are used to authenticate the user to the twitter using OAuth. It can be retrieved by  registering an application at https://dev.twitter.com/apps.

<br>
consumerKey="consumerKey"<br>
consumerSecret="consumerSecret"<br>
accessToken="accessToken"<br>
accessTokenSecret="accessTokenSecret"<br>
<br>

# Installation and Running Application 
You should have java 8 installed on your system.

<br>
Repository contains the jar file, which can be run from command prompt using above command. Please check that you have rights on the directoy where jar is placed. 

<br>
By default ports are given as server.port: 9000 and management.port: 9001.

<br>
In case ports are already in use we can send those directly using command line argument.

<br>
If you start it from command line and it doesnt start means you have system rights problem so place it somewhere else. in ase it started with printing something on CMD and again stopped then check logs twitter.log.

<br>
> java -jar twitter-search-0.0.1.jar

<br>
you can also use --debug to check application logs.<br>
> java -jar twitter-search-0.0.1.jar --debug

<br>
you can also use --trace option to check all the logs from Spring mvc like parameter, request specific logs.<br>
> java -jar twitter-search-0.0.1.jar --trace

<br>
In case ports are already used then use above command to specify port. <br>
> java -jar -Dmanagement.port=9991 -Dserver.port=9992  twitter-search-0.0.1.jar

<br>
Code can be imported to any IDE and you can use Maven to build and package the project. You can also use command prompt and use maven to build and package project.

<br>
# Tests

There are integration and unit testcases to test the functionality. TwitterApplicationTests is the class which contains all the integration tests and TwitterSearchEngineTest contains unit testcases.




