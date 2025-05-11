CREATE TABLE patient (
    id INT AUTO_INCREMENT not null,
    name VARCHAR(100) NOT NULL,
    age int NOT NULL,
    `condition` VARCHAR(20),
    self_care BIT NOT NULL DEFAULT 0,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT,
    updated_by INT,
    deleted TINYINT(1) NOT NULL DEFAULT 0,

    PRIMARY KEY(id),
    CONSTRAINT fk_created_by_patient_user_id FOREIGN KEY (created_by) REFERENCES user(id),
    CONSTRAINT fk_updated_by_patient_user_id FOREIGN KEY (updated_by) REFERENCES user(id)
);