CREATE TABLE lotteries
(
    id         bigserial primary key,
    created_at timestamp,
    status     int default 0
);

CREATE TABLE users
(
    id         bigserial primary key,
    name       varchar not null,
    age        int     not null,
    city       varchar not null,
    lottery_id bigint,
    prize      int,
    foreign key (lottery_id) references lotteries (id)
);

CREATE TABLE winners
(
    id         bigserial primary key,
    user_id    bigint not null,
    lottery_id bigint not null,
    foreign key (user_id) references users (id),
    foreign key (lottery_id) references lotteries (id),
    unique (lottery_id)
);
