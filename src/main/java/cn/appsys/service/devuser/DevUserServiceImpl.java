package cn.appsys.service.devuser;

import cn.appsys.dao.devuser.DevUserMapper;
import cn.appsys.pojo.DevUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: APP
 * @description: 开发者业务逻辑层
 * @author: Xiaoxu
 * @create: 2020-11-26 23:25
 **/
@Service
public class DevUserServiceImpl implements DevUserService {
    @Autowired
    private DevUserMapper devUserMapper;

    @Override
    public DevUser login(DevUser devUser) {
        return devUserMapper.selectByNameAndPwd(devUser);
    }
}