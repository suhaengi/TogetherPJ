package com.together.togetherpj.domain.id;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@EqualsAndHashCode
public class ApplyingId implements Serializable  {
  private Long applierId;
  private Long recruitId;
}
