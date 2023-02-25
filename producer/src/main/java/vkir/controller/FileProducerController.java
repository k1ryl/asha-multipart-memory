package vkir.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vkir.model.Data;
import vkir.repository.DataJpaRepository;

@RestController
public class FileProducerController {

    private final RestTemplate restTemplate;
    private final DataJpaRepository dataJpaRepository;

    public FileProducerController(DataJpaRepository dataJpaRepository) {
        this.dataJpaRepository = dataJpaRepository;
        restTemplate = new RestTemplate();
    }

    @PostMapping(value = "/uploadFile")
    public ResponseEntity<String> uploadFile() {
        // retrieve data from database
        var dataList = dataJpaRepository.findAll();

        // create a string from the data
        StringBuilder sb = new StringBuilder();
        for (Data data : dataList) {
            sb.append(data.toString()).append("\n");
        }
        var dataString = sb.toString();

        // prepare request
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(dataString.getBytes()) {
            @Override
            public String getFilename() {
                return "file";
            }
        });

        var request = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity("http://localhost:8080/receiveFile", request, String.class);
    }
}
