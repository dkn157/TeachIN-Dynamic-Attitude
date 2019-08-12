package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.GroupDynamicDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.GroupDynamic;
import org.springframework.stereotype.Component;

@Component
public class GroupDynamicToGroupDynamicDto extends AbstractConverter<GroupDynamic, GroupDynamicDto> {

    @Override
    public GroupDynamicDto convert(GroupDynamic groupDynamic) {

        GroupDynamicDto groupDynamicDto = new GroupDynamicDto();

        groupDynamicDto.setId(groupDynamic.getId());
        groupDynamicDto.setDescription(groupDynamic.getDescription());
        groupDynamicDto.setMinAge(groupDynamic.getMinAge());
        groupDynamicDto.setMaxAge(groupDynamic.getMaxAge());
        groupDynamicDto.setName(groupDynamic.getName());

        return groupDynamicDto;
    }
}
