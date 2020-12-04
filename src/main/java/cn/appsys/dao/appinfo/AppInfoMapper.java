package cn.appsys.dao.appinfo;

import cn.appsys.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Xiaoxu
 * @description:app数据访问层接口
 * @time: 2020/11/28
 */
public interface AppInfoMapper {

    //获取app信息列表
    List<AppInfo> getAppInfoList(@Param("softwareName") String var1, @Param("status") Integer var2, @Param("categoryLevel1") Integer var3, @Param("categoryLevel2") Integer var4, @Param("categoryLevel3") Integer var5, @Param("flatformId") Integer var6, @Param("devId") Integer var7, @Param("from") Integer var8, @Param("pageSize") Integer var9) throws Exception;
    //获取app信息列表记录数
    int getAppInfoCount(@Param("softwareName") String var1, @Param("status") Integer var2, @Param("categoryLevel1") Integer var3, @Param("categoryLevel2") Integer var4, @Param("categoryLevel3") Integer var5, @Param("flatformId") Integer var6, @Param("devId") Integer var7) throws Exception;

    //添加app信息
    boolean add(AppInfo var)throws  Exception;

    AppInfo getAppInfo(@Param("id") Integer var1, @Param("APKName") String var2) throws Exception;

    int updateVersionId(@Param("versionId") Integer var1, @Param("id") Integer var2) throws Exception;

    int modify(AppInfo var1) throws Exception;


    int deleteAppInfoById(@Param("id") Integer var1) throws Exception;
}
 