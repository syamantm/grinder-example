package com.syamantakm.api;

import HTTPClient.NVPair;

/**
 * @author Syamantak Mukhopadhyay
 */
public interface Request {
    public enum Method {
        GET,
        POST,
        PUT,
        DELETE;
    }

    int getRequestNumber();

    String getName();

    Method getMethod();

    String getUrl();

    String getData();

    NVPair[] getHeaders();

}
