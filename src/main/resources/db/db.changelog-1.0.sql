--liquibase formatted sql
--changeset amipatil:202001040901
CREATE TABLE users
(
   username varchar_ignorecase (50) NOT null primary key,
   password varchar_ignorecase (50) NOT null,
   enabled boolean NOT null
);
CREATE TABLE authorities
(
   username varchar_ignorecase (50) NOT null,
   authority varchar_ignorecase (50) NOT null,
   cONstraint fk_authorities_users foreign key (username) references users (username)
);
CREATE unique INDEX ix_auth_username ON authorities
(
   username,
   authority
);
-- When liquibase enabled, spring automatically disabled 'spring.jpa.generate-ddl' which seems to be correct, so we need to create all tables, otherwise we can enable 'spring.jpa.generate-ddl'
CREATE TABLE book
(
   id bigint primary key AUTO_INCREMENT,
   created_on timestamp,
   updated_on timestamp,
   is_deleted boolean,
   author varchar (100),
   description varchar (255),
   isbn varchar (100),
   reader varchar (255),
   title varchar (255),
   constraint fk_book_users foreign key (reader) references users (username)
);
-- In auto mode, hibernate use sequence with H2 database with name 'hibernate_sequence'
CREATE SEQUENCE hibernate_sequence start with 1 increment by 1 nocycle;
-- rollback DROP INDEX ix_auth_username ON authorities;
-- rollback DROP TABLE authorities;
-- rollback DROP TABLE users;
-- rollback DROP TABLE  book ;
-- rollback DROP SEQUENCE hibernate_sequence;