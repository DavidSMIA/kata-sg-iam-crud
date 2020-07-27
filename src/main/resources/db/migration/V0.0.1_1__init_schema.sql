CREATE TABLE employee
(
    id                  TEXT PRIMARY KEY,
    lastname            TEXT NOT NULL,
    firstname           TEXT,
    created_at          TIMESTAMP NOT NULL,
    last_updated_at     TIMESTAMP NOT NULL
);

CREATE TABLE role
(
    id                  TEXT PRIMARY KEY,
    code                TEXT NOT NULL,
    description         TEXT,
    created_at          TIMESTAMP NOT NULL,
    last_updated_at     TIMESTAMP NOT NULL
);

CREATE TABLE employee_role
(
    employee_id        TEXT NOT NULL,
    role_id        TEXT NOT NULL,
    created_at          TIMESTAMP ,
    last_updated_at     TIMESTAMP ,
    PRIMARY KEY (employee_id, role_id),
    CONSTRAINT fk_employee
      FOREIGN KEY(employee_id)
	  REFERENCES employee(id),
	CONSTRAINT fk_role
      FOREIGN KEY(role_id)
	  REFERENCES role(id)
);
