create database dictionarydb;
use dictionarydb;

CREATE TABLE Collection (
  name        varchar(250) NOT NULL,
  description text,
  images      varchar(250),
  create_date datetime NULL,
  update_time timestamp NULL,
  isAccess    tinyint DEFAULT 1,
  PRIMARY KEY (name));
CREATE TABLE History_Detail (
  user_id         int(11) NOT NULL,
  collection_name varchar(250) NOT NULL,
  action_date     datetime NULL,
  description     text NOT NULL,
  PRIMARY KEY (user_id,
  collection_name));
CREATE TABLE Part_Of_Speech (
  name        varchar(150) NOT NULL,
  description text,
  PRIMARY KEY (name));
CREATE TABLE Purchase_Detail (
  user_id         int(11) NOT NULL,
  collection_name varchar(250) NOT NULL,
  purchase_status varchar(150) NOT NULL,
  purchase_date   datetime NULL,
  inprogress_date datetime NULL,
  cancel_date     datetime NULL,
  success_date    datetime NULL,
  PRIMARY KEY (user_id,
  collection_name));
CREATE TABLE Purchase_Status (
  name        varchar(150) NOT NULL,
  description text,
  PRIMARY KEY (name));
CREATE TABLE Role_Access_Collection (
  user_id         int(11) NOT NULL,
  collection_name varchar(250) NOT NULL,
  isAccess        tinyint DEFAULT 1,
  PRIMARY KEY (user_id,
  collection_name));
CREATE TABLE `User` (
  id             int(11) NOT NULL AUTO_INCREMENT,
  email          varchar(150) UNIQUE,
  password       varchar(250),
  isActive       tinyint,
  register_date  datetime NULL,
  update_date    timestamp NULL,
  full_name      varchar(150),
  phone_number   varchar(15) UNIQUE,
  tokenTimestamp bigint(20),
  user_type      varchar(150) NOT NULL,
  PRIMARY KEY (id));
CREATE TABLE User_Type (
  name        varchar(150) NOT NULL,
  description text,
  PRIMARY KEY (name));
CREATE TABLE Vocabulary (
  id                  int(11) NOT NULL AUTO_INCREMENT,
  english_word        varchar(100) UNIQUE,
  pronunciation       varchar(100),
  vietnamese_meaning  text,
  images              varchar(250),
  example             text,
  update_time         timestamp NULL,
  abbreviation        varchar(50),
  sound               varchar(250),
  english_meaning     text,
  part_of_speech_name varchar(150) NOT NULL,
  PRIMARY KEY (id));
CREATE TABLE Vocabulary_Collection (
  vocabulary_id   int(11) NOT NULL,
  collection_name varchar(250) NOT NULL,
  PRIMARY KEY (vocabulary_id,
  collection_name));
ALTER TABLE Role_Access_Collection ADD CONSTRAINT FKRole_Acces808525 FOREIGN KEY (collection_name) REFERENCES Collection (name);
ALTER TABLE Role_Access_Collection ADD CONSTRAINT FKRole_Acces507494 FOREIGN KEY (user_id) REFERENCES `User` (id);
ALTER TABLE History_Detail ADD CONSTRAINT FKHistory_De367374 FOREIGN KEY (user_id) REFERENCES `User` (id);
ALTER TABLE History_Detail ADD CONSTRAINT FKHistory_De288196 FOREIGN KEY (collection_name) REFERENCES Collection (name);
ALTER TABLE Purchase_Detail ADD CONSTRAINT FKPurchase_D456446 FOREIGN KEY (purchase_status) REFERENCES Purchase_Status (name);
ALTER TABLE Purchase_Detail ADD CONSTRAINT FKPurchase_D988187 FOREIGN KEY (user_id) REFERENCES `User` (id);
ALTER TABLE Purchase_Detail ADD CONSTRAINT FKPurchase_D667382 FOREIGN KEY (collection_name) REFERENCES Collection (name);
ALTER TABLE `User` ADD CONSTRAINT FKUser848884 FOREIGN KEY (user_type) REFERENCES User_Type (name);
ALTER TABLE Vocabulary ADD CONSTRAINT FKVocabulary619903 FOREIGN KEY (part_of_speech_name) REFERENCES Part_Of_Speech (name);
ALTER TABLE Vocabulary_Collection ADD CONSTRAINT FKVocabulary684438 FOREIGN KEY (collection_name) REFERENCES Collection (name);
ALTER TABLE Vocabulary_Collection ADD CONSTRAINT FKVocabulary904653 FOREIGN KEY (vocabulary_id) REFERENCES Vocabulary (id);
