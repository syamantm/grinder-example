package com.syamantakm.grinder;

import com.syamantakm.annotation.Resource;
import com.syamantakm.api.UrlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpGetTestRunner extends AbstractTestRunner {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGetTestRunner.class);

    @Resource
    private UrlProvider urlProvider;

    public HttpGetTestRunner() {
        LOGGER.info("Default Constructor :: HttpGetTestRunner");
    }

    public void call() throws Exception {
        List<String> urls = urlProvider.getUrl(5);
        for(String url : urls) {
            this.test.GET(url);
        }
    }
}
