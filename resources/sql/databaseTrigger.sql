CREATE OR REPLACE TRIGGER account_creation_trigger
BEFORE INSERT
   ON PatientAccount
   FOR EACH ROW

DECLARE
v_username varchar2(100);

BEGIN

SELECT user INTO v_username
FROM dual;

INSERT INTO ActivityLog (Username, Activity, EventDate)
VALUES (v_username, 'CREATION', SYSDATE);

END;

/

CREATE OR REPLACE TRIGGER account_deletion_trigger
AFTER DELETE
ON PatientAccount
   FOR EACH ROW

DECLARE
v_username varchar2(100);

BEGIN

SELECT user INTO v_username
FROM dual;

INSERT INTO ActivityLog (Username, Activity, EventDate)
VALUES (v_username, 'DELETION', SYSDATE);

END;

/