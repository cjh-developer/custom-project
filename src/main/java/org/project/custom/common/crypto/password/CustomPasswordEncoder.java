package org.project.custom.common.crypto.password;

import org.project.custom.common.constants.EncodeType;
import org.project.custom.common.constants.PasswordHashType;

public interface CustomPasswordEncoder {

    String encode(String rawPassword, String salt);

    boolean matches(String rawPassword, String encodedPassword, String salt);

    PasswordHashType getPasswordHashType();

    EncodeType getEncodeType();
}
