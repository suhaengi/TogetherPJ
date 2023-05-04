package com.together.togetherpj.domain;

import com.together.togetherpj.domain.id.ReviewId;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

  @EmbeddedId
  private ReviewId reviewId;

  @MapsId("reviewerId")
  @ManyToOne
  @JoinColumn(name = "REVIEWER_ID")
  private Member reviewer;

  @MapsId("applyingId")
  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "reviewedMemberID", referencedColumnName = "C_APPLIER_ID"),
      @JoinColumn(name = "C_ID", referencedColumnName = "C_ID")
  })
  private Applying applying;

  @Column(name = "REVIEW_COMMENT", nullable = false, length = 100)
  private String comment;

  /*@Column(name ="REVIEW_LIKE")
  @ColumnDefault("0")
  private boolean like;*/
}
