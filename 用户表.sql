use blog;
create table user(
`userId` varchar(100) not null,
`userPassword` varchar(255) not null,
`userName` varchar(100) not null,
`sex` bool not null,
`introduction` text,
`school` varchar(20),
primary key(`userId`)
)DEFAULT CHARSET=utf8;

