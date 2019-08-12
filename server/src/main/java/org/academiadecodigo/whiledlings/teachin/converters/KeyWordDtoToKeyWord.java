package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.KeyWordDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.KeyWord;
import org.academiadecodigo.whiledlings.teachin.services.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;

@Component
public class KeyWordDtoToKeyWord implements Converter<KeyWordDto, KeyWord> {

    private KeyWordService keyWordService;

    @Autowired
    public void setKeyWordService(KeyWordService keyWordService) {
        this.keyWordService = keyWordService;
    }

    @Override
    public KeyWord convert(KeyWordDto keyWordDto) {

        KeyWord keyword = (keyWordDto.getId() != null ? keyWordService.get(keyWordDto.getId()) : new KeyWord());

        keyword.setDescription(keyWordDto.getDescription());

        return keyword;
    }
}
