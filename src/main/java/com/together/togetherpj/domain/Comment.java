package com.together.togetherpj.domain;

import com.together.togetherpj.domain.id.CommentId;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity{
  @EmbeddedId
  private CommentId id;

  @Column(name="CC_CONTENT", nullable = false, length=500)
  private String content;

  @MapsId("memberId")
  @ManyToOne
  @JoinColumn(name = "M_ID")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "C_ID")
  private Recruit recruit;

}