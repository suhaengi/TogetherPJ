package com.together.togetherpj.domain;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Comment extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CC_ID")
  private Long id;

  @Column(name="CC_CONTENT", nullable = false, length=500)
  private String content;


  @ManyToOne
  @JoinColumn(name = "CC_WRITER_ID")
  private Member commentWriter;

  @ManyToOne
  @JoinColumn(name = "C_ID")
  private Recruit recruit;
}