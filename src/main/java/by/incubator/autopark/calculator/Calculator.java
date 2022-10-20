package by.incubator.autopark.calculator;

import by.incubator.autopark.dto.VehicleDto;

import java.util.List;

public class Calculator {
    public static double calculateAverageTax(List<VehicleDto> dtoList) {
        double sum = 0.0d;
        double count = 0.0d;

        for (VehicleDto vehicleDto:
                dtoList) {
            sum += vehicleDto.getTax();
            count++;
        }

        return (sum / count);
    }

    public static double calculateAverageIncome(List<VehicleDto> dtoList) {
        double sum = 0.0d;
        double count = 0.0d;

        for (VehicleDto vehicleDto:
                dtoList) {
            sum += vehicleDto.getIncome();
            count++;
        }

        return (sum / count);
    }

    public static double calculateTotalProfit(List<VehicleDto> dtoList) {
        double sum = 0.0d;

        for (VehicleDto v:
                dtoList) {
            sum += v.getProfit();
        }

        return sum;
    }
}
