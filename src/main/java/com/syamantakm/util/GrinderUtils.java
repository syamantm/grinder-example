package com.syamantakm.util;

import HTTPClient.NVPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Syamantak Mukhopadhyay
 */
public class GrinderUtils {

    public static NVPair[] convertToNVPairs(Map<String, String> headerMap) {
        List<NVPair> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            NVPair pair = new NVPair(entry.getKey(), entry.getValue());
            pairs.add(pair);
        }
        return pairs.toArray(new NVPair[pairs.size()]);
    }
}
