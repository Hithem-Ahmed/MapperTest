package com.mapping.com.mapping.util;

import lombok.experimental.UtilityClass;

import java.net.URL;
import java.util.Optional;

@UtilityClass
public class TestUtil {
    public String getFileNameFromResourcePath(String resourceName) {
        URL url = Optional.ofNullable(TestUtil.class.getResource(resourceName)).orElseThrow(
                ()-> new RuntimeException(resourceName + " not found"));

        return url.getFile();
    }
}
