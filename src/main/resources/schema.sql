DROP SCHEMA IF EXISTS RENTABLES;
CREATE SCHEMA IF NOT EXISTS RENTABLES;

drop table if exists rentables.brand CASCADE; 
drop table if exists rentables.tool CASCADE; 
drop table if exists rentables.tooltype CASCADE;
drop table if exists rentables.agreement CASCADE;

create table rentables.brand (
	id varchar(255) not null, 
	name varchar(255) not null, 
	primary key (id));

create table rentables.tool (
	id varchar(255) not null, 
	tool_code varchar(255) not null, 
	brand_id varchar(255) not null, 
	type_id varchar(255) not null, 
	primary key (id));

create table rentables.tooltype (
	id varchar(255) not null, 
	daily_charge Decimal(5,2) not null, 
	holiday_charge boolean not null, 
	name varchar(255) not null, 
	weekday_charge boolean not null, 
	weekend_charge boolean not null, 
	primary key (id));

create table rentables.rental_agreement (
	id varchar(255) not null, 
	tool_code varchar(255) not null,--this info is available on tool but would consistently save us joins
	tool_id varchar(255) not null,
	type_id varchar(255) not null,--this info is available on tool but might consistently save us joins
	brand_id varchar(255) not null,--this info is available on tool but might consistently save us joins 
	rental_days int not null,
	charge_days int not null,
	checkout_date date not null,
	due_date date not null,
	daily_rental_charge Decimal(5,2) not null,
	pre_discount_charge Decimal(10,2) not null,
	discount_percent int not null,
	discount_amount Decimal(10,2) not null,
	final_charge Decimal(10,2) not null,	
	primary key (id));

alter table rentables.brand add constraint UK_brand_name unique (name);
alter table rentables.tool add constraint UK_tool_tool_code unique (tool_code);
alter table rentables.tooltype add constraint UK_tool_type_name unique (name);

alter table rentables.tool add constraint FK_tool_brand foreign key (brand_id) references rentables.brand;
alter table rentables.tool add constraint FK_tool_tool_type foreign key (type_id) references rentables.tooltype;

alter table rentables.rental_agreement add constraint FK_rental_agreement_tool foreign key (tool_id) references rentables.tool;
alter table rentables.rental_agreement add constraint FK_rental_agreement_tool_type foreign key (type_id) references rentables.tooltype;
alter table rentables.rental_agreement add constraint FK_rental_agreement_brand foreign key (brand_id) references rentables.brand;