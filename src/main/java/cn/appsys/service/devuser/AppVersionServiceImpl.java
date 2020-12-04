package cn.appsys.service.devuser;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.dao.appversion.AppVersionMapper;
import cn.appsys.pojo.AppVersion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: APP
 * @description: app版本信息业务逻辑层实现类
 * @author: Xiaoxu
 * @create: 2020-11-30 15:28
 **/
@Service
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper mapper;
    @Resource
    private AppInfoMapper appInfoMapper;

    public AppVersionServiceImpl() {
    }

    public List<AppVersion> getAppVersionList(Integer appId) throws Exception {
        return this.mapper.getAppVersionList(appId);
    }

    public boolean appsysadd(AppVersion appVersion) throws Exception {
        boolean flag = false;
        Integer versionId = null;
        if (this.mapper.add(appVersion) > 0) {
            versionId = appVersion.getId();
            flag = true;
        }

        if (this.appInfoMapper.updateVersionId(versionId, appVersion.getAppId()) > 0 && flag) {
            flag = true;
        }

        return flag;
    }
    public AppVersion getAppVersionById(Integer id) throws Exception {
        return this.mapper.getAppVersionById(id);
    }

    public boolean modify(AppVersion appVersion) throws Exception {
        boolean flag = false;
        if (this.mapper.modify(appVersion) > 0) {
            flag = true;
        }

        return flag;
    }
}