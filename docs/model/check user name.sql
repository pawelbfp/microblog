-- Function: checkusername(character varying, character varying)

DROP FUNCTION checkusername(character varying, character varying);

CREATE OR REPLACE FUNCTION checkusername(username character varying, pass character varying)
  RETURNS integer AS
$BODY$
DECLARE 
  id integer;
BEGIN
  select "IdUser" into id from "Users" where "Username" = username AND "Password"=pass;
  return id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION checkusername(character varying, character varying)
  OWNER TO postgres;


Select "checkusername"(
    'Ola',
    'Ola'
);
