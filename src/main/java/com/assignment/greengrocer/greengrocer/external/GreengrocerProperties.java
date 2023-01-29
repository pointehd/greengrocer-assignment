package com.assignment.greengrocer.greengrocer.external;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Component
@ConfigurationProperties("external.api.greengrocer")
public class GreengrocerProperties {

    private String url;

    public String getGreengrocerUrl(GreengrocerType greengrocerType) {
        return "http://"+greengrocerType.getValue() +"."+ url;
    }
}
