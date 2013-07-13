package com.syamantakm.grinder;

import HTTPClient.NVPair;
import com.syamantakm.annotation.Resource;
import com.syamantakm.api.DataProvider;
import com.syamantakm.api.HeaderProvider;
import com.syamantakm.api.UrlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpPutTestRunner extends AbstractTestRunner {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpPutTestRunner.class);

    @Resource
    private UrlProvider urlProvider;

    @Resource
    private DataProvider dataProvider;

    @Resource
    private HeaderProvider headerProvider;

    public HttpPutTestRunner() {
        LOGGER.info("Default Constructor :: HttpGetTestRunner");
    }

    public void call() throws Exception {
        List<String> urls = urlProvider.getUrl(5);
        for (String url : urls) {
            NVPair[] headers = getHeaders(headerProvider.getHttpHeaders());
            byte[] data = dataProvider.getData("lt-" + System.currentTimeMillis());
            this.test.PUT(url, data, headers);
        }
    }

    private NVPair[] getHeaders(Map<String, String> headerMap) {
        List<NVPair> headers = new ArrayList<>();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            NVPair pair = new NVPair(entry.getKey(), entry.getValue());
            headers.add(pair);
        }
        return headers.toArray(new NVPair[headers.size()]);
    }
}
