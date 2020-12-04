package cn.appsys.service.devuser;

import cn.appsys.pojo.AppCategory;

import java.util.List;

public interface AppCategoryService {
    List<AppCategory> getAppCategoryListByParentId(Integer var1) throws Exception;
}
