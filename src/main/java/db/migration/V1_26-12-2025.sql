select constraint_name from information_schema.constraint_column_usage where table_name = 'users_access'
and column_name = 'access_id' and constraint_name <> 'unique_access_user' 
and constraint_name <> 'users_access_access_id_not_null'


alter table users_access drop constraint "uk334qtcbwdr3q9ah8ocu75pdxn";