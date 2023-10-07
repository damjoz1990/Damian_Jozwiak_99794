insert into users (email, password)
values ('admin@google.com', '{noop}admin123'), ('user@google.com', '{noop}user123');

insert into user_role (name, description)
values ('ADMIN', 'pełne uprawnienia'), ('USER', 'podstawowe uprawnienia, możliwość oddawania głosów');

insert into user_roles (user_id, role_id)
values (1, 1), (2, 2);
