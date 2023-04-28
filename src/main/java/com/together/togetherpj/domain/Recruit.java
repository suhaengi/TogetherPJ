package com.together.togetherpj.domain;

import com.together.togetherpj.constant.State;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
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
    private long cur_num;

    @Temporal(TemporalType.DATE)
    @Column(name="C_TRAVEL_START", nullable = false)
    private Date startdate;

    @Temporal(TemporalType.DATE)
    @Column(name="C_TRAVEL_END", nullable = false)
    private Date enddate;

    @Column(name="C_STATE", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name="C_VIEWCOUNT", nullable = false)
    @ColumnDefault("0")
    private long viewcount;

    @Column(name="C_NICK", nullable = false)
    private String writer;
/*
    @OneToMany
    @JoinColumn(name = "CC_ID")
    private List<Comment> commentList = new ArrayList<>();

*/
    @PrePersist
    public void prePersist(){
        this.state= this.state == null ? State.RECRUITING : this.state;
        this.cur_num = this.cur_num == 0 ? 1 : this.cur_num;
    }
}
