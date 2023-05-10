package com.together.togetherpj.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.together.togetherpj.domain.Recruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
public class RecruitRepositoryImpl implements RecruitRepositoryCustom{
  private final EntityManager em;
  private final JPAQueryFactory queryFactory;

  public RecruitRepositoryImpl(EntityManager em){
    this.em = em;
    this.queryFactory = new JPAQueryFactory(em);
  }

/*  @Override
  public List<Recruit> findLatest12Recruits() {
    return null;
  }

  @Override
  public List<Recruit> findTop12ByOrderByModDateDesc() {
    TypedQuery<Recruit> query = em.createQuery("SELECT r FROM Recruit r ORDER BY r.modDate DESC", Recruit.class);
    query.setMaxResults(12);
    return query.getResultList();
  }*/

}
