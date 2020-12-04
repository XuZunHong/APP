package cn.appsys.dao.appcategory;

import cn.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {
    List<AppCategory> getAppCategoryListByParentId(@Param("parentId") Integer var1) throws Exception;

}
