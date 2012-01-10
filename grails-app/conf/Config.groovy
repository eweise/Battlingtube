// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text-plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        all: '*/*',
        json: ['application/json', 'text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true

// set per-environment serverURL stem for creating absolute links
environments {
  production {
    bt {
      sendEmails = true
      loadTestData = false
      testdate {
        on = false
      }
    }
  }

}

// log4j configuration
log4j {
  appender.stdout = "org.apache.log4j.ConsoleAppender"
  appender.'stdout.layout' = "org.apache.log4j.PatternLayout"
  appender.'stdout.layout.ConversionPattern' = '[%r] %c{2} %m%n'
  appender.errors = "org.apache.log4j.FileAppender"
  appender.'errors.layout' = "org.apache.log4j.PatternLayout"
  appender.'errors.layout.ConversionPattern' = '[%r] %c{2} %m%n'
  appender.'errors.File' = "stacktrace.log"
  rootLogger = "info,stdout"
  logger {
    grails = "info"
    StackTrace = "error,errors"
    org {
      codehaus.groovy.grails.web.servlet = "info" //  controllers
      codehaus.groovy.grails.web.pages = "info" //  GSP
      codehaus.groovy.grails.web.sitemesh = "info" //  layouts
      codehaus.groovy.grails."web.mapping.filter" = "info" // URL mapping
      codehaus.groovy.grails."web.mapping" = "info" // URL mapping
      codehaus.groovy.grails.commons = "info" // core / classloading
      codehaus.groovy.grails.plugins = "info" // plugins
      codehaus.groovy.grails.orm.hibernate = "info" // hibernate integration
      springframework = "off"
      hibernate = "on"
    }
  }
  additivity.StackTrace = false

}

grails {
  mail {
    host = "smtp.gmail.com"
    port = 465
    username = "adminuser@site.com"
    password = "password"
    from = "mail@site.com"
    props = ["mail.smtp.auth": "true",
            "mail.smtp.ssl.enable": "true",
            "mail.smtp.starttls.enable":"true",
            "mail.smtp.socketFactory.port": "465",
            "mail.smtp.socketFactory.class": "javax.net.ssl.SSLSocketFactory",
            "mail.smtp.socketFactory.fallback": "false"]
  }
}


bt {
  name = "site.com"
  sendEmails = false
  loadTestData = true
  testdate {
    on = true
    value = "9/23/08"
  }
  media {
    context.root = "/bt/testmedia"
    musicpath = "music"
    imagepath = "battleimage"
    size = 100
  }

}

// The following properties have been added by the Upgrade process...
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
