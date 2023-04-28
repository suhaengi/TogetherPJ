package com.together.togetherpj.domain.id;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ReviewId implements Serializable {
  private Long reviewerId;
  private ApplyingId applyingId;
}
