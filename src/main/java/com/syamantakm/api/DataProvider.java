package com.syamantakm.api;

/**
 * @param <T> type of param
 * @author Syamantak Mukhopadhyay
 */
public interface DataProvider<T> {
    byte[] getData(T param);
}
