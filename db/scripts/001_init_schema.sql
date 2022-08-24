create table if not exists engine(
    id serial primary key,
    type varchar not null,
    volume int not null
);

create table if not exists transmission(
   id serial primary key,
   type varchar not null,
   gears int not null
);

create table if not exists body(
    id serial primary key,
    type varchar not null,
    colour varchar not null
);

create table if not exists vehicle(
    id serial primary key,
    brand varchar not null,
    model varchar not null,
    engine_id int not null references engine(id),
    transmission_id int not null references transmission(id),
    body_id int not null references body(id),
    mileage int not null,
    owners_count int not null
);

create table if not exists user(
    id serial primary key,
    name varchar not null,
    login varchar not null unique,
    password varchar not null
);

create table if not exists item(
    id serial primary key,
    header varchar not null,
    description varchar not null,
    vehicle_id int not null references vehicle(id),
    is_sales bool,
    photo bytea not null,
    user_id int references user(id),
    created timestamp not null
);