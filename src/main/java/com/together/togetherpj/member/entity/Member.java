package com.together.togetherpj.member.entity;

import com.together.togetherpj.member.constant.Gender;
import com.together.togetherpj.member.constant.Role;
import com.together.togetherpj.member.dto.MemberFormDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
  @Column(name = "M_IMG")
  @Lob
  private byte[] img;
  @Column(name = "M_JOINDATE")
  private LocalDateTime joinDate;
  @Column(name = "M_SOCIAL")
  @ColumnDefault("0")
  private boolean social;
  @Column(name = "M_ROLE")
  @Enumerated(EnumType.STRING)
  private Role role;
  @Column(name = "M_LIKE")
  @ColumnDefault("0")
  private long like;

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
    member.setJoinDate(LocalDateTime.now());
    member.setSocial(false);
    member.setRole(Role.MEMBER);
    String password = passwordEncoder.encode(memberFormDto.getPassword());
    member.setPassword(password);
    return member;

  }
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