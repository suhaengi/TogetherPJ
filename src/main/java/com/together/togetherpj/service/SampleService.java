package com.together.togetherpj.service;

import com.together.togetherpj.dao.SampleDAO;
import com.together.togetherpj.dto.SampleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {

    @Autowired
    private SampleDAO dao;

    public void register(SampleDTO sampleDTO) {
        dao.insert(sampleDTO);
    }


}
