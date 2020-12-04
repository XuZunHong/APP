package cn.appsys.service.backenduser;

import cn.appsys.pojo.BackendUser;


/**
 * @author: Xiaoxu
 * @description:后台管理员业务逻辑层
 * @time: 2020/11/26
 */

public interface BackendUserService {
    BackendUser login(String var1, String var2) throws Exception;
}
