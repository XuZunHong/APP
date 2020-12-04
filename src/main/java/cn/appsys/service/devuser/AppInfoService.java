package cn.appsys.service.devuser;

import cn.appsys.pojo.AppInfo;

import java.util.List;

/**
 * @author: Xiaoxu
 * @description:APP业务逻辑层接口
 * @time: 2020/11/29
 */
public interface AppInfoService {

    //获取app信息列表
    List<AppInfo> getAppInfoList(String var1, Integer var2, Integer var3, Integer var4, Integer var5, Integer var6, Integer var7, Integer var8, Integer var9) throws Exception;


    //获取app信息列表记录数
    int getAppInfoCount(String var1, Integer var2, Integer var3, Integer var4, Integer var5, Integer var6, Integer var7) throws Exception;

    //添加app信息
    boolean add(AppInfo var1) throws Exception;

    AppInfo getAppInfo(Integer var1, String var2) throws Exception;

    boolean modify(AppInfo var1) throws Exception;

    boolean appsysdeleteAppById(Integer var1) throws Exception;
}
