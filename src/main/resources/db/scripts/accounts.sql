create table accounts
(
	"AccountNumber" serial not null
		constraint accounts_pk
			primary key,
	"Balance" real not null
);

create unique index accounts_accountnumber_uindex
	on accounts ("AccountNumber");

