package com.together.togetherpj.domain;

import com.together.togetherpj.constant.State;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "C_CONTENT", nullable = false)
    @Lob
    private String content;

    @Column(name="C_PER_NUM", nullable = false)
    private long perNum;

    @Column(name="C_CUR_NUM", nullable = false)
    @ColumnDefault("1")
    private long curNum;

    @Column(name="C_TRAVEL_START", nullable = false)
    private LocalDate startdate;

    @Column(name="C_TRAVEL_END", nullable = false)
    private LocalDate enddate;

    @Column(name="C_STATE", nullable = false, length = 20)

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name="C_VIEWCOUNT", nullable = false)
    @ColumnDefault("0")
    private long viewcount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="C_WRITER_ID")
    private Member recruitWriter;

   /* //작성자닉네임
    @Column(name="C_NICK", nullable = false)
    private String writerNick;*/

    //동행그룹과의관계
    @OneToMany(mappedBy = "recruit")
    private List<Applying>  applyingList = new ArrayList<>();
/*
    @OneToMany
    @JoinColumn(name = "CC_ID")
    private List<Comment> commentList = new ArrayList<>();

*/
    @PrePersist
    public void prePersist(){
        this.state= this.state == null ? State.RECRUITING : this.state;
        this.curNum = this.curNum == 0 ? 1 : this.curNum;
    }
}
