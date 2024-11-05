CREATE DATABASE CulinaryAcademy IF NOT EXISTS;
USE CulinaryAcademy;

CREATE TABLE student (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          contact VARCHAR(20) UNIQUE NOT NULL,
                          address VARCHAR(255),
                          joined_at DATETIME
);


CREATE TABLE course (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           course_id VARCHAR(100) UNIQUE NOT NULL,
                           course_name VARCHAR(255) NOT NULL,
                           duration_months INT,
                           fee DECIMAL(10, 2),
                           description VARCHAR(255)
);


CREATE TABLE registration (
                               id INT PRIMARY KEY AUTO_INCREMENT,
                               student_id INT,
                               course_id INT,
                               registration_date DATETIME,
                               status VARCHAR(50),
                               start_date DATETIME,
                               end_date DATETIME,
                               FOREIGN KEY (student_id) REFERENCES student(id) ON UPDATE CASCADE ON DELETE CASCADE,
                               FOREIGN KEY (course_id) REFERENCES course(id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE payment (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          registration_id INT,
                          amount DECIMAL(10, 2),
                          payment_method VARCHAR(50),
                          payment_date DATETIME,
                          transaction_id VARCHAR(100),
                          status VARCHAR(50),
                          FOREIGN KEY (registration_id) REFERENCES registration(id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE user (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       name VARCHAR(255),
                       is_active BOOLEAN
);


CREATE TABLE role (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(100) UNIQUE NOT NULL,
                       description VARCHAR(255)
);


CREATE TABLE user_role (
                            user_id INT,
                            role_id INT,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE
);
