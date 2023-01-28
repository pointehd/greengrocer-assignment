package com.assignment.greengrocer.greengrocer.external;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("external.api.greengrocer")
public class GreengrocerProperties {

    private String url;
    private List<String> types;

}
