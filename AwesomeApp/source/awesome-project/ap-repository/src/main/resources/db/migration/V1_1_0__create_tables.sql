CREATE TABLE weather_condition_results
(
    id                bigint            AUTO_INCREMENT,
    ip_address        varchar(255)      NOT NULL,
    name              varchar(255),
    current_condition varchar(255),
    description       varchar(255),
    temp              double(18, 2),
    pressure          double(18, 2),
    humidity          double(18, 2),
    created_date      datetime          NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

