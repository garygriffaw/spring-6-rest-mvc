package com.garygriffaw.spring6restmvc.mappers;

import com.garygriffaw.spring6restmvc.entities.Beer;
import com.garygriffaw.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
