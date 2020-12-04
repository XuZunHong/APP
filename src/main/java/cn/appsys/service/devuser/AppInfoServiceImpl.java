package cn.appsys.service.devuser;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.dao.appversion.AppVersionMapper;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @program: APP
 * @description: APP业务逻辑实现类
 * @author: Xiaoxu
 * @create: 2020-11-29 21:50
 **/
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoMapper mapper;
    @Resource
    private AppVersionMapper appVersionMapper;

    //获取app信息列表
    @Override
    public List<AppInfo> getAppInfoList(String querySoftwareName, Integer queryStatus, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer queryFlatformId, Integer devId, Integer currentPageNo, Integer pageSize) throws Exception {
        return this.mapper.getAppInfoList(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId, (currentPageNo - 1) * pageSize, pageSize);
    }

    //获取app信息列表记录数
    public int getAppInfoCount(String querySoftwareName, Integer queryStatus, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer queryFlatformId, Integer devId) throws Exception {
        return this.mapper.getAppInfoCount(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId);
    }

    @Override
    public boolean add(AppInfo appInfo) throws Exception {
        boolean flag = false;
        boolean add = this.mapper.add(appInfo);
        if (add=true){
            flag = true;
        }

        return flag;
    }


    public AppInfo getAppInfo(Integer id, String APKName) throws Exception {
        return this.mapper.getAppInfo(id, APKName);
    }

    public boolean modify(AppInfo appInfo) throws Exception {
        boolean flag = false;
        if (this.mapper.modify(appInfo)>0) {
            flag = true;
        }

        return flag;
    }



    public boolean appsysdeleteAppById(Integer id) throws Exception {
        boolean flag = false;
        int versionCount = this.appVersionMapper.getVersionCountByAppId(id);
        List<AppVersion> appVersionList = null;
        if (versionCount > 0) {
            appVersionList = this.appVersionMapper.getAppVersionList(id);
            Iterator var6 = appVersionList.iterator();

            while(var6.hasNext()) {
                AppVersion appVersion = (AppVersion)var6.next();
                if (appVersion.getApkLocPath() != null && !appVersion.getApkLocPath().equals("")) {
                    File file = new File(appVersion.getApkLocPath());
                    if (file.exists() && !file.delete()) {
                        throw new Exception();
                    }
                }
            }

            this.appVersionMapper.deleteVersionByAppId(id);
        }

        AppInfo appInfo = this.mapper.getAppInfo(id, (String)null);
        if (appInfo.getLogoLocPath() != null && !appInfo.getLogoLocPath().equals("")) {
            File file = new File(appInfo.getLogoLocPath());
            if (file.exists() && !file.delete()) {
                throw new Exception();
            }
        }

        if (this.mapper.deleteAppInfoById(id) > 0) {
            flag = true;
        }

        return flag;
    }
}