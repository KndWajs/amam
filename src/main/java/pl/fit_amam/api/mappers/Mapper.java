package pl.fit_amam.api.mappers;

public interface Mapper<Dto, EntityInterface> {

    Dto toDTO(EntityInterface entity);

    EntityInterface toEntity(Dto dto);
}
