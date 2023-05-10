package com.together.togetherpj.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable//필드값 묶기
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ApplyingId implements Serializable  {
  private Long applierId; //신청자
  private Long recruitId; //게시글id
}
