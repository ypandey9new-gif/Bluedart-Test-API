package com.example.demo.dto;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.example.demo.dto.CancelStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkCancelResult {
    private String awbNo;
    private CancelStatus status;
    private String message;
}

