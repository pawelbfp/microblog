-- Function: getuserdubscribed(integer)

DROP FUNCTION getusersubscribed(integer);

CREATE OR REPLACE FUNCTION getusersubscribed(IN iduser integer)
  RETURNS SETOF character varying AS
$BODY$
DECLARE 
  users record; 
BEGIN
   for users in select "IdSubscribed" from "Subcriptions" where "IdSubscriber"= iduser loop 
      return next users; 
    end loop; 
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getusersubscribed(integer)
  OWNER TO postgres;

 select getusersubscribed(1)