package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelHistoryRecord {
    private String awbNo;
    private String status;
    private String message;
    private LocalDateTime cancelledAt;
    private String source;
    private String requestedBy;
}