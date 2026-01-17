package com.example.demo.dto;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PickupRequestDto {

@NotNull
private Request request;
private Profile profile;

}