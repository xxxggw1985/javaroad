package com.javaroad.simpledi.core;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 16:19
 * @version: 1.0
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
