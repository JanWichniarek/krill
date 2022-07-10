CREATE SEQUENCE public.categories_seq
	INCREMENT BY 50
	MINVALUE 1;

CREATE TABLE categories (
	id int8 NOT NULL,
	color varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.schedules_seq
	INCREMENT BY 50
	MINVALUE 1;

CREATE TABLE schedules (
	id int8 NOT NULL,
	schedule_id uuid NOT NULL,
	title varchar(255) NULL,
	CONSTRAINT schedule_pkey PRIMARY KEY (id)
	CONSTRAINT uk_schedule_id UNIQUE KEY (schedule_id)
);

CREATE UNIQUE INDEX schedule_id_idx ON schedules(schedule_id)

CREATE SEQUENCE public.events_seq
	INCREMENT BY 50
	MINVALUE 1;

CREATE TABLE events (
	id int8 NOT NULL,
	"date" timestamp NULL,
	description varchar(255) NULL,
	icon varchar(255) NULL,
	priority varchar(64) NULL,
	timetype varchar(64) NULL,
	title varchar(255) NOT NULL,
	"type" varchar(64) NULL,
	schedule_id int8 NULL,
	CONSTRAINT event_pkey PRIMARY KEY (id),
	CONSTRAINT fk_events_schedules FOREIGN KEY (schedule_id) REFERENCES schedules(id)
);

CREATE INDEX schedule_id_date_idx ON events(schedule_id, "date")

CREATE TABLE events_categories (
	event_id int8 NOT NULL,
	categories_id int8 NOT NULL,
	CONSTRAINT event_category_pkey PRIMARY KEY (event_id, categories_id),
	CONSTRAINT fk_event_categories_events FOREIGN KEY (event_id) REFERENCES events(id),
	CONSTRAINT fk_event_categories_categories FOREIGN KEY (categories_id) REFERENCES categories(id)
);