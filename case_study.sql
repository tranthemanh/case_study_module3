create database financial_management;
use financial_management;

create table wallet(
    id int not null auto_increment primary key,
    walletName nvarchar(255) not null,
    amount int
);

create table category_spend(
    id int not null auto_increment primary key,
    name nvarchar(255) not null ,
    note nvarchar(500)
);

create table category_earn(
    id int not null auto_increment primary key,
    name nvarchar(255) not null,
    note nvarchar(500)
);

create table monny_earn(
    id int not null auto_increment primary key,
    date datetime,
    amount int,
    note nvarchar(500),
    categoryEarn_id int,
    foreign key (categoryEarn_id) references category_earn(id),
    wallet_id int,
    foreign key (wallet_id) references wallet(id)
);

create table monny_spend(
    id int not null auto_increment primary key,
    date datetime,
    amount int,
    note nvarchar(500),
    categorySpend_id int,
    foreign key (categorySpend_id) references category_spend(id),
    wallet_id int,
    foreign key (wallet_id) references wallet(id)
);

insert into wallet(walletName, amount) value ('Tien Mat', 500000);
insert into wallet(walletName, amount) value ('Tai Khoan', 1000000);

update wallet set walletName = 'Luong', amount='7000000' where id =2;



insert into category_earn(name, note) value ('Tien Luong Codegym', 'Luong thang 7');
insert into category_earn(name, note) value ('Tien vo cho', 'vo cho tien tieu hang thang');
insert into category_earn(name, note) value ('Tien dau tu', 'Tien dau tu hang thang');
insert into category_spend(name, note) value ('Tien Nha', 'Tra tien nha hang thang');
insert into category_spend(name, note) value ('Tien An', 'Tra tien an hang thang');

select * from category_earn;
select * from category_spend;

insert into monny_earn(date, amount, note, categoryEarn_id, wallet_id) value ('2024-07-01', 3000000, 'Tien Luong thang 7',  1, 3);
insert into monny_earn(date, amount, note, categoryEarn_id, wallet_id) value ('2024-07-01', 1000000, 'Tien dau tu thang 7',  3, 3);

select * from monny_earn;
select * from monny_spend;

insert into monny_spend(date, amount, note, categorySpend_id, wallet_id) value ('2024-07-15', 3000000,'Chi tien nha thang 7', 1, 3);
insert into monny_spend(date, amount, note, categorySpend_id, wallet_id) value ('2024-07-15', 1000000,'Chi tien an thang 7', 2, 3);

