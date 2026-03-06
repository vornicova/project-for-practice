package com.practice.demo.repository;

import com.practice.demo.entity.UserEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(
        name = "repository.type",
        havingValue = "jpa",
        matchIfMissing = true
)
public class UserJpaRepositoryAdapter implements UserRepository {

    private final UserJpaSpringDataRepository repo;

    public UserJpaRepositoryAdapter(UserJpaSpringDataRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return repo.save(user);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return repo.findAll();
    }
}
