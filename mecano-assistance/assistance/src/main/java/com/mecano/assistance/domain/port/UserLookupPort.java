package com.mecano.assistance.domain.port;

import java.util.Optional;
import java.util.UUID;

public interface UserLookupPort {

    Optional<String> findEmailById(UUID userId);
}