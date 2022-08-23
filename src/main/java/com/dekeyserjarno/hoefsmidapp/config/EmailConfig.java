package com.dekeyserjarno.hoefsmidapp.config;

import org.springframework.stereotype.Service;

@Service
public interface EmailConfig {
    String getEmail();
    String getPassword();
}
