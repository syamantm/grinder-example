grinder-example
===============

Load test example with The Grinder, java, pico


Dependencies
------------

[The Grinder 3.11](http://grinder.sourceforge.net/)


Overview
---------------

### Key Concepts

*single script to bridge between java and jython
*Pico container to inject dependencies to Test Runner.

### Usage

Create a test runner by extending [AbstractTestRunner.java](src/main/java/com/syamantakm/grinder/AbstractTestRunner.java)

    public class HttpGetTestRunner extends AbstractTestRunner {
    ....
    }

Inject test dependencies with [@Resource](src/main/java/com/syamantakm/annotation/Resource.java) annotation

    public class HttpGetTestRunner extends AbstractTestRunner {
        ....

        @Resource
        private UrlProvider urlProvider;

        ....
    }

