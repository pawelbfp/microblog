-- Function: getusernotsubscribed(integer)

 DROP FUNCTION getusernotsubscribed(integer);

CREATE OR REPLACE FUNCTION getusernotsubscribed(IN id integer)
  RETURNS SETOF character varying AS
$BODY$
DECLARE 
  users record; 
BEGIN

   for users in SELECT "IdUser" FROM "Users" Where 
		"IdUser" != id  AND
		"IdUser" not in 
		( Select "IdSubscribed" FROM "Subcriptions" WHERE "IdSubscriber"= id )
   loop 
      return next users; 
   end loop; 



END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getusernotsubscribed(integer)
  OWNER TO postgres;

select getusernotsubscribed(4)