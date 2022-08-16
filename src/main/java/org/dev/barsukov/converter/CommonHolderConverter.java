package org.dev.barsukov.converter;

import org.dev.barsukov.entity.CommonHolderEntity;
import org.dev.barsukov.service.dto.CommonHolderDto;

import java.util.List;


public interface CommonHolderConverter {

    CommonHolderDto toDto(CommonHolderEntity entity);

    CommonHolderEntity fromDto (CommonHolderDto dto);

    List<CommonHolderDto> toDto(List<CommonHolderEntity> trades);
}
