package com.todayworker.springboot.domain.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}
