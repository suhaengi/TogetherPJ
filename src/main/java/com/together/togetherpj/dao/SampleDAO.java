package com.together.togetherpj.dao;

import com.together.togetherpj.dto.SampleDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleDAO {
    void insert (SampleDTO sampleDTO);

}
