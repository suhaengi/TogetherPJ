package com.together.togetherpj.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class RecruitRepositoryImpl implements RecruitRepositoryCustom{
  private final EntityManager em;
  private final JPAQueryFactory queryFactory;

  public RecruitRepositoryImpl(EntityManager em){
    this.em = em;
    this.queryFactory = new JPAQueryFactory(em);
  }



}
