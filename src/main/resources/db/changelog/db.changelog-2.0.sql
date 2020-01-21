--liquibase formatted sql
--changeset amipatil:202001040930
INSERT INTO users VALUES
(
   'user',
   'password',
   true
);
-- spring authorities always start with ROLE_
INSERT INTO authorities VALUES
(
   'user',
   'ROLE_READER'
);