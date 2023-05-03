package com.together.togetherpj.domain.id;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@EqualsAndHashCode
public class ApplyingId implements Serializable  {
  private Long applierId; //신청자
  private Long recruitId; //게시글작성자
}
