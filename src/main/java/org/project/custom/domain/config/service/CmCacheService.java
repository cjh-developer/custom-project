package org.project.custom.domain.config.service;

import org.project.custom.common.constants.CommonErrorCode;
import org.project.custom.common.exception.CustomException;
import org.project.custom.common.util.StringCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CmCacheService {

    Logger CONFIG_LOG = LoggerFactory.getLogger(CmCacheService.class);

    String getValue(String oid, String defaultValue);

    Map<String, String> getAllValues();

    default String getValue(String oid) {
        return getValue(oid, null);
    }

    default String getString(String oid, String defaultValue) {
        return getValue(oid, defaultValue);
    }

    default int getInt(String oid, int defaultValue){
        String value = getValue(oid);
        if(StringCheck.isEmpty(value)){
            return defaultValue;
        }

        try{
            return Integer.parseInt(value);
        }catch(NumberFormatException e){
            CONFIG_LOG.warn("정책 값이 숫자가 아닙니다. oid = {}, value = {} -> 기본값 적용 = {}",  oid, value, defaultValue );
            return defaultValue;
        }
    }

    default long getLong(String oid, long defaultValue){
        String value = getValue(oid);
        if(StringCheck.isEmpty(value)){
            return defaultValue;
        }
        try{
            return Long.parseLong(value);
        }catch (NumberFormatException e){
            CONFIG_LOG.warn("정책 값이 숫자가 아닙니다. oid = {}, value = {} -> 기본값 적용 = {}",  oid, value, defaultValue );
            return defaultValue;
        }
    }

    default double getDouble(String oid, double defaultValue){
        String value = getValue(oid);
        if(StringCheck.isEmpty(value)){
            return defaultValue;
        }
        try{
            return Double.parseDouble(value);
        }catch (NumberFormatException e){
            CONFIG_LOG.warn("정책 값이 숫자가 아닙니다. oid = {}, value = {} -> 기본값 적용 = {}",  oid, value, defaultValue );
            return defaultValue;
        }
    }

    default boolean getBoolean(String oid, boolean defaultValue){
        String value = getValue(oid);
        if(StringCheck.isEmpty(value)){
            return defaultValue;
        }
        return "Y".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
    }

    default List<String> getList(String oid, String delimiter, List<String> defaultValue){
        String value = getValue(oid);
        if(StringCheck.isEmpty(value)){
            return defaultValue;
        }

        List<String> result = new ArrayList<>();
        for( String t : value.split(delimiter) ){
            if( StringCheck.isNotEmpty(t)){
                result.add(t.trim());
            }
        }
        return result;
    }

    default List<String> getList(String oid){
        return getList(oid, ",", List.of());
    }

    default String[] getArray(String oid){
        return getList(oid).toArray(new String[0]);
    }

    default <E extends Enum<E>> E getEnum(Class<E> type, String oid, E defaultValue){
        String value = getValue(oid);
        if(StringCheck.isEmpty(value)){
            return defaultValue;
        }
        try{
            return Enum.valueOf(type, value.toUpperCase().replace('-', '_'));
        }catch (IllegalArgumentException e){
            CONFIG_LOG.warn("정책 값이 유효한 {}이 아닙니다. oid={}, value={} -> 기본값 적용", type.getSimpleName(), oid, value);
            return defaultValue;
        }
    }

    /**
     * 없으면 예외, 반드시 존재해야 하는 예외
     * @param oid
     * @return
     */
    default String getRequired(String oid){
        String value = getValue(oid);
        if(StringCheck.isEmpty(value)){
            throw new CustomException(CommonErrorCode.DATA_NOT_FOUND, "필수 정책이 없습니다.");
        }
        return value;
    }

    default Map<String, String> getByPrefix(String prefix){
        Map<String, String> result = new HashMap<>();
        getAllValues().forEach((key, value) -> {
            if (key.startsWith(prefix)) {
                result.put(key, value);
            }
        });
        return result;
    }
}
