package cn.appsys.service.devuser;

import cn.appsys.dao.appcategory.AppCategoryMapper;
import cn.appsys.pojo.AppCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: APP
 * @description: APP类别业务逻辑实现类
 * @author: Xiaoxu
 * @create: 2020-11-29 22:58
 **/
@Service
public class AppCategoryServiceImpl implements AppCategoryService {
    @Resource
    private AppCategoryMapper mapper;

    public AppCategoryServiceImpl() {
    }

    public List<AppCategory> getAppCategoryListByParentId(Integer parentId) throws Exception {
        return this.mapper.getAppCategoryListByParentId(parentId);
    }
}