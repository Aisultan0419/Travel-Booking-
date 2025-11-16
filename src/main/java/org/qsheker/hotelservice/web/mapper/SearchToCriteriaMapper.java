package org.qsheker.hotelservice.web.mapper;

import org.mapstruct.Mapper;
import org.qsheker.hotelservice.web.dto.HotelSearchCriteria;
import org.qsheker.hotelservice.web.dto.HotelSearchRequest;

@Mapper(componentModel = "spring")
public interface SearchToCriteriaMapper {
    HotelSearchCriteria toCriteria(HotelSearchRequest request);
}
