package com.syamantakm.api;

import java.util.List;

/**
 * @author Syamantak Mukhopadhyay
 * @param <T> type of url parameter
 */
public interface UrlProvider<T> {
    List<String> getUrl(T param);
}
