package com.together.togetherpj.domain;

import com.together.togetherpj.constant.Gender;
import com.together.togetherpj.constant.Role;
import com.together.togetherpj.dto.MemberFormDto;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "M_ID")
  private Long id;
  @Column(name = "M_PW", nullable = false, length = 100)
  private String password;
  @Column(name = "M_NAME", nullable = false, length = 10)
  private String Name;
  @Column(name = "M_GENDER", nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @Column(name = "M_PHONE", nullable = false, length = 11)
  private String phone;
  @Column(name = "M_EMAIL", nullable = false, length = 30, unique = true)
  private String email;
//  @Column(name = "M_BIRTH", nullable = false)
//  private Date birth;
  @Column(name = "M_NICK", nullable = false, length = 20, unique = true)
  private String nickname;
  @Column(name = "M_JOINDATE")
  private LocalDate joinDate;
  @Column(name = "M_SOCIAL")
  @ColumnDefault("0")
  private boolean social;
  @Column(name = "M_ROLE")
  @Enumerated(EnumType.STRING)
  private Role role;
  @Column(name = "M_LIKE")
  @ColumnDefault("0")
  private long like;

  @Column(name="introduce", length=500)
  private String intro;

  @OneToMany(mappedBy = "recruitWriter")
  private List<Recruit> recruitList = new ArrayList<>();

  @OneToMany(mappedBy = "commentWriter")
  private List<Comment> commentList = new ArrayList<>();

  @OneToMany(mappedBy = "applier")
  private List<Applying>  applyingList = new ArrayList<>();

  @OneToMany(mappedBy = "reviewer")
  private List<Review> reviewList = new ArrayList<>();

  /*@OneToOne(mappedBy = "member",
          cascade = {CascadeType.ALL},
          fetch = FetchType.LAZY,
          orphanRemoval = true)
  @Builder.Default
  private ProfileImg profileImg;

  public void addImg(String uuid,String fimeName){
    ProfileImg profileImg = ProfileImg.builder()
            .uuid(uuid)
            .fileName(fimeName)
            .member(this)
            .build();
  }*/

  public static Member createMember(
      MemberFormDto memberFormDto,
      PasswordEncoder passwordEncoder
  ) {
    Member member = new Member();
    member.setName(memberFormDto.getName());
    member.setGender(memberFormDto.getGender());
    member.setPhone(memberFormDto.getPhone());
    member.setEmail(memberFormDto.getEmail());
//    member.setBirth(memberFormDto.getBirth());
    member.setNickname(memberFormDto.getNickname());
    member.setJoinDate(LocalDate.now());
    member.setSocial(false);
    member.setRole(Role.MEMBER);
    String password = passwordEncoder.encode(memberFormDto.getPassword());
    member.setPassword(password);
    return member;

  }
/*
  public void change(String nickname, String intro,String phone, String password,PasswordEncoder passwordEncoder){
    this.nickname=nickname;
    this.intro=intro;
    this.phone=phone;
    String secret = passwordEncoder.encode(password);
    this.password=secret;
  }*/

}

//  CREATE TABLE `MEMBER` (
//    `M_ID`	VARCHAR(30)	NOT NULL,
//    `M_PW`	VARCHAR(20)	NOT NULL,
//    `M_NAME`	VARCHAR(10)	NOT NULL,
//    `M_GENDER`	VARCHAR(5)	NOT NULL,
//    `M_PHONE`	VARCHAR(11)	NOT NULL,
//    `M_EMAIL`	VARCHAR(30)	NOT NULL,
//    `M_BIRTHDAY`	Date	NOT NULL,
//    `M_NICK`	VARCHAR(20)	NOT NULL,
//    `M_IMG`	BLOB	NULL,
//    `M_JOINDATE`	DATETIME	NOT NULL,
//    `M_SOCIAL`	BOOLEAN	NOT NULL,
//    `Role`	VARCHAR(20)	NOT NULL,
//    `M_LIKE`	NUMBER	NOT NULL	DEFAULT 0
//    );