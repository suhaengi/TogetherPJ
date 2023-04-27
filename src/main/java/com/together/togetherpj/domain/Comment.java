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

  @MapsId("commentWriterId")
  @ManyToOne
  @JoinColumn(name = "CC_WRITER_ID")
  private Member commentWriter;

  @ManyToOne
  @JoinColumn(name = "C_ID")
  private Recruit recruit;

}