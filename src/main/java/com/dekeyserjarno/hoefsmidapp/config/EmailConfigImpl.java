package com.dekeyserjarno.hoefsmidapp.config;

import org.hibernate.annotations.Source;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:email.properties")
@ConfigurationProperties(prefix = "email")
public class EmailConfigImpl implements EmailConfig{

    private String email;
    private String password;

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
