package org.academiadecodigo.whiledlings.teachin.controller;

import org.academiadecodigo.whiledlings.teachin.command.GroupDynamicDto;
import org.academiadecodigo.whiledlings.teachin.converters.GroupDynamicToGroupDynamicDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.GroupDynamic;
import org.academiadecodigo.whiledlings.teachin.services.GroupDynamicService;
import org.academiadecodigo.whiledlings.teachin.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/search/")
public class searchController {

    private IssueService issueService;
    private GroupDynamicToGroupDynamicDto dynamicToDto;


    private GroupDynamicDto groupDynamicDto;
    private GroupDynamicService groupDynamicService;
    private GroupDynamicToGroupDynamicDto groupDynamicToGroupDynamicDto;

    @Autowired
    public void setGroupDynamicToGroupDynamicDto(GroupDynamicToGroupDynamicDto groupDynamicToGroupDynamicDto) {
        this.groupDynamicToGroupDynamicDto = groupDynamicToGroupDynamicDto;
    }

    @Autowired
    public void setGroupDynamicService(GroupDynamicService groupDynamicService) {
        this.groupDynamicService = groupDynamicService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dynamic")
    public ResponseEntity<GroupDynamicDto> showResult(@RequestParam("id") int id) {

        GroupDynamicDto groupDynamicDto = groupDynamicToGroupDynamicDto.convert(groupDynamicService.get(id));

        return new ResponseEntity<>(groupDynamicDto, HttpStatus.OK);
    }


    @Autowired
    public void setDynamicToDto(GroupDynamicToGroupDynamicDto dynamicToDto) {
        this.dynamicToDto = dynamicToDto;
    }

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<Set<GroupDynamicDto>> search(@PathVariable Integer id) {

        Set<GroupDynamicDto> dynamicDtos = new HashSet<>();

        Set<GroupDynamic> dynamics = issueService.get(id).getGroupDynamics();

        for (GroupDynamic groupDynamic : dynamics){
            dynamicDtos.add(dynamicToDto.convert(groupDynamic));
        }

        return new ResponseEntity<>(dynamicDtos, HttpStatus.OK);

    }

}
