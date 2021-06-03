package com.awesomeproject.service.converter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;

import java.util.Collection;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public abstract class SelfRegisteringConverter<S, T> implements Converter<S, T>, InitializingBean {
    protected final ConversionService conversionService;

    public SelfRegisteringConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public void afterPropertiesSet() {
        if (conversionService instanceof ConfigurableConversionService) {
            ((ConfigurableConversionService) conversionService).addConverter(this);
        }
    }

    protected <I, R> List<R> convertCollection(Collection<I> collection, Class<R> clazz) {
        return ofNullable(collection)
                .stream()
                .flatMap((Collection::stream))
                .map(elem -> conversionService.convert(elem, clazz))
                .collect(toList());
    }
}
