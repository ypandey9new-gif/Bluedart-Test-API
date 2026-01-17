package com.example.demo.dto;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class Request {
    private List<String> AWBNo;

    @NotBlank(message ="AreaCode must not be blank")
    private String AreaCode;
    private Boolean CISDDN;
    private String ContactPersonName;
    private String CustomerAddress1;
    private String CustomerAddress2;
    private String CustomerAddress3;
    private String CustomerCode;

    @Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
    private String CustomerPincode;
    private String CustomerTelephoneNumber;
    private String MobileTelNo;
    private String DoxNDox;
    private String EmailID;
    private boolean IsForcePickup;
    private boolean IsReversePickup; 
    private boolean isToPayShipper; 
    private String Remarks;
    private String RouteCode;


    private String CustomerName;
    private String ProductCode;
    private String ReferenceNo;
    private String ShipmentPickupTime;
    private String ShipmentPickupDate;
    private Double WeightofShipment;
    private Double VolumeWeight;
    private Integer NumberofPieces;
    private List<String> SubProducts;
    private String OfficeCloseTime;
    private String PackType;

}
