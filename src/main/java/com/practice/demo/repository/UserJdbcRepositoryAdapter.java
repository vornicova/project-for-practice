package com.practice.demo.repository;

import com.practice.demo.entity.UserEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(
        name = "repository.type",
        havingValue = "jdbc"
)
public class UserJdbcRepositoryAdapter implements UserRepository {

    private static final String INSERT_SQL = """
            INSERT INTO t_users (first_name, last_name, is_active, email)
            VALUES (?, ?, ?, ?)
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT id, first_name, last_name, is_active, email
            FROM t_users
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, first_name, last_name, is_active, email
            FROM t_users
            """;

    private static final RowMapper<UserEntity> USER_ROW_MAPPER = (rs, rowNum) ->
            UserEntity.builder()
                    .id(rs.getLong("id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .isActive(rs.getObject("is_active", Boolean.class))
                    .email(rs.getString("email"))
                    .build();

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserEntity save(UserEntity user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setObject(3, user.getIsActive());
            ps.setString(4, user.getEmail());
            return ps;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId == null) {
            throw new IllegalStateException("Failed to retrieve generated id for user");
        }

        user.setId(generatedId.longValue());
        return user;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        List<UserEntity> result = jdbcTemplate.query(FIND_BY_ID_SQL, USER_ROW_MAPPER, id);
        return result.stream().findFirst();
    }

    @Override
    public List<UserEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, USER_ROW_MAPPER);
    }
}
