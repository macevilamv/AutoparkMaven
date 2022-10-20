package by.incubator.autopark.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class RentDto {
    private Long id;
    private Long vehicleId;
    private Date date;
    private Double cost;
}
