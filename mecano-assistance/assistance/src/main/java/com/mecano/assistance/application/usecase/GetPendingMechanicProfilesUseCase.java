package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.mapper.MechanicProfileResultMapper;
import com.mecano.assistance.application.result.MechanicProfileResult;
import com.mecano.assistance.domain.port.MechanicProfileRepositoryPort;

import java.util.List;

public class GetPendingMechanicProfilesUseCase {


        private final MechanicProfileRepositoryPort mechanicProfileRepository;

        public GetPendingMechanicProfilesUseCase(
                MechanicProfileRepositoryPort mechanicProfileRepository
        ) {
            this.mechanicProfileRepository = mechanicProfileRepository;
        }

        public List<MechanicProfileResult> execute() {
            return mechanicProfileRepository.findPendingApproval()
                    .stream()
                    .map(MechanicProfileResultMapper::toResult)
                    .toList();
        }

}
