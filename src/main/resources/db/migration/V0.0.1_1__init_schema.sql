CREATE TABLE employee
(
    id                  UUID PRIMARY KEY,
    lastname            TEXT NOT NULL,
    firstname           TEXT,
    created_at          TIMESTAMP NOT NULL,
    last_updated_at     TIMESTAMP NOT NULL
);

CREATE TABLE role
(
    id                  UUID PRIMARY KEY,
    code                TEXT NOT NULL UNIQUE,
    description         TEXT,
    created_at          TIMESTAMP NOT NULL,
    last_updated_at     TIMESTAMP NOT NULL
);

CREATE TABLE employee_role
(
    employee_id        UUID NOT NULL,
    role_id        UUID NOT NULL,
    created_at          TIMESTAMP NOT NULL,
    last_updated_at     TIMESTAMP  NOT NULL,
    PRIMARY KEY (employee_id, role_id),
    CONSTRAINT fk_employee
      FOREIGN KEY(employee_id)
	  REFERENCES employee(id),
	CONSTRAINT fk_role
      FOREIGN KEY(role_id)
	  REFERENCES role(id)
);
