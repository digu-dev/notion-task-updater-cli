CREATE TABLE tasks
(
    id                   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title                VARCHAR(500)   NOT NULL,
    type                 VARCHAR(50),
    category             VARCHAR(100),
    subcategory          VARCHAR(100),
    horizon              INTEGER,
    priority             VARCHAR(50),
    importance           VARCHAR(100),
    objective            VARCHAR(100),
    severity             VARCHAR(50),
    owner_to_do          VARCHAR(255),
    key_result_owner     VARCHAR(255),
    bug_owner            VARCHAR(255),
    status               VARCHAR(50)    NOT NULL DEFAULT 'NOT_STARTED',
    description          TEXT,
    sequence_to_do       INTEGER,
    progress_percent     INTEGER,
    estimated_hours      NUMERIC(10, 2),
    start_date           DATE,
    due_date             DATE,
    due_date_day_of_week VARCHAR(50),
    delivery_deadline    VARCHAR(100),
    hours_invested       NUMERIC(10, 2),
    log_hours            NUMERIC(10, 2),
    completion_date      TIMESTAMP,
    completed_on_time    BOOLEAN,
    business_account     VARCHAR(255),
    contact              VARCHAR(255),
    how_it_was_done      TEXT,
    tester               VARCHAR(255),
    related_tasks        TEXT,
    endpoint_related     VARCHAR(500),
    address              TEXT,
    approved_by          VARCHAR(255),
    approved_at          DATE,
    notion_page_id       VARCHAR(255),
    sync_status          VARCHAR(50)    NOT NULL DEFAULT 'PENDING_SYNC',
    created_at           TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at           TIMESTAMP      NOT NULL DEFAULT NOW()
);

CREATE TABLE task_tags
(
    task_id UUID         NOT NULL REFERENCES tasks (id) ON DELETE CASCADE,
    tag     VARCHAR(100) NOT NULL,
    CONSTRAINT pk_task_tags PRIMARY KEY (task_id, tag)
);

CREATE INDEX idx_tasks_status ON tasks (status);
CREATE INDEX idx_tasks_sync_status ON tasks (sync_status);
CREATE INDEX idx_tasks_due_date ON tasks (due_date);
CREATE INDEX idx_task_tags_task_id ON task_tags (task_id);
