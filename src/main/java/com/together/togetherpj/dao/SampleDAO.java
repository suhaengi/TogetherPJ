package com.together.togetherpj.dao;

import com.together.togetherpj.dto.SampleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleDAO {
    void insert (SampleDTO sampleDTO);
    List<SampleDTO> getList();
}
