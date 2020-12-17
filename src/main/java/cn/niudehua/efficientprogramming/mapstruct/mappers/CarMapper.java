package cn.niudehua.efficientprogramming.mapstruct.mappers;

import cn.niudehua.efficientprogramming.mapstruct.domain.Car;
import cn.niudehua.efficientprogramming.mapstruct.domain.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author deng
 * @datetime 2020/12/17 15:08
 */
@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDTO carToCarDto(Car car);
}
