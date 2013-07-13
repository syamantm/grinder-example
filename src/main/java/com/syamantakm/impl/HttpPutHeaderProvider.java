package com.syamantakm.impl;

import com.syamantakm.api.HeaderProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Syamantak Mukhopadhyay
 */
public class HttpPutHeaderProvider implements HeaderProvider {
    @Override
    public Map<String, String> getHttpHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "text/plain");
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
