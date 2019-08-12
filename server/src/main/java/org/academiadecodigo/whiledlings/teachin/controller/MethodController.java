package org.academiadecodigo.whiledlings.teachin.controller;

import org.academiadecodigo.whiledlings.teachin.command.MethodDto;
import org.academiadecodigo.whiledlings.teachin.converters.MethodDtoToMethod;
import org.academiadecodigo.whiledlings.teachin.converters.MethodToMethodDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Method;
import org.academiadecodigo.whiledlings.teachin.services.MethodService;
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
@RequestMapping("/api/methods")
public class MethodController {

    private MethodService methodService;

    private MethodToMethodDto methodToMethodDto;
    private MethodDtoToMethod methodDtoToMethod;

    @Autowired
    public void setMethodService(MethodService methodService) {
        this.methodService = methodService;
    }

    @Autowired
    public void setMethodToMethodDto(MethodToMethodDto methodToMethodDto) {
        this.methodToMethodDto = methodToMethodDto;
    }

    @Autowired
    public void setMethodDtoToMethod(MethodDtoToMethod methodDtoToMethod) {
        this.methodDtoToMethod = methodDtoToMethod;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<MethodDto>> listMethods() {

        List<MethodDto> methodDtos = new ArrayList<>();

        for (Method method : methodService.list()) {
            methodDtos.add(methodToMethodDto.convert(method));
        }

        return new ResponseEntity<>(methodDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<HttpHeaders> addMethod(@RequestBody MethodDto methodDto, UriComponentsBuilder uriComponentsBuilder) {

        Method savedMethod = methodService.save(methodDtoToMethod.convert(methodDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/methods/" + savedMethod.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    public ResponseEntity<MethodDto> deleteMethod(@PathVariable Integer id) {

        methodService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<MethodDto> methodDetails(@PathVariable Integer id) {

        Method method;

        method = methodService.get(id);

        return new ResponseEntity<>(methodToMethodDto.convert(method), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    public ResponseEntity<MethodDto> editMethod(@RequestBody MethodDto methodDto, @PathVariable Integer id) {

        if (methodDto.getId() != null && !methodDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (methodService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        methodDto.setId(id);

        methodService.save(methodDtoToMethod.convert(methodDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
