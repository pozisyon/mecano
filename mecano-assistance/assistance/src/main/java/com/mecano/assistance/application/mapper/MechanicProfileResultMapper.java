package com.mecano.assistance.application.mapper;

import com.mecano.assistance.application.result.MechanicProfileResult;
import com.mecano.assistance.domain.model.MechanicProfile;

public class MechanicProfileResultMapper {
    public static MechanicProfileResult toResult(MechanicProfile profile) {
        return new MechanicProfileResult(
                profile.getId(),
                profile.getUserId(),
                profile.getFullName(),
                profile.getSpeciality(),
                profile.isAvailable(),
                profile.isApproved(),
                profile.getRating(),
                profile.getCurrentLocation().latitude(),
                profile.getCurrentLocation().longitude()
        );
    }
}
