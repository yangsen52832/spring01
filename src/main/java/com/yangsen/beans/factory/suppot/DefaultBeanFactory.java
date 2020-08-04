package com.yangsen.beans.factory.suppot;

import com.yangsen.beans.factory.BeanDefinition;
import com.yangsen.beans.factory.BeanFactory;
import com.yangsen.utils.ClassUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.misc.ClassLoaderUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultBeanFactory implements BeanFactory {

    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();


    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

    /**
     * 解析xml
     * @param configFile
     */
    private void loadBeanDefinition(String configFile)  {
        InputStream in = null;
        try{
           ClassLoader cl = ClassUtils.getDefaultClassLoader();
           in = cl.getResourceAsStream(configFile);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(in);
            Element el = document.getRootElement();
            Iterator<Element> iterator = el.elementIterator();
            while (iterator.hasNext()){
                Element ele = iterator.next();
                String id = ele.attributeValue("id");
                String beanClassName = ele.attributeValue("class");
                BeanDefinition bd = new GenericBeanDefiniton(id,beanClassName);
                beanDefinitionMap.put(id,bd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }catch (Exception e){
              e.printStackTrace();
            }

        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if(bd == null){
            return null;
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clazz = cl.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
