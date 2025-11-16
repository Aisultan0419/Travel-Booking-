package org.qsheker.hotelservice.context.pattern.strategy;

import org.qsheker.hotelservice.context.db.models.Hotel;
import org.qsheker.hotelservice.web.dto.HotelSearchCriteria;

import java.util.List;

public interface SearchStrategy {
    List<Hotel> search(List<Hotel> hotels, HotelSearchCriteria criteria);
}
