package com.vuekafkar.springboot.kafkaprodcons;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void post(@RequestBody SimpleModel simpleModel){
        kafkaTemplate.send("myTopic", jsonConverter.toJson(simpleModel));
    }

    @KafkaListener(topics = "myTopic")
    public void getFromKafka(String simpleModel){
        System.out.println(simpleModel);
        SimpleModel simpleModel1 = jsonConverter.fromJson(simpleModel, SimpleModel.class);
        System.out.println(simpleModel1.toString());
    }

    /**
     * Consumer
     */

    @PostMapping("/v2")
    public void post(@RequestBody MoreSimpleModel moreSimpleModel){
        kafkaTemplate.send("myTopic2", jsonConverter.toJson(moreSimpleModel));
    }

    @KafkaListener(topics = "myTopic2")

    public void getFromKafka2(String moreSimpleModel){
        System.out.println(moreSimpleModel);
        MoreSimpleModel simpleModel1 = jsonConverter.fromJson(moreSimpleModel, MoreSimpleModel.class);
        System.out.println(simpleModel1.toString());
    }
}
