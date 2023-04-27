package com.together.togetherpj.domain.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
@Embeddable
public class CommentId implements Serializable {

  private Long memberId;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CC_ID")
  private Long id;
}
