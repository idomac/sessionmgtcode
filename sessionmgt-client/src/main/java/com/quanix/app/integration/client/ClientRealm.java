package com.quanix.app.integration.client;

import com.quanix.app.integration.remote.PermissionContext;
import com.quanix.app.integration.remote.RemoteServiceInterface;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author : lihaoquan
 */
public class ClientRealm extends AuthorizingRealm {

    private RemoteServiceInterface remoteService;
    private String appKey;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PermissionContext context = remoteService.getPermissions(appKey, username);
        authorizationInfo.setRoles(context.getRoles());
        authorizationInfo.setStringPermissions(context.getPermissions());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        throw new UnsupportedOperationException("永远不会被调用");
    }

    public void setRemoteService(RemoteServiceInterface remoteService) {
        this.remoteService = remoteService;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
