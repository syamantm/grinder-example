package com.syamantakm.impl;

import com.syamantakm.api.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpPutDataProvider implements DataProvider<String> {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpPutDataProvider.class);
    public static final String JSON_TEMPLATE = "{ \"name\" : \"%s\" }";

    @Override
    public byte[] getData(String param) {
        String json = String.format(JSON_TEMPLATE, param);
        LOGGER.info(String.format("data : %s", json));
        return json.getBytes();
    }
}
