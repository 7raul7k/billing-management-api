package ro.myclass.billingmanagementapi.permission.service;

import ro.myclass.billingmanagementapi.permission.dto.PermissionDTO;

public interface PermissionCommandService {

    void addPermission(PermissionDTO permissionDTO);

    void deletePermission(String title);

    void updatePermission(PermissionDTO permissionDTO);

}
