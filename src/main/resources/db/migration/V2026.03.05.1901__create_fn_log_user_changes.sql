CREATE OR REPLACE FUNCTION log_user_change(
    p_user_id BIGINT,
    p_action user_action_type
)
    RETURNS VOID AS
$$
BEGIN
    INSERT INTO t_user_logs (user_id, action, timestamp)
    VALUES (p_user_id, p_action, CURRENT_TIMESTAMP);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_log_user_changes()
    RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'INSERT' THEN
        PERFORM log_user_change(NEW.id, 'CREATED');
        RETURN NEW;
    ELSIF TG_OP = 'UPDATE' THEN
        PERFORM log_user_change(NEW.id, 'CHANGED');
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        PERFORM log_user_change(OLD.id, 'DELETED');
        RETURN OLD;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;
