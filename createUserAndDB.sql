-- CREATE SCHEMA AND USER --
CREATE SCHEMA `startup-hibernate-db` DEFAULT CHARACTER SET utf8 ;

CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON `startup-hibernate-db`.* TO `user`@`localhost`;
FLUSH PRIVILEGES;