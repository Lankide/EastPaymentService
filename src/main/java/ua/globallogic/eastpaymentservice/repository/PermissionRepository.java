package ua.globallogic.eastpaymentservice.repository;

import ua.globallogic.eastpaymentservice.domain.Permission;

import java.util.Collection;

public interface PermissionRepository {

    public Collection<Permission> getPermissions();
    public Permission getPermission(int id);
    public Permission getPermission(String name);
}
