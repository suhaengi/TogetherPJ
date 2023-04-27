package com.together.togetherpj.domain;

import com.together.togetherpj.constant.State;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity



public class Recruit extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID")
    private Long id;

    @Column(name = "C_TITLE", nullable = false, length = 30)
    private String title;

    @Column(name = "C_CITY",nullable = false, length = 30)
    private String city;

    /*@Column(name = "C_IMG")
    @Lob
    private byte[] c_img;*/

    /*@Column(name = "C_TOUR", nullable = true, length = 10)
    private String tour;*/

    @Column(name = "C_CONTENT", nullable = false, length = 1000)
    private String content;

    @Column(name="C_PER_NUM", nullable = false)
    private long per_num;

    @Column(name="C_CUR_NUM", nullable = false)
    @ColumnDefault("1")
    private long cur_num;

    @Column(name="C_TRAVEL_START", nullable = false)
    private Date startdate;

    @Column(name="C_TRAVEL_END", nullable = false)
    private Date enddate;

    @Column(name="C_STATE", nullable = false, length = 20)

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name="C_VIEWCOUNT", nullable = false)
    @ColumnDefault("0")
    private long viewcount;

    @ManyToOne
    @JoinColumn(name = "M_ID")
    private Member member;

}
