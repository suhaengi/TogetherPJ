-- MEMBER
alter table member
    add constraint UK_MEMBER_EMAIL unique (m_email);

alter table member
    add constraint UK_MEMBER_NICK unique (m_nick);

-- RECRUIT

alter table recruit
    add constraint FK_RECRUIT_C_WRITER_ID
        foreign key (c_writer_id) references member (m_id);

ALTER TABLE test_woon.recruit MODIFY COLUMN c_content LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL;


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