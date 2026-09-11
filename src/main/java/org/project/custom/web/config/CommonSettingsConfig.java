package org.project.custom.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:config/custom.server.settings.xml", ignoreResourceNotFound = true)
})
public class CommonSettingsConfig {
}
