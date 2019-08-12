package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.IssueDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Issue;
import org.academiadecodigo.whiledlings.teachin.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;

@Component
public class IssueDtoToIssue implements Converter<IssueDto, Issue> {

    private IssueService issueService;

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public Issue convert(IssueDto issueDto) {

        Issue issue = (issueDto.getId() != null ? issueService.get(issueDto.getId()) : new Issue());

        issue.setTittle(issueDto.getTittle());

        return issue;
    }
}
