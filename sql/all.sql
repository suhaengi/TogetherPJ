-- DROP
DROP TABLE review CASCADE;
DROP TABLE applying CASCADE;
DROP TABLE comment  CASCADE;
DROP TABLE recruit CASCADE;
DROP TABLE `member` CASCADE;

-- CREATE

create table member (
                        m_id             bigint auto_increment
                            primary key,
                        moddate          datetime(6)         not null,
                        regdate          datetime(6)         not null,
                        m_name           varchar(10)         not null,
                        m_birth          date                not null,
                        m_email          varchar(30)         not null,
                        m_gender         varchar(10)         not null,
                        intro            varchar(255)        null,
                        m_like           bigint default 0    not null,
                        m_nick           varchar(20)         not null,
                        m_pw             varchar(100)        not null,
                        m_phone          varchar(11)         not null,
                        profile_img_name varchar(255)        null,
                        profile_img_path varchar(255)        null,
                        m_role           varchar(255)        not null,
                        m_social         bit    default b'0' not null,
) engine=InnoDB;

create table recruit (
                         c_id           bigint auto_increment
                             primary key,
                         moddate        datetime(6)      not null,
                         regdate        datetime(6)      not null,
                         c_city         varchar(30)      not null,
                         c_content      longtext         not null,
                         c_cur_num      bigint default 1 not null,
                         c_travel_end   date             not null,
                         img_name       varchar(255)     null,
                         img_path       varchar(255)     null,
                         c_per_num      bigint           not null,
                         tourspot       varchar(10)      null,
                         c_travel_start date             not null,
                         c_state        varchar(20)      not null,
                         c_title        varchar(30)      not null,
                         c_viewcount    bigint default 1 not null,
                         c_writer_id    bigint           not null,
) engine=InnoDB;

create table comment
(
    cc_id        bigint auto_increment
        primary key,
    moddate      datetime     not null,
    regdate      datetime     not null,
    cc_content   varchar(500) not null,
    cc_writer_id bigint       not null,
    c_id         bigint       not null
) engine=InnoDB;

create table applying (
                          c_applier_id bigint not null,
                          c_id bigint not null,
                          is_ok bit not null,
                          primary key (c_applier_id, c_id)
) engine=InnoDB;

create table review (
                        reviewer_id       bigint       not null,
                        review_comment    varchar(100) not null,
                        reviewed_memberid bigint       not null,
                        c_id              bigint       not null,
                        primary key (reviewed_memberid, c_id, reviewer_id)
) engine=InnoDB;

-- UK, FK

-- MEMBER
alter table member
    add constraint UK_MEMBER_EMAIL unique (m_email);

alter table member
    add constraint UK_MEMBER_NICK unique (m_nick);

-- RECRUIT

alter table recruit
    add constraint FK_RECRUIT_C_WRITER_ID
        foreign key (c_writer_id) references member (m_id);

-- COMMENT

alter table comment
    add constraint FK_COMMENT_CC_WRITER_ID
        foreign key (cc_writer_id) references member (m_id);

alter table comment
    add constraint FK_COMMENT_C_ID
        foreign key (c_id) references recruit (c_id);

-- APPLYING

alter table applying
    add constraint FK_APPLYING_C_APPLIER_ID
        foreign key (c_applier_id) references member (m_id);

alter table applying
    add constraint FK_APPLYING_C_ID
        foreign key (c_id) references recruit (c_id);

-- REVIEW

alter table review
    add constraint FK_REVIEW_REVIEWED_MEMBER_C_ID
        foreign key (reviewed_memberid, c_id) references applying (c_applier_id, c_id);

alter table review
    add constraint FK_REVIEW_REVIEWER_ID
        foreign key (reviewer_id) references member (m_id);