package com.yangsen;

import com.yangsen.beans.factory.BeanFactory;
import com.yangsen.beans.factory.BeanDefinition;
import com.yangsen.beans.factory.suppot.DefaultBeanFactory;
import com.yangsen.day01.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class SpringFactoryTest {

    @Test
    public void springFactoryTest(){

        BeanFactory factory = new DefaultBeanFactory("spring.xml");
        BeanDefinition dbf = factory.getBeanDefinition("petStore");
        Assert.assertEquals("com.yangsen.day01.PetStoreService",dbf.getBeanClassName());
        PetStoreService petStoreService = (PetStoreService)factory.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

}
