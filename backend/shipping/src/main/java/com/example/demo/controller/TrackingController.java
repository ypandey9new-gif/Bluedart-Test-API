package com.example.demo.controller;

import com.example.demo.service.BluedartTrackingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final BluedartTrackingService trackingService;

    public TrackingController(BluedartTrackingService trackingService) {
        this.trackingService = trackingService;
    }

    private final XmlMapper xmlMapper = new XmlMapper();

    public JsonNode parseXmlToJson(String xmlResponse) throws Exception {
        return xmlMapper.readTree(xmlResponse);
    }

    @GetMapping("/{awb}")
public ResponseEntity<Object> track(@PathVariable String awb) {
    System.out.println("Controller executing..");
    try {
        String responseXml = trackingService.trackShipment(awb);
        JsonNode json = parseXmlToJson(responseXml);
        ObjectMapper objectMapper=new ObjectMapper();
        Object jsObject=objectMapper.convertValue(json,Object.class);
        return ResponseEntity.ok(jsObject);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

}
