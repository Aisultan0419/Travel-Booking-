package org.qsheker.hotelservice.context.pattern.factory;

import org.qsheker.hotelservice.context.pattern.strategy.PriceSearchStrategy;
import org.qsheker.hotelservice.context.pattern.strategy.RatingSearchStrategy;
import org.qsheker.hotelservice.context.pattern.strategy.SearchStrategy;
import org.qsheker.hotelservice.context.pattern.strategy.StrategyType;
import org.springframework.stereotype.Component;

@Component
public class StrategyFactory {

    public SearchStrategy of(StrategyType strategyType){
        return switch (strategyType){
            case PRICE -> new PriceSearchStrategy();
            case RATING -> new RatingSearchStrategy();
            default -> new PriceSearchStrategy();
        };
    }
}
