-- Function: getusers()

DROP FUNCTION getusers();

CREATE TYPE userdata AS (id INTEGER,username character varying);

CREATE OR REPLACE FUNCTION getusers()
  RETURNS SETOF userdata AS
$BODY$
DECLARE
 userdata record;
BEGIN

   for userdata in select "IdUser","Username" from "Users" loop 
      return next userdata; 
    end loop; 

END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION getusers()
  OWNER TO postgres;

  select getusers();
