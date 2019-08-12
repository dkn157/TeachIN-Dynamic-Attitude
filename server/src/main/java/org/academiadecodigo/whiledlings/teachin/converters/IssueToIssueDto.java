package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.IssueDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueToIssueDto extends AbstractConverter<Issue, IssueDto> {

    @Override
    public IssueDto convert(Issue issue) {

        IssueDto issueDto = new IssueDto();

        issueDto.setId(issue.getId());
        issueDto.setTittle(issue.getTittle());

        return issueDto;
    }

}
