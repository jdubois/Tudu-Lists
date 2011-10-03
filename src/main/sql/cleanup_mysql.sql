delete l from tuser_todo_list l inner join tuser u on u.login=l.users_login where u.lastAccessDate < "2011-01-01 00:00:00";
delete r from tuser_role r inner join tuser u on u.login=r.tuser_login where u.lastAccessDate < "2011-01-01 00:00:00";
delete r from tuser_role r inner join tuser u on u.login=r.tuser_login where u.lastAccessDate < "2011-01-01 00:00:00";
update todo t, tuser u set t.assignedUser_login=null where u.login=t.assignedUser_login and u.lastAccessDate < "2011-01-01 00:00:00";
delete u from tuser u where u.lastAccessDate < "2011-01-01 00:00:00";
delete t from todo t where t.todoList_id in(select tl.id from todo_list tl left outer join tuser_todo_list ttl on tl.id=ttl.todoLists_id where ttl.todoLists_id is null);
delete tl from todo_list tl left outer join tuser_todo_list ttl on tl.id=ttl.todoLists_id where ttl.todoLists_id is null;