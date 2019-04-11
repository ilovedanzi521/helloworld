package com.wanglei.spring.formework.context;

import com.wanglei.spring.formework.beans.GPBeanFactory;
import com.wanglei.spring.formework.beans.config.GPBeanDefinition;
import com.wanglei.spring.formework.beans.support.GPBeanDefinitionReader;
import com.wanglei.spring.formework.beans.support.GPDefaultListableBeanFactory;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GPApplicationContext
 * @Description TODO
 * @Author yuman
 * @Date 2019/4/11 13:45
 * @菜鸡加油 run run run
 */
public class GPApplicationContext extends GPDefaultListableBeanFactory implements GPBeanFactory {
    private String[] configLoaction;
    private GPBeanDefinitionReader reader;

    public GPApplicationContext(String... configLoaction) {
        this.configLoaction = configLoaction;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void refresh() throws Exception {
        //1 定位
        reader = new GPBeanDefinitionReader(this.configLoaction);

        //2 加载
        List<GPBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        //3注册
        doRegisterBeanDefinition(beanDefinitions);
        
        //4 NOT LAZY INIT
        doAutowrited();
    }
    //not lazy init
    private void doAutowrited() {
        for (Map.Entry<String, GPBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
             String beanName = beanDefinitionEntry.getKey();
             if(!beanDefinitionEntry.getValue().isLazyInit()){
                 getBean(beanName);
             }

        }

    }

    private void doRegisterBeanDefinition(List<GPBeanDefinition> beanDefinitions) throws Exception {
        for (GPBeanDefinition beanDefinition : beanDefinitions) {
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw  new Exception("the "+beanDefinition.getFactoryBeanName()+"is exsit");
            }
            super.beanDefinitionMap.put(beanDefinition.getBeanClassName(),beanDefinition);

        }

    }

    @Override
    public Object getBean(String beanname) {
        return null;
    }

}
