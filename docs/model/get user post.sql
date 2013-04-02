-- Function: getuserpost(integer, integer)

 DROP FUNCTION getuserpost(integer, integer);


CREATE OR REPLACE FUNCTION getuserpost(IN iduser integer, IN idpost integer)
  RETURNS character varying AS
$BODY$
DECLARE    r character varying; 
BEGIN
  select "Body"  into r from "Posts" where "IdUser"= iduser AND "IdPost" = idpost ;
  return r;
END
$BODY$
  LANGUAGE plpgsql VOLATILE;

SELECT getuserpost(   1,    1);
  
