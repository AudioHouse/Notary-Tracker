package com.audiohouse.notarytracker.shared.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CodecHash {

    public static String sha256(String stringToHash) {
        return DigestUtils.sha256Hex(stringToHash);
    }

}
