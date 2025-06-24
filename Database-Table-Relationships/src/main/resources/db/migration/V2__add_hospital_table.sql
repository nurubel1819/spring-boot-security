CREATE TABLE hospital_table
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    hospital_name    VARCHAR(255) NOT NULL,
    hospital_address VARCHAR(255) NULL,
    CONSTRAINT pk_hospital_table PRIMARY KEY (id)
);

ALTER TABLE lab
    ADD hospital_id BIGINT NULL;

ALTER TABLE lab
    ADD CONSTRAINT FK_LAB_ON_HOSPITAL FOREIGN KEY (hospital_id) REFERENCES hospital_table (id);