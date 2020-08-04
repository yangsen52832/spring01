package com.yangsen.beans.factory;
import com.yangsen.beans.factory.BeanDefinition;
public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanId);

    Object getBean(String beanId);
}
