package com.syamantakm.api;

import java.util.List;

/**
 * @param <T> type of url parameter
 * @author Syamantak Mukhopadhyay
 */
public interface UrlProvider<T> {
    List<String> getUrl(T param);
}
