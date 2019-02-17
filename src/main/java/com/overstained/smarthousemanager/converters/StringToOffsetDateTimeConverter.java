package com.overstained.smarthousemanager.converters;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOffsetDateTimeConverter  implements Converter<String, OffsetDateTime> {

	private static final String PATTERN = "dd.MM.yyyy'T'HH:mm";
	
    @Override
    public OffsetDateTime convert(String source) {
        return ZonedDateTime.parse(source, DateTimeFormatter.ofPattern(PATTERN).withZone(ZoneId.of("UTC"))).toOffsetDateTime();
    }

}
