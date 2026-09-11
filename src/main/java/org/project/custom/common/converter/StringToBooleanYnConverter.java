package org.project.custom.common.converter;

import org.project.custom.common.constants.YnType;
import org.project.custom.common.util.StringCheck;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

/** 요청 파라미터 "Y"/"N"/"true" -> boolean 바인딩 */
public class StringToBooleanYnConverter implements Converter<String, Boolean> {

    @Override
    public Boolean convert(@NonNull String source) {
        if(StringCheck.isEmpty(source)){
            return null;
        }

        return YnType.Y.isEqual(source);
    }
}