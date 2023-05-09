package com.together.togetherpj.domain.id;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ApplyingId implements Serializable  {

  private Long applierId; //신청자
  private Long recruitId; //게시글작성자
}
