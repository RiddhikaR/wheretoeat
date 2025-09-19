package com.wheretoeat.wheretoeat.dto;

import lombok.Data;

@Data
public class RequestDto {
    private double latitude;
    private double longitude;
    private double radius;
}
