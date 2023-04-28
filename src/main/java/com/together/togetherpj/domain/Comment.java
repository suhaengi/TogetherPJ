package com.together.togetherpj.domain;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CC_NUM")
    private Long id;

    @Column(name="CC_CONTENT", nullable = false, length=500)
    private String content;

    @Id
    @ManyToOne
    @JoinColumn(name = "M_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "C_ID")
    private Recruit recruit;

}


