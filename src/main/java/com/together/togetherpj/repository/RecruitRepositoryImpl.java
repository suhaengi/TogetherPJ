package com.together.togetherpj.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.together.togetherpj.constant.State;
import com.together.togetherpj.domain.QRecruit;
import com.together.togetherpj.dto.LatestRecruitDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.together.togetherpj.domain.QRecruit.*;

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

  public Page<LatestRecruitDto> getLatestRecruitDto(Pageable pageable){
    List<LatestRecruitDto> content = queryFactory.select(Projections.constructor(LatestRecruitDto.class,
                    recruit.id, recruit.title, recruit.recruitWriter.nickname,
                    recruit.startdate, recruit.enddate, recruit.city))
            .from(recruit)
            .where(recruit.state.eq(State.RECRUITING))
            .orderBy(recruit.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();


    return new PageImpl<>(content, pageable, content.size());
  }
}
