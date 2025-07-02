package com.vuekafkar.springboot.kafkaprodcons;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/kafka")
public class KafkaSimpleController {
    private KafkaTemplate<String, String> kafkaTemplate;
    private Gson jsonConverter;

    @Autowired
    public KafkaSimpleController(KafkaTemplate<String, String> kafkaTemplate, Gson jsonConverter){
        this.kafkaTemplate = kafkaTemplate;
        this.jsonConverter = jsonConverter;
    }

    /**
     * Producer
     */

    @PostMapping
    public ResponseEntity<String> post(@RequestBody SimpleModel simpleModel) throws InterruptedException, ExecutionException {
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send("myTopic", jsonConverter.toJson(simpleModel));
        return new ResponseEntity<>(result.get().getProducerRecord().value(), HttpStatus.OK);
    }

    @KafkaListener(topics = "myTopic")
    public void getFromKafka(String simpleModel){
        System.out.println("Kafka event consumed is: " + simpleModel);
        SimpleModel simpleModel1 = jsonConverter.fromJson(simpleModel, SimpleModel.class);
        System.out.println("Model converted value: " + simpleModel1.toString());
    }

    /**
     * Consumer
     */

    @PostMapping("/v2")
    public ResponseEntity<String> post(@RequestBody MoreSimpleModel moreSimpleModel) throws InterruptedException, ExecutionException{
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send("myTopic2", jsonConverter.toJson(moreSimpleModel));
        return new ResponseEntity<>(result.get().getProducerRecord().value(), HttpStatus.OK);
    }

    @KafkaListener(topics = "myTopic2")
    public void getFromKafka2(String moreSimpleModel) {
        System.out.println("Kafka event consumed is: " + moreSimpleModel);
        MoreSimpleModel simpleModel1 = jsonConverter.fromJson(moreSimpleModel, MoreSimpleModel.class);
        System.out.println("Model converted value: " +  simpleModel1.toString());
    }
}
