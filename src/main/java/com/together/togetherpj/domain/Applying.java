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
public class Applying {

  @EmbeddedId
  private ApplyingId id;

  private boolean isOk;

  @MapsId("applierId")
  @ManyToOne
  @JoinColumn(name = "C_APPLIER_ID")
  private Member applier;

  @MapsId("recruitId")
  @ManyToOne
  @JoinColumn(name = "C_ID")
  private Recruit recruit;

  @OneToMany(mappedBy = "applying")
  private List<Review> reviewList = new ArrayList<>();
}
