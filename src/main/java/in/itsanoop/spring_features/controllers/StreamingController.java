package in.itsanoop.spring_features.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/stream")
public class StreamingController {

    @GetMapping(path = "/numbers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> streamNumbers(){

        StreamingResponseBody responseBody =  outputStream -> {
            for(int i = 0; i < 100; i++) {
                String data = "data: " + i + "\n\n";
                outputStream.write(data.getBytes());
                outputStream.flush();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseBody);
    }
}
