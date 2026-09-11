package org.project.custom.web.config;

import org.project.custom.common.crypto.cipher.AesGcmCipher;
import org.project.custom.common.util.StringCheck;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class DBEncConfig implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String MARK_PREFIX = "ENC(";
    private static final String MARK_SUFFIX = ")";
    private static final String SOURCE_NAME = "decryptedProperties";

    private static final String KEY_ENV = "CUS_SECRET_KEY";
    private static final String KEY_PROP = "cus_secret.key";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Map<String,Object> dec = new HashMap<>();
        AesGcmCipher cipher = null;

        for( PropertySource<?> source : environment.getPropertySources()){
            if( !(source instanceof EnumerablePropertySource<?> enumerable)){
                continue;
            }

            for( String name : enumerable.getPropertyNames()){
                if( !(enumerable.getProperty(name) instanceof String text)){
                  continue;
                }
                if( !text.startsWith(MARK_PREFIX) || !text.endsWith(MARK_SUFFIX)){
                    continue;
                }
                if(cipher == null){
                    cipher = createCipher();
                }

                String body = text.substring(MARK_PREFIX.length(), text.length() - MARK_SUFFIX.length());
                dec.put(name, cipher.decrypt(body));
            }
        }

        if( !dec.isEmpty() ){
            environment.getPropertySources().addFirst(new MapPropertySource(SOURCE_NAME, dec));
        }
    }

    private AesGcmCipher createCipher() {

        String base64Key = System.getenv(KEY_ENV);
        if (StringCheck.isEmpty(base64Key)) {
            base64Key = System.getProperty(KEY_PROP);
        }

        if (StringCheck.isEmpty(base64Key)) {
            throw new IllegalStateException(
                    "ENC(...) 로 감싼 설정이 있으나 복호화 키가 없습니다. "
                            + "환경변수 " + KEY_ENV + " 또는 -D" + KEY_PROP + " 로 지정하세요.");
        }

        return AesGcmCipher.getByBase64(base64Key);
    }
}
