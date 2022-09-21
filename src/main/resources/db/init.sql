CREATE TABLE clan (
    clan_id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(50) NOT NULL,
    gold int CHECK (gold >= 0)
);

CREATE TABLE player(
    player_id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    gold int DEFAULT 0,
    username varchar(30) NOT NULL,
    clan_id bigint REFERENCES clan(clan_id)
);

CREATE TABLE quest(
    quest_id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    is_complete boolean DEFAULT false,
    gold_reward int DEFAULT 0,
    user_id bigint REFERENCES player(player_id)
);