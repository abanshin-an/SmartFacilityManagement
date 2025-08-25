create table inbox (
    id varchar(20),
    facilityId varchar(20),
    messageType varchar(20),
    paramValue bigint,
    unit varchar(255),
    createdAt timestamp,
    referenceId varchar(20),
    json varchar(512),
    primary key (id)
);
create table outbox (
    id varchar(20),
    facilityId varchar(20),
    messageType varchar(20),
    paramValue bigint,
    unit varchar(255),
    createdAt timestamp,
    referenceId varchar(20),
    json varchar(512),
    primary key (id)
);
