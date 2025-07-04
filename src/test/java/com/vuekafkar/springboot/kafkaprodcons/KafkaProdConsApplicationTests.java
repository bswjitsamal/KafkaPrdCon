package com.vuekafkar.springboot.kafkaprodcons;

import com.vuekafkar.springboot.kafkaprodcons.repo.SimpleRepo;
import com.vuekafkar.springboot.kafkaprodcons.service.SimpleModelService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
//import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"user-created-topic"})
class KafkaProdConsApplicationTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private SimpleModelService simpleModelService;

    @Autowired
    private SimpleRepo simpleRepo;

    @Autowired
    private Consumer<String, String> consumer;

    /**
     *


    @Test
    void testReceive() throws Exception {
        kafkaTemplate.send("testTopic", "Hello Kafka");
        ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, "testTopic");
        assertEquals("Hello Kafka", record.value());
    }
     * @throws Exception
     */

    @Test
    void contextLoads() {
    }

    @Test
    public void testKafkaToDBFlow() throws InterruptedException {
       SimpleModel sm = new SimpleModel(1,"Hello","Padu");
       simpleModelService.save(sm);

        assertNotNull(simpleModelService.fetchSimpleModelList());
        assertEquals("Hello", sm.getField1());
        assertEquals("Padu", sm.getField2());
    }


    @Test
    public void testKafkaFlow() throws InterruptedException {
        String userJson = "{\"Field1\": \"field1\", \"Field2\": \"field2\"}";

        // Send message to Kafka topic
        kafkaTemplate.send("user-created-topic", userJson);

        // Give the consumer time to process the message (simulating real-world delay)
        Thread.sleep(1000);

            kafkaTemplate.send("testTopic", "Hello Kafka");
            ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, "testTopic");
            assertEquals("Hello Kafka", record.value());
    }

}
