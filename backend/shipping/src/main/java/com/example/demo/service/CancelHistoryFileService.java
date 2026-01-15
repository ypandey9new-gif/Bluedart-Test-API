package com.example.demo.service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.dto.CancelHistoryRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.SerializationFeature;




@Service
public class CancelHistoryFileService {

    private static final String DATA_DIR="data";
    private static final String FILE_NAME="cancellation-history.json";
    private final ObjectMapper objectMapper;
    private final Path filePath;

    public CancelHistoryFileService() throws IOException {
        this.objectMapper=new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Path baseDir=Paths.get(DATA_DIR);
        if(!Files.exists(baseDir)) {
            Files.createDirectories(baseDir);
        }

        this.filePath=baseDir.resolve(FILE_NAME);

        if(!Files.exists(filePath)) {
            Files.write(filePath, "[]".getBytes(StandardCharsets.UTF_8));
        }
    }

    public synchronized void save(CancelHistoryRecord record) {

        try {

            List<CancelHistoryRecord> records= objectMapper.readValue(filePath.toFile(),
            new TypeReference<List<CancelHistoryRecord>>() {});  //readAll();
            records.add(record);
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(filePath.toFile(), records);    
        } catch(IOException e) {
            throw new RuntimeException("Failed to save cancellation history", e);
        }
    }

    // public List<CancelHistoryRecord> readAll() throws IOException {
    //     return objectMapper.readValue(
    //         filePath.toFile(),
    //         new TypeReference<List<CancelHistoryRecord>>(){}
    //     );
    // }

}