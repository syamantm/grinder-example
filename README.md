grinder-example
===============

Load test example with The Grinder, java, pico


References
------------

[The Grinder 3.11](http://grinder.sourceforge.net/)
[The Grinder with java](http://kjetilvalle.com/posts/java-grinder-tests.html)


Overview
---------------

### Key Concepts

* single script to bridge between java and jython
* Pico container to inject dependencies to Test Runner.

### Usage

* Create a test runner by extending [AbstractTestRunner.java](src/main/java/com/syamantakm/grinder/AbstractTestRunner.java):

    public class HttpGetTestRunner extends AbstractTestRunner {
    ....
    }

* Inject test dependencies with [@Resource](src/main/java/com/syamantakm/annotation/Resource.java) annotation :

    public class HttpGetTestRunner extends AbstractTestRunner {
        ....

        @Resource
        private UrlProvider urlProvider;

        ....
    }

* Based on the test need, a new implementation of the following providers/interfaces may be required:
    * [UrlProvider](src/main/java/com/syamantakm/api/UrlProvider.java) - provides URL(s) to be tested.
    * [DataProvider](src/main/java/com/syamantakm/api/DataProvider.java) - provides json data to be tested(for put/post etc.).
    * [HeaderProvider](src/main/java/com/syamantakm/api/HeaderProvider.java) - provides http headers required for each http call( application/json, test/plain etc.).

* Define the test runner and dependency classes in grinder properties(under src/test/grinder), e,g [http-get.properties](src/test/grinder/http-get.properties):

    #grinder properties file
    ......
    java_test_runner = com.syamantakm.grinder.HttpGetTestRunner
    resource_classes=com.syamantakm.dao.SimpleJdbcDao;net.grinder.plugin.http.HTTPRequest;com.syamantakm.impl.HttpGetUrlProvider
    .....

