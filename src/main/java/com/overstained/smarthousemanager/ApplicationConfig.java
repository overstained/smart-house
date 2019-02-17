package com.overstained.smarthousemanager;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.overstained.smarthousemanager.converters.DateToOffsetDateTimeConverter;
import com.overstained.smarthousemanager.converters.OffsetDateTimeToDateConverter;
import com.overstained.smarthousemanager.converters.StringToOffsetDateTimeConverter;

@SpringBootApplication
public class ApplicationConfig implements WebMvcConfigurer {

	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new LinkedList<>();
		converters.add(new DateToOffsetDateTimeConverter());
		converters.add(new OffsetDateTimeToDateConverter());
		return new MongoCustomConversions(converters);
	}
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToOffsetDateTimeConverter());
    }

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfig.class, args);
	}

}
