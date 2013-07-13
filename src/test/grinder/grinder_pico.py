from net.grinder.plugin.http import HTTPRequest
from net.grinder.script.Grinder import grinder
from org.picocontainer import DefaultPicoContainer;
from org.picocontainer.injectors import AnnotatedFieldInjection;
from com.syamantakm.annotation import Resource

def load_class(class_name):
    m = __import__(class_name)
    for comp in class_name.split('.')[1:]:
        m = getattr(m, comp)
    return m

test_runner = load_class(grinder.getProperties().getProperty('java_test_runner'))

dependent_class_prop = grinder.getProperties().getProperty('resource_classes')

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
            pico.addComponent(dep_class);
        pico.addComponent(test_runner)

        self.runner = pico.getComponent(test_runner)
        self.runner.init()

    def __call__(self):
        self.runner.call()