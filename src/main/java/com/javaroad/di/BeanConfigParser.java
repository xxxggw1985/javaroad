package com.javaroad.di;

import java.io.InputStream;
import java.util.List;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-08 17:57
 * @version: 1.0
 */
public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream in) ;
//    List<BeanDefinition> parse(String configContent);
}
