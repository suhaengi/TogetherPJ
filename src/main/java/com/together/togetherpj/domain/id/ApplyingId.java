package com.together.togetherpj.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class ApplyingId implements Serializable  {
  private Long applierId; //신청자아이디
  private Long recruitId; //동행게시글번호
}
