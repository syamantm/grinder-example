package com.syamantakm.impl;

import com.syamantakm.annotation.Resource;
import com.syamantakm.api.UrlProvider;
import com.syamantakm.dao.SimpleJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpGetUrlProvider implements UrlProvider<Integer> {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGetUrlProvider.class);
    public static final String URL_TEMPLATE = "http://localhost:4080/cache/entry/%s";

    @Resource
    private SimpleJdbcDao jdbcDao;

    public HttpGetUrlProvider() {
         LOGGER.info("@HttpGetUrlProvider()");
    }

    @Override
    public List<String> getUrl(Integer param) {
        List<String> urls = new ArrayList<>();
        List<Integer> ids = jdbcDao.getCacheEntryIds(param);
        for(int id : ids) {
            urls.add(String.format(URL_TEMPLATE, id));
        }
        return urls;
    }
}
