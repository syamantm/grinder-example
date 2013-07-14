package com.syamantakm.impl;

import HTTPClient.NVPair;
import com.syamantakm.api.Request;
import com.syamantakm.util.GrinderUtils;

import java.util.Map;

/**
 * @author Syamantak Mukhopadhyay
 */
public class GETRequest implements Request {
    private final String urlTemplate;
    private final Object[] urlTemplateParams;
    private final int requestNumber;
    private final String name;
    private final Map<String, String> headers;


    private GETRequest(String urlTemplate, Object[] urlTemplateParams,
                       int requestNumber, String name, Map<String, String> headers) {
        this.urlTemplate = urlTemplate;
        this.urlTemplateParams = urlTemplateParams;
        this.requestNumber = requestNumber;
        this.name = name;
        this.headers = headers;
    }

    @Override
    public int getRequestNumber() {
        return this.requestNumber;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public String getUrl() {
        return String.format(urlTemplate, urlTemplateParams);
    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public NVPair[] getHeaders() {
        return GrinderUtils.convertToNVPairs(headers);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String urlTemplate;
        private Object[] urlTemplateParams;
        private int requestNumber;
        private String name;
        private Map<String, String> headers;

        public Builder buildUrl(String urlTemplate, Object... urlTemplateParams) {
            this.urlTemplate = urlTemplate;
            this.urlTemplateParams = urlTemplateParams;
            return this;
        }

        public Builder buildHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder buildRequestNumber(int requestNumber) {
            this.requestNumber = requestNumber;
            return this;
        }

        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        public GETRequest build() {
            return new GETRequest(urlTemplate, urlTemplateParams, requestNumber, name, headers);
        }

    }
}
