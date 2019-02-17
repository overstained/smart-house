package com.overstained.smarthousemanager.converters;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {

    @Override
    public OffsetDateTime convert(Date source) {
        return source == null ? null : OffsetDateTime.ofInstant(source.toInstant(), ZoneId.of("UTC"));
    }

}