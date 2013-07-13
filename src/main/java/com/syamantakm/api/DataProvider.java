package com.syamantakm.api;

/**
 * @author Syamantak Mukhopadhyay
 * @param <T> type of param
 */
public interface DataProvider<T> {
    byte[] getData(T param);
}
