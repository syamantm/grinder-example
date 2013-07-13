package com.syamantakm.api;

import java.util.Map;

/**
 * @author Syamantak Mukhopadhyay
 */
public interface HeaderProvider {

    Map<String, String> getHttpHeaders();
}
