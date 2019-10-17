package com.shan.shiro;


/*import com.norland.common.utils.Constant;
import com.norland.modules.sys.dao.SysMenuDao;
import com.norland.modules.sys.dao.SysUserDao;
import com.norland.modules.sys.entity.SysMenu;
import com.norland.modules.sys.entity.SysUser;
import org.apache.commons.lang.StringUtils;*/

import com.shan.domain.SysUser;
import com.shan.mapper.SysUserMapper;
import com.shan.utils.ShiroUtils;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 认证
 *
 * @author chenshun
 * @email wangyong@hatech.com.cn
 * @date 2016年11月10日 上午11:55:49
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;
    /*   @Autowired
    private SysMenuDao sysMenuDao;*/

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*SysUser user = (SysUser)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();
		
		List<String> permsList;
		
		//系统管理员，拥有最高权限
		if(userId == Constant.SUPER_ADMIN){
			List<SysMenu> menuList = sysMenuDao.selectList(null);
			permsList = new ArrayList<String>(menuList.size());
			for(SysMenu menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = sysUserDao.queryAllPerms(userId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}

				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				info.setStringPermissions(permsSet);
				return info;*/
        return null;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //查询用户信息
        SysUser user = new SysUser();
        user.setUsername(token.getUsername());
        user = sysUserMapper.selectByParam(user);
        //当前realm对象的name
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        //账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        //将查询到的密码返回给框架，由shiro框架进行比对，如果比对成功表示认证通过，比对不成功，抛出异常IncorrectCredentialsException
        // 三个参数  当前对象 认证密码  盐 realm名称
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), realmName);
        return info;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
   /* @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
      //指定加密方式为MD5
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.HASH_ALGORITHM_NAME);
      //加密次数
        shaCredentialsMatcher.setHashIterations(ShiroUtils.HASH_ITERATIONS);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }*/
}
