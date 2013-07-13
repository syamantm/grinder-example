package com.syamantakm.impl;

import com.syamantakm.api.UrlProvider;
import com.syamantakm.dao.SimpleJdbcDao;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpPutUrlProvider implements UrlProvider<Integer> {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpPutUrlProvider.class);
    public static final String URL_TEMPLATE = "http://localhost:4080/cache/new";


    public HttpPutUrlProvider() {
         LOGGER.info("@HttpPutUrlProvider()");
    }

    @Override
    public List<String> getUrl(Integer param) {
        List<String> urls = new ArrayList<>();
        for(int i =0 ; i < param ; i++) {
            urls.add(String.format(URL_TEMPLATE));
        }
        return urls;
    }
}
