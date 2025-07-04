package com.vuekafkar.springboot.kafkaprodcons.service;

import com.vuekafkar.springboot.kafkaprodcons.SimpleModel;
import com.vuekafkar.springboot.kafkaprodcons.repo.SimpleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleModelImpl implements SimpleModelService{

    //@Autowired
    public SimpleRepo simpleRepo;

    public SimpleModelImpl(SimpleRepo simpleRepo) {
        this.simpleRepo = simpleRepo;
    }

    @Override
    public SimpleModel save(SimpleModel simpleModel) {
        return simpleRepo.save(simpleModel);
    }

    @Override
    public List<SimpleModel> fetchSimpleModelList() {
        return (List<SimpleModel>)simpleRepo.findAll();
    }

}
