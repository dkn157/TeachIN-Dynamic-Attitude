package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.MethodDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Method;
import org.springframework.stereotype.Component;

@Component
public class MethodToMethodDto extends AbstractConverter<Method, MethodDto>{

    @Override
    public MethodDto convert(Method method) {

        MethodDto methodDto = new MethodDto();

        methodDto.setId(method.getId());
        methodDto.setName(method.getName());
        methodDto.setDescription(method.getDescription());

        return methodDto;
    }

}
