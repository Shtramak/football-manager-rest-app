CREATE TABLE team (
  id         BIGSERIAL,
  name       VARCHAR(255) NOT NULL,
  captain_id BIGINT,
  CONSTRAINT team_PK PRIMARY KEY (id)
);

CREATE TABLE player (
  id         BIGSERIAL,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  position   VARCHAR(255) NOT NULL,
  birthday   DATE         NOT NULL,
  team_id    bigint,
  CONSTRAINT player_PK PRIMARY KEY (id),
  CONSTRAINT player_team_FK FOREIGN KEY (team_id) REFERENCES team
);