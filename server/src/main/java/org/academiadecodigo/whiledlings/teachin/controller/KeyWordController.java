package org.academiadecodigo.whiledlings.teachin.controller;

import org.academiadecodigo.whiledlings.teachin.command.KeyWordDto;
import org.academiadecodigo.whiledlings.teachin.converters.KeyWordDtoToKeyWord;
import org.academiadecodigo.whiledlings.teachin.converters.KeyWordToKeyWordDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.KeyWord;
import org.academiadecodigo.whiledlings.teachin.services.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/keywords")
public class KeyWordController {

    private KeyWordService keyWordService;

    private KeyWordToKeyWordDto keyWordToKeyWordDto;
    private KeyWordDtoToKeyWord keyWordDtoToKeyWord;

    @Autowired
    public void setKeyWordService(KeyWordService keyWordService) {
        this.keyWordService = keyWordService;
    }

    @Autowired
    public void setKeyWordToKeyWordDto(KeyWordToKeyWordDto keyWordToKeyWordDto) {
        this.keyWordToKeyWordDto = keyWordToKeyWordDto;
    }

    @Autowired
    public void setKeyWordDtoToKeyWord(KeyWordDtoToKeyWord keyWordDtoToKeyWord) {
        this.keyWordDtoToKeyWord = keyWordDtoToKeyWord;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<KeyWordDto>> listKeyWords() {

        List<KeyWordDto> keyWordDtos = new ArrayList<>();

        for (KeyWord keyWord : keyWordService.list()) {
            keyWordDtos.add(keyWordToKeyWordDto.convert(keyWord));
        }

        return new ResponseEntity<>(keyWordDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<HttpHeaders> addKeyWord(@RequestBody KeyWordDto keyWordDto, UriComponentsBuilder uriComponentsBuilder) {

        KeyWord savedKeyWord = keyWordService.save(keyWordDtoToKeyWord.convert(keyWordDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/keywords/" + savedKeyWord.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    public ResponseEntity<KeyWordDto> deleteKeyWord(@PathVariable Integer id) {

        keyWordService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<KeyWordDto> keyWordDetails(@PathVariable Integer id) {

        KeyWord keyWord;

        keyWord = keyWordService.get(id);

        return new ResponseEntity<>(keyWordToKeyWordDto.convert(keyWord), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    public ResponseEntity<KeyWordDto> editKeyWord(@RequestBody KeyWordDto keyWordDto, @PathVariable Integer id) {

        if (keyWordDto.getId() != null && !keyWordDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (keyWordService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        keyWordDto.setId(id);

        keyWordService.save(keyWordDtoToKeyWord.convert(keyWordDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
