-- DROP
DROP TABLE review CASCADE;
DROP TABLE applying CASCADE;
DROP TABLE comment  CASCADE;
DROP TABLE recruit CASCADE;
DROP TABLE `member` CASCADE;

-- CREATE

create table member (
                        m_id bigint not null auto_increment,
                        m_name varchar(10) not null,
                        m_email varchar(50) not null,
                        m_gender varchar(10) not null,
                        m_img longblob,
                        m_joindate date not null,
                        m_like bigint default 0,
                        m_nick varchar(20) not null,
                        m_pw varchar(100) not null,
                        m_phone varchar(11) not null,
                        m_role varchar(255),
                        m_social bit default 0,
                        m_birth date not null,
                        primary key (m_id)
) engine=InnoDB;

create table recruit (
                         c_id bigint not null auto_increment,
                         moddate datetime(6),
                         regdate datetime(6),
                         c_city varchar(30) not null,
                         c_content varchar(1000) not null,
                         c_cur_num bigint default 1 not null,
                         c_travel_end datetime(6) not null,
                         c_per_num bigint not null,
                         c_travel_start datetime(6) not null,
                         c_state varchar(20) not null,
                         c_title varchar(30) not null,
                         c_viewcount bigint default 0 not null,
                         c_writer_id bigint,
                         primary key (c_id)
) engine=InnoDB;

create table comment (
                         cc_writer_id bigint not null,
                         cc_id bigint not null,
                         moddate datetime(6),
                         regdate datetime(6),
                         cc_content varchar(500) not null,
                         c_id bigint,
                         primary key (cc_writer_id, cc_id)
) engine=InnoDB;

create table applying (
                          c_applier_id bigint not null,
                          c_id bigint not null,
                          is_ok bit not null,
                          primary key (c_applier_id, c_id)
) engine=InnoDB;

create table review (
                        reviewer_id bigint not null,
                        review_comment varchar(100) not null,
                        review_like bit default 0,
                        reviewed_memberid bigint not null,
                        c_id bigint not null,
                        primary key (reviewed_memberid, c_id, reviewer_id)
) engine=InnoDB;

-- ALTER

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