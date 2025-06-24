CREATE TABLE lab
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    lab_name VARCHAR(255) NULL,
    address  VARCHAR(255) NULL,
    CONSTRAINT pk_lab PRIMARY KEY (id)
);

CREATE TABLE lab_map_lab_test
(
    lab_id  BIGINT NOT NULL,
    test_id BIGINT NOT NULL,
    CONSTRAINT pk_lab_map_lab_test PRIMARY KEY (lab_id, test_id)
);

CREATE TABLE lab_test
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    test_name VARCHAR(255) NULL,
    CONSTRAINT pk_labtest PRIMARY KEY (id)
);

ALTER TABLE lab_map_lab_test
    ADD CONSTRAINT fk_labmaplabtes_on_lab FOREIGN KEY (lab_id) REFERENCES lab (id);

ALTER TABLE lab_map_lab_test
    ADD CONSTRAINT fk_labmaplabtes_on_lab_test FOREIGN KEY (test_id) REFERENCES lab_test (id);