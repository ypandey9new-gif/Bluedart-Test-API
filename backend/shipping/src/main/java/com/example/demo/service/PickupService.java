package com.example.demo.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.PickupRequestDto;

import reactor.core.publisher.Mono;


@Service
public class PickupService {

    @Value("${bluedart.base-url}")
    private String baseUrl;
    private String endPoint;
    private String jwtToken;
    private final WebClient webClient;
    private final BluedartAuthService authService;


    public PickupService(WebClient webClient,BluedartAuthService authService) {
        this.webClient=webClient;
        this.authService=authService;
    }

    

    public Mono<String> registerPickup(PickupRequestDto dto) {
        endPoint="/in/transportation/pickup/v1/RegisterPickup";
        jwtToken=authService.getJwtToken();
        return webClient.post()
        .uri(baseUrl+endPoint)
        .header("JWTToken",jwtToken)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError,
        response->response.bodyToMono(String.class)
        .map(RuntimeException::new)
        )
        .bodyToMono(String.class);
    }

}