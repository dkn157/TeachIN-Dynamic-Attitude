package org.academiadecodigo.whiledlings.teachin.controller;

import org.academiadecodigo.whiledlings.teachin.command.GroupDynamicDto;
import org.academiadecodigo.whiledlings.teachin.command.UserDto;
import org.academiadecodigo.whiledlings.teachin.converters.GroupDynamicDtoToGroupDynamic;
import org.academiadecodigo.whiledlings.teachin.converters.GroupDynamicToGroupDynamicDto;
import org.academiadecodigo.whiledlings.teachin.converters.UserDtoToUser;
import org.academiadecodigo.whiledlings.teachin.converters.UserToUserDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.GroupDynamic;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Issue;
import org.academiadecodigo.whiledlings.teachin.persistence.model.User;
import org.academiadecodigo.whiledlings.teachin.services.GroupDynamicService;
import org.academiadecodigo.whiledlings.teachin.services.IssueService;
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
@RequestMapping("/api/dynamics")
public class GroupDynamicController {

    private GroupDynamicService groupDynamicService;
    private IssueService issueService;


    private GroupDynamicToGroupDynamicDto groupDynamicToGroupDynamicDto;
    private GroupDynamicDtoToGroupDynamic groupDynamicDtoToGroupDynamic;

    @Autowired
    public void setGroupDynamicService(GroupDynamicService groupDynamicService) {
        this.groupDynamicService = groupDynamicService;
    }

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @Autowired
    public void setGroupDynamicToGroupDynamicDto(GroupDynamicToGroupDynamicDto groupDynamicToGroupDynamicDto) {
        this.groupDynamicToGroupDynamicDto = groupDynamicToGroupDynamicDto;
    }

    @Autowired
    public void setGroupDynamicDtoToGroupDynamic(GroupDynamicDtoToGroupDynamic groupDynamicDtoToGroupDynamic) {
        this.groupDynamicDtoToGroupDynamic = groupDynamicDtoToGroupDynamic;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<GroupDynamicDto>> listDynamics() {

        List<GroupDynamicDto> groupDynamics = new ArrayList<>();

        for (GroupDynamic groupDynamic : groupDynamicService.list()) {
            groupDynamics.add(groupDynamicToGroupDynamicDto.convert(groupDynamic));
        }

        return new ResponseEntity<>(groupDynamics, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<HttpHeaders> addDynamics(@RequestBody GroupDynamicDto groupDynamicDto, UriComponentsBuilder uriComponentsBuilder) {

        GroupDynamic groupDynamic = groupDynamicService.save(groupDynamicDtoToGroupDynamic.convert(groupDynamicDto));

        groupDynamic.setIssue(issueService.get(groupDynamicDto.getIssue()));
        issueService.get(groupDynamicDto.getIssue()).addGroupDynamic(groupDynamic);

        UriComponents uriComponents = uriComponentsBuilder.path("/api/dynamics/" + groupDynamic.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    public ResponseEntity<GroupDynamicDto> deleteDynamic(@PathVariable Integer id) {

        groupDynamicService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<GroupDynamicDto> dynamicsDetails(@PathVariable Integer id) {

        GroupDynamic groupDynamic;

        groupDynamic = groupDynamicService.get(id);

        return new ResponseEntity<>(groupDynamicToGroupDynamicDto.convert(groupDynamic), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    public ResponseEntity<GroupDynamicDto> editDynamics(@RequestBody GroupDynamicDto groupDynamicDto, @PathVariable Integer id) {

        if (groupDynamicDto.getId() != null && !groupDynamicDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (groupDynamicService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        groupDynamicDto.setId(id);

        groupDynamicService.save(groupDynamicDtoToGroupDynamic.convert(groupDynamicDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
