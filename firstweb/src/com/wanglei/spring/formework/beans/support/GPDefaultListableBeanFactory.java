package com.wanglei.spring.formework.beans.support;

import com.wanglei.spring.formework.beans.config.GPBeanDefinition;
import com.wanglei.spring.formework.context.support.GPAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName GPDefaultListableBeanFactory
 * @Description TODO
 * @Author yuman
 * @Date 2019/4/11 13:44
 * @菜鸡加油 run run run
 */
public class GPDefaultListableBeanFactory extends GPAbstractApplicationContext {
    protected final Map<String, GPBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,GPBeanDefinition>();
}
