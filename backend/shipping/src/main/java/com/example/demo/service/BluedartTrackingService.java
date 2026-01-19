package com.example.demo.service;

import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BluedartTrackingService {

@Value("${track-url}")
private String baseUrl;

@Value("${loginid}")
private String loginId;

@Value("${lickey}")
private String licenseKey;

@Value("${tracking-path}")
private String trackingPath;

private final BluedartTrackingAuthService authService;

private final RestTemplate restTemplate=new RestTemplate();

public BluedartTrackingService(BluedartTrackingAuthService authService) {
    this.authService=authService;
}

public String trackShipment(String awbNo) {

    // for temp bedugging
    System.out.println("Executing track shipment service..");
    System.out.println("Login ID" +loginId);
    System.out.println("Base URL :"+baseUrl);
    System.out.println("Lic :"+licenseKey);

    String JWTToken=authService.getJwtToken();

    System.out.println("JWTToken : "+JWTToken);

    UriComponentsBuilder uriBuilder=UriComponentsBuilder
    .fromUriString(baseUrl)
    .queryParam("handler", "tnt")
        .queryParam("action", "custawbquery")
        .queryParam("loginid", loginId)
        .queryParam("awb", "awb")
        .queryParam("numbers", awbNo)
        .queryParam("format", "xml")
        .queryParam("lickey", licenseKey)
        .queryParam("verno", "1")
        .queryParam("scan", "1");

HttpHeaders headers=new HttpHeaders();
headers.set("JWTToken",JWTToken);
headers.setAccept(List.of(MediaType.APPLICATION_XML));
HttpEntity<Void> entity=new HttpEntity<>(headers);

ResponseEntity<String> response=restTemplate.exchange(
    uriBuilder.toUriString(),
    HttpMethod.GET,
    entity,
    String.class
);

return response.getBody();
}

}