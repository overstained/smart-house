package com.overstained.smarthousemanager.converters;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class OffsetDateTimeToDateConverter implements Converter<OffsetDateTime, Date> {

    @Override
    public Date convert(OffsetDateTime source) {
        return source == null ? null : Date.from(source.toInstant());
    }

}
