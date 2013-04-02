-- Function: getuserposts(integer)

-- DROP FUNCTION getuserposts(integer);

CREATE OR REPLACE FUNCTION getuserposts(IN iduser integer)
  RETURNS SETOF character varying AS
$BODY$
DECLARE
 posts record;
BEGIN

   for posts in select "Body" from "Posts" where "IdUser"= iduser
   loop 
      return next posts; 
   end loop; 

END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getuserposts(integer)
  OWNER TO postgres;


select getuserposts(1);
