package cn.niudehua.efficientprogramming.mapstruct.mappers;

import cn.niudehua.efficientprogramming.mapstruct.domain.Car;
import cn.niudehua.efficientprogramming.mapstruct.domain.CarDTO;
import cn.niudehua.efficientprogramming.mapstruct.domain.CarType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author deng
 * @datetime 2020/12/17 15:09
 */
class CarMapperTest {

    @Test
    void shouldMapCarToDto() {
        //given
        Car car = new Car("Morris", 5, CarType.SEDAN);

        //when
        CarDTO carDTO = CarMapper.INSTANCE.carToCarDto(car);

        //then
        assertThat(carDTO).isNotNull();
        assertThat(carDTO.getMake()).isEqualTo("Morris");
        assertThat(carDTO.getSeatCount()).isEqualTo(5);
        assertThat(carDTO.getType()).isEqualTo("SEDAN");
    }
}