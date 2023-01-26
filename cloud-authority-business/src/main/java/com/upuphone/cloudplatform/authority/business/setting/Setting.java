package com.upuphone.cloudplatform.authority.business.setting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Setting {
    @Value("${system.aes.key:RwcmlVtt}")
    private String systemSecretAesKey;

    @Value("${system.aes.iv:4e5Wa71fYoT7ktlg}")
    private String systemSecretAesIV;
}
