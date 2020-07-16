use blog;
create table user(
`id` int not null auto_increment ,
`userId` varchar(30) not null,
`userPassword` varchar(50) not null,
`userName` varchar(50) not null,
`sex` bool not null,
`introduction` text,
`school` varchar(20),
primary key(`id`)
)DEFAULT CHARSET=utf8;

