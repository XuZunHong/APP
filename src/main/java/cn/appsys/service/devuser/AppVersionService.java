package cn.appsys.service.devuser;

import cn.appsys.pojo.AppVersion;

import java.util.List;

/**
 * @author: Xiaoxu
 * @description:app版本信息业务逻辑层接口
 * @time: 2020/11/30
 */
public interface AppVersionService {

    //获取全部版本列表
    List<AppVersion> getAppVersionList(Integer var1) throws Exception;
    //添加版本信息
    boolean appsysadd(AppVersion var1) throws Exception;

    AppVersion getAppVersionById(Integer var1) throws Exception;

    boolean modify(AppVersion var1) throws Exception;

}
