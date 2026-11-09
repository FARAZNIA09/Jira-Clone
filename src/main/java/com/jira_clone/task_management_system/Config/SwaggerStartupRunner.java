
package com.jira_clone.task_management_system.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SwaggerStartupRunner implements ApplicationRunner {

    private static final Logger log =
            LoggerFactory.getLogger(SwaggerStartupRunner.class);

    private final Environment environment;

    public SwaggerStartupRunner(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) {

        String port = environment.getProperty(
                "server.port", "8080");

        String contextPath = environment.getProperty(
                "server.servlet.context-path", "");

        String baseUrl = "http://localhost:" + port + contextPath;

        log.info("");
        log.info("=================================================");
        log.info("       JIRA CLONE - APPLICATION STARTED");
        log.info("=================================================");
        log.info(" Swagger UI  : {}/swagger-ui/index.html", baseUrl);
        log.info(" OpenAPI JSON: {}/v3/api-docs", baseUrl);
        log.info("=================================================");
        log.info("");
    }
}
