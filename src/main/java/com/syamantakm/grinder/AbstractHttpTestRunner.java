package com.syamantakm.grinder;

import com.syamantakm.annotation.Resource;
import com.syamantakm.api.Request;
import com.syamantakm.api.RequestProvider;
import com.syamantakm.util.PropertyHolder;
import net.grinder.plugin.http.HTTPRequest;
import net.grinder.script.NotWrappableTypeException;
import net.grinder.script.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Syamantak Mukhopadhyay
 */
public abstract class AbstractHttpTestRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpTestRunner.class);

    @Resource
    private RequestProvider requestProvider;

    @Resource
    private PropertyHolder propertyHolder;

    private Map<Integer, HTTPRequest> tests;

    public AbstractHttpTestRunner() {
        LOGGER.info("AbstractHttpTestRunner :: constructor");
    }

    public void init() throws NotWrappableTypeException {
        LOGGER.info("Default Constructor :: HttpTestRunner");
        tests = new HashMap<>();
        for(Request req : requestProvider.getRequests()) {
            if(tests.containsKey(req.getRequestNumber())) {
                LOGGER.warn(String.format("Duplicate request found , number : %s", req.getRequestNumber()));
            }
            HTTPRequest test = new HTTPRequest();
            test = (HTTPRequest) new Test(req.getRequestNumber(), req.getName()).wrap(test);
            tests.put(req.getRequestNumber(), test);
        }
    }

    public abstract void call() throws Exception;

    protected RequestProvider getRequestProvider() {
        return requestProvider;
    }

    protected Map<Integer, HTTPRequest> getTests() {
        return tests;
    }

    protected PropertyHolder getPropertyHolder() {
        return propertyHolder;
    }
}
