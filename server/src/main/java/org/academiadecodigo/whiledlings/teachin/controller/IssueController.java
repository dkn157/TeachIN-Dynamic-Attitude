package org.academiadecodigo.whiledlings.teachin.controller;

import org.academiadecodigo.whiledlings.teachin.command.IssueDto;
import org.academiadecodigo.whiledlings.teachin.converters.IssueDtoToIssue;
import org.academiadecodigo.whiledlings.teachin.converters.IssueToIssueDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Issue;
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
@RequestMapping("/api/issues")
public class IssueController {

    private IssueService issueService;

    private IssueToIssueDto issueToIssueDto;
    private IssueDtoToIssue issueDtoToIssue;

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @Autowired
    public void setIssueToIssueDto(IssueToIssueDto issueToIssueDto) {
        this.issueToIssueDto = issueToIssueDto;
    }

    @Autowired
    public void setIssueDtoToIssue(IssueDtoToIssue issueDtoToIssue) {
        this.issueDtoToIssue = issueDtoToIssue;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<IssueDto>> listIssues() {

        List<IssueDto> issueDtos = new ArrayList<>();

        for (Issue issue : issueService.list()) {
            issueDtos.add(issueToIssueDto.convert(issue));
        }
        return new ResponseEntity<>(issueDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<HttpHeaders> addIssue(@RequestBody IssueDto issueDto, UriComponentsBuilder uriComponentsBuilder) {

        Issue savedIssue = issueService.save(issueDtoToIssue.convert(issueDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/issues/" + savedIssue.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    public ResponseEntity<IssueDto> deleteIssue(@PathVariable Integer id) {

        issueService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<IssueDto> issueDetails(@PathVariable Integer id) {

        Issue issue;

        issue = issueService.get(id);

        return new ResponseEntity<>(issueToIssueDto.convert(issue), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    public ResponseEntity<IssueDto> editIssue(@RequestBody IssueDto issueDto, @PathVariable Integer id) {

        if (issueDto.getId() != null && !issueDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (issueService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        issueDto.setId(id);

        issueService.save(issueDtoToIssue.convert(issueDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
