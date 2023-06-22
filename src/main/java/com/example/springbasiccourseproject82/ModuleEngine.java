package com.example.springbasiccourseproject82;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleEngine {

    @Autowired
    private Application application;

    public List<IModule> getSupportedModules(String path) {
        ApplicationContext context = application.getContext();
        List<IModule> result = new ArrayList<>();

        for (String name : context.getBeanDefinitionNames()) {
            Object bean = context.getBean(name);
            IModule module;
            try {
                module = (IModule) bean;
            } catch (Exception e) {
                continue;
            }
            if(module.isSupported(path))
                result.add(module);
        }

        return result;
    }

}