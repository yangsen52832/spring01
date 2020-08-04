package com.yangsen.beans.factory.suppot;

import com.yangsen.beans.factory.BeanDefinition;

public class GenericBeanDefiniton implements BeanDefinition {
    private String id;
    private String className;

    public GenericBeanDefiniton(String id, String beanClassName) {
        this.className=beanClassName;
        this.id=id;
    }

    @Override
    public String getBeanClassName() {
        return this.className = className;
    }
}
