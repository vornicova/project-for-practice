CREATE TRIGGER trg_users_audit
    AFTER INSERT OR UPDATE OR DELETE
    ON t_users
    FOR EACH ROW
EXECUTE FUNCTION fn_log_user_changes();
