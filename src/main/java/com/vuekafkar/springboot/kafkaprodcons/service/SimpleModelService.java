package com.vuekafkar.springboot.kafkaprodcons.service;

import com.vuekafkar.springboot.kafkaprodcons.SimpleModel;

import java.util.List;

public interface SimpleModelService {

    //create
    SimpleModel save(SimpleModel simpleModel);

    //get
    List<SimpleModel> fetchSimpleModelList();

    /*//update
    SimpleModel updateSimpleModel(SimpleModel simpleModel);

    //remove
    void deleteSimpleModel(SimpleModel simpleModel);
    https://www.geeksforgeeks.org/spring-boot-with-h2-database/
    https://freedium.cfd/blog.stackademic.com/component-testing-for-microservices-from-database-interactions-to-kafka-messaging-4e616061097e
    */

}
