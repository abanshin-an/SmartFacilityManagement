package ru.a2n.sfm.facility.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.a2n.sfm.facility.dto.FacilityCreateDto;
import ru.a2n.sfm.facility.dto.FacilityFullDto;
import ru.a2n.sfm.facility.dto.FacilityListItemDto;
import ru.a2n.sfm.facility.model.Facility;

@Mapper(componentModel = "spring")
@SuppressWarnings("java:S6813")
public abstract class FacilityMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Facility toFacility(FacilityCreateDto facilityCreateDto);

    public abstract FacilityListItemDto toFacilityListItemDto(Facility facility);

    public abstract FacilityFullDto toFacilityFullDto(Facility facility);
}
