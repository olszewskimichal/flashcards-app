create table card (id bigint generated by default as identity, answer varchar(255), question varchar(255), primary key (id))
create table post_card (topic_id bigint not null, card_id bigint not null)
create table test (id bigint generated by default as identity, primary key (id))
create table topic (id bigint generated by default as identity, primary key (id))
create table user (id bigint generated by default as identity, first_name varchar(255), last_name varchar(255), primary key (id))
alter table post_card add constraint FK1ryt81qw1va78w0qx54bqfb5a foreign key (card_id) references card
alter table post_card add constraint FKottchvqaiaof2oiv8n508adpr foreign key (topic_id) references topic
