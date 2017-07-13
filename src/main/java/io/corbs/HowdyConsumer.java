package io.corbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableBinding(HowdyConsumer.SinkChannels.class)
public class HowdyConsumer {

    interface SinkChannels {
        @Input
        SubscribableChannel input();
    }

    @StreamListener("input")
    void receive(String message) {
        System.out.println("Consumer received: " + message);
    }

    @GetMapping("ip")
    public Map<String, String> getIp() {
        Map<String, String> response = new HashMap<>();
        response.put("ip", AppUtils.getIp().toString());
        return response;
    }

    @GetMapping("datetime")
    public Map<String, String> getDateTime() {
        Map<String, String> response = new HashMap<>();
        response.put("datetime", AppUtils.now());
        return response;
    }

    @GetMapping("vcap/application")
    public Map<String, Object> getVcapApplication() {
        return CfUtils.getVcapApplication();
    }

    @GetMapping("vcap/services")
    public Map<String, Object> getVcapServices() {
        return CfUtils.getVcapServices();
    }

    public static void main(String[] args) {
        SpringApplication.run(HowdyConsumer.class, args);
    }
}