package org.project.custom.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.project.custom.common.constants.YnType;

/**
 * JPA 변환 : Boolean 필드 <-> "Y"/"N" 컬럼
 * autoApply=false. 모든 Boolean 에 자동 적용되면 Y/N 이 아닌 컬럼까지 망가진다.
 * 엔티티에서 @Convert 로 명시 지정한다.
 */
@Converter
public class BooleanToYnConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn( Boolean value) {
        if( value == null){
            return null;
        }

        if(!value){
            return YnType.N.getValue();
        }

        return YnType.Y.getValue();
    }

    @Override
    public Boolean convertToEntityAttribute( String value) {
        if(value == null){
            return null;
        }
        return YnType.Y.isEqual(value);
    }
}