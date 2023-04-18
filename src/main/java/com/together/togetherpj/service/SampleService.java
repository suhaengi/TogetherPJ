package com.together.togetherpj.service;

import com.together.togetherpj.dao.SampleDAO;
import com.together.togetherpj.dto.SampleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Autowired
    private SampleDAO sampleDAO2;

    public void register(SampleDTO sampleDTO) {
        sampleDAO2.insert(sampleDTO);
    }


}
