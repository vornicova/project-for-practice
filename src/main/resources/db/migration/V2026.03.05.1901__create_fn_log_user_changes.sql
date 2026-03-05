CREATE OR REPLACE FUNCTION fn_log_user_changes()
    RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO t_user_logs (id, user_id, action, timestamp)
        VALUES (nextval('t_user_logs_id_seq'), NEW.id, 'CREATED', CURRENT_TIMESTAMP);
        RETURN NEW;

    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO t_user_logs (id, user_id, action, timestamp)
        VALUES (nextval('t_user_logs_id_seq'), NEW.id, 'CHANGED', CURRENT_TIMESTAMP);
        RETURN NEW;

    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO t_user_logs (id, user_id, action, timestamp)
        VALUES (nextval('t_user_logs_id_seq'), OLD.id, 'DELETED', CURRENT_TIMESTAMP);
        RETURN OLD;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;