package cn.lethekk.chapter5.converter;

import cn.lethekk.chapter5.enumeration.SexEnum;

import javax.persistence.AttributeConverter;

public class SexConverter implements AttributeConverter<SexEnum,Integer> {

    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
