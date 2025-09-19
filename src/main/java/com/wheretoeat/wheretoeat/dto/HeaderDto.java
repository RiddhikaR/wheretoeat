package com.wheretoeat.wheretoeat.dto;

import java.util.List;
import lombok.Data;

@Data
public class HeaderDto {
    private List<String> includedTypes;
    private int maxResultCount;
    private LocationRestriction locationRestriction;

    @Data
    public static class LocationRestriction {
        private Circle circle;
    }

    @Data
    public static class Circle {
        private Center center;
        private double radius;
    }

    @Data
    public static class Center {
        private double latitude;
        private double longitude;
    }
}
