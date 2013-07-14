package com.syamantakm.impl;

import com.syamantakm.annotation.Resource;
import com.syamantakm.api.Request;
import com.syamantakm.api.RequestProvider;
import com.syamantakm.dao.SimpleJdbcDao;
import com.syamantakm.util.PropertyHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpGetRequestProvider implements RequestProvider {
    public static final String URL_TEMPLATE = "http://localhost:4080/cache/entry/%s";

    @Resource
    private SimpleJdbcDao jdbcDao;

    @Resource
    private PropertyHolder propertyHolder;

    @Override
    public List<Request> getRequests() {

        List<Request> requests = new ArrayList<>();
        List<Integer> ids = jdbcDao.getCacheEntryIds(propertyHolder.getSelectLimit());
        for (int id : ids) {
            GETRequest request = GETRequest.builder()
                    .buildUrl(URL_TEMPLATE, id)
                    .buildRequestNumber(id)
                    .buildName(String.format("getCacheEntry_%d", id))
                    .buildHeaders(Collections.singletonMap("Accept", "application/json"))
                    .build();

            requests.add(request);
        }
        return requests;
    }

}
