create table website_rank(
    id bigint(20) not null auto_increment,
    rank bigint(20) not null,
    url varchar(100) not null,
    created date not null,
    primary key (`id`)
);
--partition by range columns(created) (
--     PARTITION p0 VALUES LESS THAN ('2017-01-01'),
--    PARTITION p1 VALUES LESS THAN ('2017-02-01'),
--    PARTITION p2 VALUES LESS THAN ('2017-03-01'),
--    PARTITION p3 VALUES LESS THAN ('2017-04-01'),
--    PARTITION p4 VALUES LESS THAN ('2017-05-01'),
--    PARTITION p5 VALUES LESS THAN ('2017-06-01'),
--    PARTITION p6 VALUES LESS THAN ('2017-07-01'),
--    PARTITION p7 VALUES LESS THAN ('2017-08-01'),
--    PARTITION p8 VALUES LESS THAN ('2017-09-01'),
--    PARTITION p9 VALUES LESS THAN ('2017-10-01'),
--    PARTITION p10 VALUES LESS THAN ('2017-11-01'),
--    PARTITION p11 VALUES LESS THAN MAXVALUE
--);