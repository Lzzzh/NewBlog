use blog;
create table blog(
`id` int(11) not null auto_increment,
`userId` varchar(100) not null,
`blogTitle` varchar(1000),
`blogText` varchar(2000),
`dateTime` timestamp,
primary key(`id`)
)DEFAULT CHARSET=utf8;