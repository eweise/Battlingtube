package com.bt.services

import org.springframework.core.io.Resource
import groovy.text.SimpleTemplateEngine
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class TemplateService implements ApplicationContextAware {

    ApplicationContext applicationContext

    boolean transactional = false

    public String generate(String template, Map binding) {
        Resource resource = applicationContext.getResource(template)
        def engine = new SimpleTemplateEngine()

        Writable writable = engine.createTemplate(resource.file).make(binding)
        return writable.toString()
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext
    }


}
