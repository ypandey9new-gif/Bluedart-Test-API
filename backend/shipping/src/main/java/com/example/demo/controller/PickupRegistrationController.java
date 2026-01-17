package com.example.demo.controller;
import com.example.demo.dto.PickupRequestDto;
import com.example.demo.service.PickupService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pickup")
public class PickupRegistrationController {

    private final PickupService pickupService;

    public PickupRegistrationController(PickupService pickupService) {

        this.pickupService=pickupService;

    }

    @PostMapping("/register")
    public Mono<ResponseEntity<String>> registerPickup(@Valid  @RequestBody PickupRequestDto request){
       System.out.println(request);
        return pickupService.registerPickup(request)
        .map(ResponseEntity::ok)
        .onErrorResume(ex->Mono.just(ResponseEntity.badRequest().body(ex.getMessage())));
    }

}