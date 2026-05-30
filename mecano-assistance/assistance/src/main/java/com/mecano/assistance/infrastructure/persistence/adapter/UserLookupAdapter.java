package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.port.UserLookupPort;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserLookupAdapter implements UserLookupPort {

    private final SpringDataUserRepository userRepository;

    public UserLookupAdapter(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> findEmailById(UUID userId) {
        return userRepository.findById(userId)
                .map(user -> user.getEmail());
    }
}