package com.mecano.assistance.infrastructure.persistence.entity;

import com.mecano.assistance.domain.model.DispatchOfferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dispatch_offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispatchOfferJpaEntity {
    @Id
    private UUID id;

    private UUID breakdownRequestId;
    private UUID mechanicId;

    //private String status;
    @Enumerated(EnumType.STRING)
    private DispatchOfferStatus status;

    private LocalDateTime sentAt;
    private LocalDateTime expiresAt;

}