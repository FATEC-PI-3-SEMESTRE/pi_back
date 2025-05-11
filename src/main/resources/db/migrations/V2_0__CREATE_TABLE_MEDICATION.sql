CREATE TABLE medication (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    medication_function VARCHAR(255),
    dosage_weight FLOAT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    created_by INT,
    updated_by INT,
    deleted TINYINT(1) NOT NULL DEFAULT 0,

    CONSTRAINT FK_Medication_CreatedBy FOREIGN KEY (created_by) REFERENCES user(id),
    CONSTRAINT FK_Medication_UpdatedBy FOREIGN KEY (updated_by) REFERENCES user(id)
);
