package cn.appsys.service.backenduser;

import cn.appsys.dao.backenduser.BackendUserMapper;
import cn.appsys.pojo.BackendUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: APP
 * @description: 后台管理员业务逻辑层实现类
 * @author: Xiaoxu
 * @create: 2020-11-26 17:10
 **/
@Service
public class BackendUserServiceImpl implements BackendUserService {
    @Resource
    private BackendUserMapper mapper;


    public BackendUser login(String userCode, String userPassword) throws Exception {
        BackendUser user = null;
        user= this.mapper.getLoginUser(userCode);
        if (user != null && !user.getUserPassword().equals(userPassword)) {
            user = null;
        }

        return user;
    }
}