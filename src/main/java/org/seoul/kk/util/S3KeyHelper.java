package org.seoul.kk.util;

public class S3KeyHelper {

    public static String generateS3ObjectKey(Long travelerId, String fileName) {
        return String.format("playLand/%d/%s", travelerId, fileName);
    }

}
