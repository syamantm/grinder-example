package com.syamantakm.grinder;

import HTTPClient.NVPair;
import com.syamantakm.api.Request;
import net.grinder.plugin.http.HTTPRequest;
import net.grinder.script.NotWrappableTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpTestRunner extends AbstractHttpTestRunner {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpTestRunner.class);

    public HttpTestRunner() throws NotWrappableTypeException {
        LOGGER.info("Default Constructor :: HttpTestRunner");
    }

    public void call() throws Exception {
        for(Request request : getRequestProvider().getRequests()) {
            HTTPRequest test = getTests().get(request.getRequestNumber());
            LOGGER.info(String.format("Running test : %s", request.getRequestNumber()));
            NVPair[] headers = request.getHeaders();
            if(request.getMethod().equals(Request.Method.GET)) {
                test.GET(request.getUrl(), null, headers);
            }
            else if(request.getMethod().equals(Request.Method.PUT)) {
                test.PUT(request.getUrl(), request.getData().getBytes(), headers);
            }
        }

    }
}
