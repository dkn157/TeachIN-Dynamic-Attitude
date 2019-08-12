package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.GroupDynamicDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.GroupDynamic;
import org.academiadecodigo.whiledlings.teachin.services.GroupDynamicService;
import org.academiadecodigo.whiledlings.teachin.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupDynamicDtoToGroupDynamic implements Converter<GroupDynamicDto, GroupDynamic> {

    private GroupDynamicService groupDynamicService;
    private IssueService issueService;

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @Autowired
    public void setGroupDynamicService(GroupDynamicService groupDynamicService) {
        this.groupDynamicService = groupDynamicService;
    }

    @Override
    public GroupDynamic convert(GroupDynamicDto groupDynamicDto) {

        GroupDynamic groupDynamic = (groupDynamicDto.getId() != null ? groupDynamicService.get(groupDynamicDto.getId()) : new GroupDynamic());

        groupDynamic.setDescription(groupDynamicDto.getDescription());

        groupDynamic.setMinAge(groupDynamicDto.getMinAge());
        groupDynamic.setMaxAge(groupDynamicDto.getMaxAge());
        groupDynamic.setName(groupDynamicDto.getName());

        groupDynamic.setIssue(issueService.get(groupDynamicDto.getIssue()));

        return groupDynamic;
    }
}
