package com.overstained.smarthousemanager;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClient;
import com.overstained.smarthousemanager.converters.DateToOffsetDateTimeConverter;
import com.overstained.smarthousemanager.converters.OffsetDateTimeToDateConverter;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

@Configuration
public class TestConfig {
	private static final String MONGO_DB_URL = "localhost";
	private static final String MONGO_DB_NAME = "embeded_db";

	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
		mongo.setBindIp(MONGO_DB_URL);
		mongo.setPort(27015);
		MongoClient mongoClient = mongo.getObject();

		List<Converter<?, ?>> converters = new LinkedList<>();
		converters.add(new OffsetDateTimeToDateConverter());
		converters.add(new DateToOffsetDateTimeConverter());
		CustomConversions customConversions = new MongoCustomConversions(converters);

		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, MONGO_DB_NAME);
		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory),
				 new MongoMappingContext());
		converter.setCustomConversions(customConversions);
		return new MongoTemplate(mongoDbFactory, converter);
	}
}
