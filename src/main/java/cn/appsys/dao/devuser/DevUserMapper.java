package cn.appsys.dao.devuser;

import cn.appsys.pojo.DevUser;
import org.apache.ibatis.annotations.Select;

public interface DevUserMapper {

    //用登录名和密码查询开发者
    @Select("select * from dev_user where devCode = #{devCode} and devPassword = #{devPassword}")
    DevUser selectByNameAndPwd(DevUser devUser);
}
