package com.together.togetherpj.domain;

import com.together.togetherpj.domain.id.ApplyingId;
import com.together.togetherpj.domain.id.ReviewId;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
//동행그룹
public class Applying {

  @EmbeddedId
  private ApplyingId id;

  private boolean isOk;
  //동행신청자(member)와의 연관관계
  @MapsId("applierId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "C_APPLIER_ID")
  private Member applier;

  //동행게시글과의 연관관계
  @MapsId("recruitId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "C_ID")
  private Recruit recruit;

  @OneToMany(mappedBy = "applying")
  private List<Review> reviewList = new ArrayList<>();
}
