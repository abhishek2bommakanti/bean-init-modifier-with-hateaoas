/**
 *
 */
package com.abhishek.trial;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

/**
 * Copyright (C) 2015, GuestPlate Inc.
 *
 * @author abhishek
 *
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.setSerializerModifier(new MyBeanSerializerModifier());
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(simpleModule);
        
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setPrettyPrint(true);
		converter.setObjectMapper(objectMapper);
		
		converters.add(converter);
	}
	
	private static class MyBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public JsonSerializer<?> modifySerializer(SerializationConfig config,
                                                  BeanDescription beanDesc,
                                                  JsonSerializer<?> serializer) {
            if (IHideable.class.isAssignableFrom(beanDesc.getBeanClass())) {
                return new MyIHideableJsonSerializer((JsonSerializer<IHideable>) serializer);
            }
            return super.modifySerializer(config, beanDesc, serializer);
        }

        private static class MyIHideableJsonSerializer extends JsonSerializer<IHideable> {
            private final JsonSerializer<IHideable> serializer;

            public MyIHideableJsonSerializer(JsonSerializer<IHideable> serializer) {
                this.serializer = serializer;
            }

            @Override
            public void serialize(IHideable value,
                                  JsonGenerator jgen,
                                  SerializerProvider provider) throws IOException {
                if (!value.isHidden()) {
                     serializer.serialize(value, jgen, provider);
                }

            }
        }
    }
}
