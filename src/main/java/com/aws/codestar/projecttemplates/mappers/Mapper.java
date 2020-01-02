package com.aws.codestar.projecttemplates.mappers;

public interface Mapper<Dto, EntityInterface> {

    Dto toDTO(EntityInterface entity);

    EntityInterface toEntity(Dto dto);
}
