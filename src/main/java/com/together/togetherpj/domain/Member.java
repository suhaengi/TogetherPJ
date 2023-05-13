package com.together.togetherpj.domain;

import com.together.togetherpj.constant.Gender;
import com.together.togetherpj.constant.Role;
import com.together.togetherpj.dto.MemberRegisterFormDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Member extends BaseEntity {

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

  @Column(name = "M_BIRTH", nullable = false)
  private LocalDate birth;
  @Column(name = "M_NICK", nullable = false, length = 20, unique = true)
  private String nickname;
  @Column(name = "M_SOCIAL")
  @ColumnDefault("0")
  private boolean social;
  @Column(name = "M_ROLE")
  @Enumerated(EnumType.STRING)
  private Role role;
  @Column(name = "M_LIKE")
  @ColumnDefault("0")
  private long like;
  @Column(name = "intro")
  private String intro;
  //프로필이미지첨부
  @Setter private String profileImgName;
  @Setter private String profileImgPath;

  //동행게시글과의 연관관계
  @OneToMany(mappedBy = "recruitWriter")
  private List<Recruit> recruitList = new ArrayList<>();

  //댓글과의 연관관계
  @OneToMany(mappedBy = "commentWriter")
  private List<Comment> commentList = new ArrayList<>();

  //동행그룹과의 연관관계
  @OneToMany(mappedBy = "applier")
  private List<Applying>  applyingList = new ArrayList<>();

  //후기
  @OneToMany(mappedBy = "reviewer")
  private List<Review> reviewList = new ArrayList<>();

  public static Member createMember(
      MemberRegisterFormDto memberRegisterFormDto,
      PasswordEncoder passwordEncoder
  ) {
    Member member = new Member();
    member.setName(memberRegisterFormDto.getName());
    member.setGender(memberRegisterFormDto.getGender());
    member.setPhone(memberRegisterFormDto.getPhone());
    member.setEmail(memberRegisterFormDto.getEmail());
    member.setBirth(LocalDate.parse(memberRegisterFormDto.getBirth()));
    member.setNickname(memberRegisterFormDto.getNickname());
    member.setSocial(false);
    member.setRole(Role.MEMBER);
    String password = passwordEncoder.encode(memberRegisterFormDto.getPassword());
    member.setPassword(password);
    return member;

  }
}

