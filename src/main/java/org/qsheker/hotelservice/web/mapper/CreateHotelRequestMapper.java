package org.qsheker.hotelservice.web.mapper;

import org.mapstruct.Mapper;
import org.qsheker.hotelservice.context.db.models.Hotel;
import org.qsheker.hotelservice.web.dto.CreateHotelRequest;

@Mapper(componentModel = "spring")
public interface CreateHotelRequestMapper {
    Hotel toEntity(CreateHotelRequest createHotelRequest);
}
