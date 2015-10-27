/**
 *
 */
package com.abhishek.trial;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

/**
 * Copyright (C) 2015, GuestPlate Inc.
 *
 * @author abhishek
 *
 */
@Configuration
public class WebMvcConfig {

    @Autowired
    @Qualifier("_halObjectMapper")
    private ObjectMapper halObjectMapper;

    @Bean
    ObjectMapper objectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.setSerializerModifier(new MyBeanSerializerModifier());
        this.halObjectMapper.registerModule(simpleModule);
        return this.halObjectMapper;
    }

    public static class MyBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public JsonSerializer<?> modifySerializer(SerializationConfig config,
                                                  BeanDescription beanDesc,
                                                  JsonSerializer<?> serializer) {
            if (IHideable.class.isAssignableFrom(beanDesc.getBeanClass())) {
                return new MyIHideableJsonSerializer((JsonSerializer<IHideable>) serializer);
            }
            return super.modifySerializer(config, beanDesc, serializer);
        }

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            return super.changeProperties(config, beanDesc, beanProperties);
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
                else {
                    jgen.writeString("hidden");
                }

            }
        }
    }
}
