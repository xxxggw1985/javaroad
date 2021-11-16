package com.javaroad.simpledi.xml;

import com.javaroad.di.BeanDefinitionException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.javaroad.simpledi.beans.BeanDefinition;
import com.javaroad.simpledi.beans.GenericBeanDefiniton;
import com.javaroad.simpledi.beans.support.BeanDefinitionRegistry;
import com.javaroad.simpledi.core.Resource;
import com.javaroad.simpledi.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-21 14:59
 * @version: 1.0
 */
public class XmlBeanDefinitionReader {
    private static  final  String BEAN_ID_ATTRIBUTE = "id";
    private static  final  String BEAN_CLASS_ATTRIBUTE = "class";
    private static  final  String BEAN_SCOPE_ATTRIBUTE = "scope";
    private static  final  String PROPERTY_ATTRIBUTE = "property";
    private static  final  String REF_ATTRIBUTE = "ref";
    private static  final  String VALUE_ATTRIBUTE = "value";
    private static  final  String NAME_ATTRIBUTE = "name";
    private static  final  String TYPE_ATTRIBUTE = "type";
    public static final String BEAN_NAMESPACE_URI = "http://www.springframework.org/schema/beans";
    public static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";
    public static final String AOP_NAMESPACE_URI = "http://www.springframework.org/schema/aop";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry){
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource){
        try(InputStream is = resource.getInputStream()){
            SAXReader saxReader  = new SAXReader();
            Document document = saxReader.read(is);
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()){
                Element element = iterator.next();
                String namespaceUri  =  element.getNamespaceURI();
                if(isDefaultNamespace(namespaceUri)){
                    parseDefaultElement(element);
                }
            }
        }catch (IOException | DocumentException e){
            throw new BeanDefinitionException("IOException parsing XML document:" + resource, e);
        }

    }

    private void parseDefaultElement(Element element) {
        String beanId = element.attributeValue(BEAN_ID_ATTRIBUTE);
        String beanClassName = element.attributeValue(BEAN_CLASS_ATTRIBUTE);
        BeanDefinition bd  = new GenericBeanDefiniton(beanId,beanClassName);
    }

    private boolean isDefaultNamespace(String namespaceUri) {
        return (StringUtils.hasLength(namespaceUri) && BEAN_NAMESPACE_URI.equals(namespaceUri));
    }
}
