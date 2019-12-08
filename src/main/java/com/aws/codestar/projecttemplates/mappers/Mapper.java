package com.aws.codestar.projecttemplates.mappers;

public interface Mapper<Dto, EntityInterface> {

    Dto toDto(EntityInterface entity);

    EntityInterface toEntity(Dto dto);
}
