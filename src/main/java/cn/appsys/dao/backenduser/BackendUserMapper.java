package cn.appsys.dao.backenduser;


import cn.appsys.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;

/**
 * @program: APP
 * @description: 后台管理远数据访问层接口
 * @author: Xiaoxu
 * @create: 2020-11-26 14:53
 **/
public interface BackendUserMapper {
    BackendUser getLoginUser(@Param("userCode") String var1) throws Exception;

}