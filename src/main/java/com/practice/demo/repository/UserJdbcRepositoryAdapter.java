package com.practice.demo.repository;

import com.practice.demo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "repository.type",
        havingValue = "jdbc"
)
public class UserJdbcRepositoryAdapter implements UserRepository {

    private static final String INSERT_SQL = """
            INSERT INTO t_users (first_name, last_name, is_active, email)
            VALUES (?, ?, ?, ?)
            RETURNING id
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

    @Override
    public UserEntity save(UserEntity user) {
        Long id = jdbcTemplate.queryForObject(
                INSERT_SQL,
                Long.class,
                user.getFirstName(),
                user.getLastName(),
                user.getIsActive(),
                user.getEmail()
        );
        user.setId(id);
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
