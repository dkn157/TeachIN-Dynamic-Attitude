package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.KeyWordDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.KeyWord;
import org.springframework.stereotype.Component;

@Component
public class KeyWordToKeyWordDto extends AbstractConverter<KeyWord, KeyWordDto>{

    @Override
    public KeyWordDto convert(KeyWord keyword) {

        KeyWordDto keyWordDto = new KeyWordDto();

        keyWordDto.setId(keyword.getId());
        keyWordDto.setDescription(keyword.getDescription());

        return keyWordDto;
    }

}
