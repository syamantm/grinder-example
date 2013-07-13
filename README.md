grinder-example
===============

Load test example with The Grinder, java, pico


References
------------

[The Grinder 3.11](http://grinder.sourceforge.net/)

[PicoContainer](http://picocontainer.codehaus.org/)

[The Grinder with java](http://kjetilvalle.com/posts/java-grinder-tests.html)


Overview
---------------

### Key Concepts

* single script to bridge between java and jython
* Pico cdependent_class_prop = grinder.getProperties().getProperty('resource_classes')

print dependent_class_prop

dependent_classes = []
for dep_c in dependent_class_prop.split(';'):
    dep_class = load_class(dep_c)
    print dep_class
    dependent_classes.append(dep_class)

class TestRunner:

    def __init__(self):
        pico = DefaultPicoContainer(AnnotatedFieldInjection(Resource));

        for dep_class in dependent_classes:
            pico.addComponent(dep_class);ontainer to inject dependencies to Test Runner.

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

        ......
        java_test_runner = com.syamantakm.grinder.HttpGetTestRunner
        resource_classes=com.syamantakm.dao.SimpleJdbcDao;net.grinder.plugin.http.HTTPRequest;com.syamantakm.impl.HttpGetUrlProvider
        .....


* [grinder_pico.py](src/test/grinder/grinder_pico.py) will inject all the dependencies to the test runner class using PicoContainer, which is available with grinder.
