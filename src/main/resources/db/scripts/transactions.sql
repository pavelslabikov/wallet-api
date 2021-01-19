create type transaction_type as enum ('income', 'outcome');

create table transactions
(
	"AccountNumber" serial not null
		constraint transactions_accounts_accountnumber_fk
			references accounts
				on delete cascade,
	"Type" transaction_type not null,
	"Date" date not null,
	"Amount" real not null,
	"Description" text default ''::text
);

