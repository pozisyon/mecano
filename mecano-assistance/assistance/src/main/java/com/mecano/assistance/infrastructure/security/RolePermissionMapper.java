package com.mecano.assistance.infrastructure.security;

import com.mecano.assistance.domain.model.Permission;
import com.mecano.assistance.domain.model.Role;

import java.util.Set;

public class RolePermissionMapper {

    public static Set<Permission> permissionsFor(Role role) {
        return switch (role) {

            case SYSTEM_ADMIN -> Set.of(Permission.values());

            case SUPPLIER_VALIDATION_AGENT -> Set.of(
                    Permission.MECHANIC_READ,
                    Permission.MECHANIC_VALIDATE,
                    Permission.MECHANIC_REJECT,
                    Permission.GARAGE_READ,
                    Permission.GARAGE_VALIDATE,
                    Permission.GARAGE_REJECT
            );

            case SUPPLIER_VALIDATION_MANAGER -> Set.of(
                    Permission.MECHANIC_READ,
                    Permission.MECHANIC_VALIDATE,
                    Permission.MECHANIC_REJECT,
                    Permission.GARAGE_READ,
                    Permission.GARAGE_VALIDATE,
                    Permission.GARAGE_REJECT,
                    Permission.AUDIT_READ
            );

            case OPERATIONS_AGENT -> Set.of(
                    Permission.INTERVENTION_READ,
                    Permission.INTERVENTION_MANAGE,
                    Permission.DISPATCH_MONITOR,
                    Permission.MECHANIC_READ
            );

            case OPERATIONS_MANAGER -> Set.of(
                    Permission.INTERVENTION_READ,
                    Permission.INTERVENTION_MANAGE,
                    Permission.DISPATCH_MONITOR,
                    Permission.MECHANIC_READ,
                    Permission.AUDIT_READ
            );

            case SUPPORT_AGENT -> Set.of(
                    Permission.DISPUTE_READ,
                    Permission.DISPUTE_MANAGE,
                    Permission.USER_READ,
                    Permission.INTERVENTION_READ
            );

            case SUPPORT_MANAGER -> Set.of(
                    Permission.DISPUTE_READ,
                    Permission.DISPUTE_MANAGE,
                    Permission.USER_READ,
                    Permission.INTERVENTION_READ,
                    Permission.AUDIT_READ
            );

            case FINANCE_AGENT -> Set.of(
                    Permission.PAYMENT_READ,
                    Permission.FINANCIAL_REPORT_READ
            );

            case FINANCE_MANAGER -> Set.of(
                    Permission.PAYMENT_READ,
                    Permission.PAYMENT_REFUND,
                    Permission.FINANCIAL_REPORT_READ,
                    Permission.AUDIT_READ
            );

            default -> Set.of();
        };
    }
}