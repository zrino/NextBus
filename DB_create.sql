CREATE table transport(
 id INTEGER PRIMARY KEY,
 type INTEGER,
 transport_id INTEGER
  );

CREATE table day(
  id INTEGER PRIMARY KEY,
  day_type INTEGER,
  raw_content TEXT,
  transport_id INTEGER,
  last_updated DATE,
  FOREIGN KEY(transport_id) REFERENCES transport(id),
  UNIQUE (day_type, transport_id) ON CONFLICT REPLACE
  );


INSERT INTO transport(type, transport_id) VALUES(1,218);
INSERT INTO transport(type, transport_id) VALUES(1,219);
INSERT INTO transport(type, transport_id) VALUES(1,281);

INSERT INTO day(day_type, raw_content, transport_id, last_updated) VALUES (1, "test_raw_content", 1, time("now"));
INSERT INTO day(day_type, raw_content, transport_id, last_updated) VALUES (2, "test_raw_content", 1, time("now"));


