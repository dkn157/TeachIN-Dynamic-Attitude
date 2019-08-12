package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.MethodDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Method;
import org.academiadecodigo.whiledlings.teachin.services.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;

@Component
public class MethodDtoToMethod implements Converter<MethodDto, Method> {

    private MethodService methodService;

    @Autowired
    public void setMethodService(MethodService methodService) {
        this.methodService = methodService;
    }

    @Override
    public Method convert(MethodDto methodDto) {

        Method method = (methodDto.getId() != null ? methodService.get(methodDto.getId()) : new Method());

        method.setName(methodDto.getName());
        method.setDescription(methodDto.getDescription());

        return method;
    }
}
