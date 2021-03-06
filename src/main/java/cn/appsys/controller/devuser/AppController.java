package cn.appsys.controller.devuser;

import cn.appsys.pojo.*;
import cn.appsys.service.devuser.AppCategoryService;
import cn.appsys.service.devuser.AppInfoService;
import cn.appsys.service.devuser.DataDictionaryService;
import cn.appsys.service.devuser.AppVersionService;
import cn.appsys.tool.PageSupport;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: APP
 * @description: app维护控制层类
 * @author: Xiaoxu
 * @create: 2020-11-29 22:13
 **/
@Controller
@RequestMapping({"/dev/flatform/app"})
public class AppController {
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private AppVersionService appVersionService;



/**
 * @author: Xiaoxu
 * @description:点击App维护显示个人的app信息列表
 * @time: 2020/11/30
 */
    @RequestMapping({"/list"})
    public String getAppInfoList(Model model, HttpSession session, @RequestParam(value = "querySoftwareName",required = false) String querySoftwareName, @RequestParam(value = "queryStatus",required = false) String _queryStatus, @RequestParam(value = "queryCategoryLevel1",required = false) String _queryCategoryLevel1, @RequestParam(value = "queryCategoryLevel2",required = false) String _queryCategoryLevel2, @RequestParam(value = "queryCategoryLevel3",required = false) String _queryCategoryLevel3, @RequestParam(value = "queryFlatformId",required = false) String _queryFlatformId, @RequestParam(value = "pageIndex",required = false) String pageIndex) {
        Integer devId = ((DevUser)session.getAttribute("devUserSession")).getId();
        List<AppInfo> appInfoList = null;
        List<DataDictionary> statusList = null;
        List<DataDictionary> flatFormList = null;
        List<AppCategory> categoryLevel1List = null;
        List<AppCategory> categoryLevel2List = null;
        List<AppCategory> categoryLevel3List = null;
        int pageSize = 5;
        Integer currentPageNo = 1;
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException var30) {
                var30.printStackTrace();
            }
        }

        Integer queryStatus = null;
        if (_queryStatus != null && !_queryStatus.equals("")) {
            queryStatus = Integer.parseInt(_queryStatus);
        }

        Integer queryCategoryLevel1 = null;
        if (_queryCategoryLevel1 != null && !_queryCategoryLevel1.equals("")) {
            queryCategoryLevel1 = Integer.parseInt(_queryCategoryLevel1);
        }

        Integer queryCategoryLevel2 = null;
        if (_queryCategoryLevel2 != null && !_queryCategoryLevel2.equals("")) {
            queryCategoryLevel2 = Integer.parseInt(_queryCategoryLevel2);
        }

        Integer queryCategoryLevel3 = null;
        if (_queryCategoryLevel3 != null && !_queryCategoryLevel3.equals("")) {
            queryCategoryLevel3 = Integer.parseInt(_queryCategoryLevel3);
        }

        Integer queryFlatformId = null;
        if (_queryFlatformId != null && !_queryFlatformId.equals("")) {
            queryFlatformId = Integer.parseInt(_queryFlatformId);
        }

        int totalCount = 0;

        try {
            totalCount = this.appInfoService.getAppInfoCount(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId);
        } catch (Exception var29) {
            var29.printStackTrace();
        }

        PageSupport pages = new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        try {
            appInfoList = this.appInfoService.getAppInfoList(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, devId, currentPageNo, Integer.valueOf(pageSize));
            statusList = this.getDataDictionaryList("APP_STATUS");
            flatFormList = this.getDataDictionaryList("APP_FLATFORM");
            categoryLevel1List = this.appCategoryService.getAppCategoryListByParentId((Integer)null);
        } catch (Exception var28) {
            var28.printStackTrace();
        }

        model.addAttribute("appInfoList", appInfoList);
        model.addAttribute("statusList", statusList);
        model.addAttribute("flatFormList", flatFormList);
        model.addAttribute("categoryLevel1List", categoryLevel1List);
        model.addAttribute("pages", pages);
        model.addAttribute("queryStatus", queryStatus);
        model.addAttribute("querySoftwareName", querySoftwareName);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        model.addAttribute("queryFlatformId", queryFlatformId);
        if (queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")) {
            categoryLevel2List = this.getCategoryList(queryCategoryLevel1.toString());
            model.addAttribute("categoryLevel2List", categoryLevel2List);
        }

        if (queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")) {
            categoryLevel3List = this.getCategoryList(queryCategoryLevel2.toString());
            model.addAttribute("categoryLevel3List", categoryLevel3List);
        }

        return "developer/appinfolist";
    }

    public List<DataDictionary> getDataDictionaryList(String typeCode) {
        List dataDictionaryList = null;

        try {
            dataDictionaryList = this.dataDictionaryService.getDataDictionaryList(typeCode);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return dataDictionaryList;
    }

    @RequestMapping(
            value = {"/datadictionarylist.json"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<DataDictionary> getDataDicList(@RequestParam String tcode) {
        return this.getDataDictionaryList(tcode);
    }
    @RequestMapping(
            value = {"/categorylevellist.json"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<AppCategory> getAppCategoryList(@RequestParam String pid) {
        if (pid.equals("")) {
            pid = null;
        }

        return this.getCategoryList(pid);
    }

    public List<AppCategory> getCategoryList(String pid) {
        List categoryLevelList = null;

        try {
            categoryLevelList = this.appCategoryService.getAppCategoryListByParentId(pid == null ? null : Integer.parseInt(pid));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return categoryLevelList;
    }



    //点击操作里的添加信息页面跳转到developer/appinfoadd页面
    @RequestMapping(value = {"/appinfoadd"},
            method = {RequestMethod.GET})
    public String add(@ModelAttribute("appInfo") AppInfo appInfo) {
        return "developer/appinfoadd";
    }

    /**
     * @author: Xiaoxu
     * @description:点击确定保存添加的按钮发生的信息
     * @time: 2020/11/30
     */
    @RequestMapping(value = {"/appinfoaddsave"},
            method = {RequestMethod.POST})
    public String addSave(AppInfo appInfo, HttpSession session, HttpServletRequest request, @RequestParam(value = "a_logoPicPath",required = false) MultipartFile attach) {
        String logoPicPath = null;
        String logoLocPath = null;
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            int filesize = 500000;
            if (attach.getSize() > (long)filesize) {
                request.setAttribute("fileUploadError", " * 上传文件过大！");
                return "developer/appinfoadd";
            }

            if (!prefix.equalsIgnoreCase("jpg") && !prefix.equalsIgnoreCase("png") && !prefix.equalsIgnoreCase("jepg") && !prefix.equalsIgnoreCase("pneg")) {
                request.setAttribute("fileUploadError", " * 上传文件格式不正确！");
                return "developer/appinfoadd";
            }

            String fileName = appInfo.getAPKName() + ".jpg";
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            try {
                attach.transferTo(targetFile);
            } catch (Exception var15) {
                var15.printStackTrace();
                request.setAttribute("fileUploadError", " * 上传失败！");
                return "developer/appinfoadd";
            }

            logoPicPath = request.getContextPath() + "/statics/uploadfiles/" + fileName;
            logoLocPath = path + File.separator + fileName;
        }

        appInfo.setCreatedBy(((DevUser)session.getAttribute("devUserSession")).getId());
        appInfo.setCreationDate(new Date());
        appInfo.setLogoPicPath(logoPicPath);
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setDevId(((DevUser)session.getAttribute("devUserSession")).getId());
        appInfo.setStatus(1);

        try {
            if (this.appInfoService.add(appInfo)) {
                return "redirect:/dev/flatform/app/list";
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        return "developer/appinfoadd";
    }


/**
 * @author: Xiaoxu
 * @description:app版本增加
 * @time: 2020/11/30
 */
    @RequestMapping(
            value = {"/appversionadd"},
            method = {RequestMethod.GET}
    )
    public String addVersion(@RequestParam("id") String appId, @RequestParam(value = "error",required = false) String fileUploadError, AppVersion appVersion, Model model) {

        if (fileUploadError != null && fileUploadError.equals("error1")) {
            fileUploadError = " * APK信息不完整！";
        } else if (fileUploadError != null && fileUploadError.equals("error2")) {
            fileUploadError = " * 上传失败！";
        } else if (fileUploadError != null && fileUploadError.equals("error3")) {
            fileUploadError = " * 上传文件格式不正确！";
        }

        appVersion.setAppId(Integer.parseInt(appId));
        List appVersionList = null;

        try {
            appVersionList = this.appVersionService.getAppVersionList(Integer.parseInt(appId));
            appVersion.setAppName(this.appInfoService.getAppInfo(Integer.parseInt(appId), (String)null).getSoftwareName());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        model.addAttribute("appVersionList", appVersionList);
        model.addAttribute(appVersion);
        model.addAttribute("fileUploadError", fileUploadError);
        return "developer/appversionadd";
    }

    @RequestMapping(
            value = {"/addversionsave"},
            method = {RequestMethod.POST}
    )
    public String addVersionSave(AppVersion appVersion, HttpSession session, HttpServletRequest request, @RequestParam(value = "a_downloadLink",required = false) MultipartFile attach) {
        String downloadLink = null;
        String apkLocPath = null;
        String apkFileName = null;
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            if (!prefix.equalsIgnoreCase("apk")) {
                return "redirect:/dev/flatform/app/appversionadd?id=" + appVersion.getAppId() + "&error=error3";
            }

            String apkName = null;

            try {
                apkName = this.appInfoService.getAppInfo(appVersion.getAppId(), (String)null).getAPKName();
            } catch (Exception var16) {
                var16.printStackTrace();
            }

            if (apkName == null || "".equals(apkName)) {
                return "redirect:/dev/flatform/app/appversionadd?id=" + appVersion.getAppId() + "&error=error1";
            }

            apkFileName = apkName + "-" + appVersion.getVersionNo() + ".apk";
            File targetFile = new File(path, apkFileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            try {
                attach.transferTo(targetFile);
            } catch (Exception var15) {
                var15.printStackTrace();
                return "redirect:/dev/flatform/app/appversionadd?id=" + appVersion.getAppId() + "&error=error2";
            }

            downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
            apkLocPath = path + File.separator + apkFileName;
        }

        appVersion.setCreatedBy(((DevUser)session.getAttribute("devUserSession")).getId());
        appVersion.setCreationDate(new Date());
        appVersion.setDownloadLink(downloadLink);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setApkFileName(apkFileName);

        try {
            if (this.appVersionService.appsysadd(appVersion)) {
                return "redirect:/dev/flatform/app/list";
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        return "redirect:/dev/flatform/app/appversionadd?id=" + appVersion.getAppId();
    }
    @RequestMapping(
            value = {"/appinfomodify"},
            method = {RequestMethod.GET}
    )
    public String modifyAppInfo(@RequestParam("id") String id, @RequestParam(value = "error",required = false) String fileUploadError, Model model) {
        AppInfo appInfo = null;

        if (fileUploadError != null && fileUploadError.equals("error1")) {
            fileUploadError = " * APK信息不完整！";
        } else if (fileUploadError != null && fileUploadError.equals("error2")) {
            fileUploadError = " * 上传失败！";
        } else if (fileUploadError != null && fileUploadError.equals("error3")) {
            fileUploadError = " * 上传文件格式不正确！";
        } else if (fileUploadError != null && fileUploadError.equals("error4")) {
            fileUploadError = " * 上传文件过大！";
        }

        try {
            appInfo = this.appInfoService.getAppInfo(Integer.parseInt(id), (String)null);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        model.addAttribute(appInfo);
        model.addAttribute("fileUploadError", fileUploadError);
        return "developer/appinfomodify";
    }

    @RequestMapping(
            value = {"/appversionmodify"},
            method = {RequestMethod.GET}
    )
    public String modifyAppVersion(@RequestParam("vid") String versionId, @RequestParam("aid") String appId, @RequestParam(value = "error",required = false) String fileUploadError, Model model) {
        AppVersion appVersion = null;
        List<AppVersion> appVersionList = null;
        if (fileUploadError != null && fileUploadError.equals("error1")) {
            fileUploadError = " * APK信息不完整！";
        } else if (fileUploadError != null && fileUploadError.equals("error2")) {
            fileUploadError = " * 上传失败！";
        } else if (fileUploadError != null && fileUploadError.equals("error3")) {
            fileUploadError = " * 上传文件格式不正确！";
        }

        try {
            appVersion = this.appVersionService.getAppVersionById(Integer.parseInt(versionId));
            appVersionList = this.appVersionService.getAppVersionList(Integer.parseInt(appId));
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        model.addAttribute(appVersion);
        model.addAttribute("appVersionList", appVersionList);
        model.addAttribute("fileUploadError", fileUploadError);
        return "developer/appversionmodify";
    }

    @RequestMapping(
            value = {"/appversionmodifysave"},
            method = {RequestMethod.POST}
    )
    public String modifyAppVersionSave(AppVersion appVersion, HttpSession session, HttpServletRequest request, @RequestParam(value = "attach",required = false) MultipartFile attach) {
        String downloadLink = null;
        String apkLocPath = null;
        String apkFileName = null;
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");

            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            if (!prefix.equalsIgnoreCase("apk")) {
                return "redirect:/dev/flatform/app/appversionmodify?vid=" + appVersion.getId() + "&aid=" + appVersion.getAppId() + "&error=error3";
            }

            String apkName = null;

            try {
                apkName = this.appInfoService.getAppInfo(appVersion.getAppId(), (String)null).getAPKName();
            } catch (Exception var16) {
                var16.printStackTrace();
            }

            if (apkName == null || "".equals(apkName)) {
                return "redirect:/dev/flatform/app/appversionmodify?vid=" + appVersion.getId() + "&aid=" + appVersion.getAppId() + "&error=error1";
            }

            apkFileName = apkName + "-" + appVersion.getVersionNo() + ".apk";
            File targetFile = new File(path, apkFileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            try {
                attach.transferTo(targetFile);
            } catch (Exception var15) {
                var15.printStackTrace();
                return "redirect:/dev/flatform/app/appversionmodify?vid=" + appVersion.getId() + "&aid=" + appVersion.getAppId() + "&error=error2";
            }

            downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
            apkLocPath = path + File.separator + apkFileName;
        }

        appVersion.setModifyBy(((DevUser)session.getAttribute("devUserSession")).getId());
        appVersion.setModifyDate(new Date());
        appVersion.setDownloadLink(downloadLink);
        appVersion.setApkLocPath(apkLocPath);
        appVersion.setApkFileName(apkFileName);

        try {
            if (this.appVersionService.modify(appVersion)) {
                return "redirect:/dev/flatform/app/list";
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        return "developer/appversionmodify";
    }

    @RequestMapping(
            value = {"/appinfomodifysave"},
            method = {RequestMethod.POST}
    )
    public String modifySave(AppInfo appInfo, HttpSession session, HttpServletRequest request, @RequestParam(value = "attach",required = false) MultipartFile attach) {
        String logoPicPath = null;
        String logoLocPath = null;
        String APKName = appInfo.getAPKName();
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
            String oldFileName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName);
            int filesize = 500000;
            if (attach.getSize() > (long)filesize) {
                return "redirect:/dev/flatform/app/appinfomodify?id=" + appInfo.getId() + "&error=error4";
            }

            if (!prefix.equalsIgnoreCase("jpg") && !prefix.equalsIgnoreCase("png") && !prefix.equalsIgnoreCase("jepg") && !prefix.equalsIgnoreCase("pneg")) {
                return "redirect:/dev/flatform/app/appinfomodify?id=" + appInfo.getId() + "&error=error3";
            }

            String fileName = APKName + ".jpg";
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            try {
                attach.transferTo(targetFile);
            } catch (Exception var16) {
                var16.printStackTrace();
                return "redirect:/dev/flatform/app/appinfomodify?id=" + appInfo.getId() + "&error=error2";
            }

            logoPicPath = request.getContextPath() + "/statics/uploadfiles/" + fileName;
            logoLocPath = path + File.separator + fileName;
        }

        appInfo.setModifyBy(((DevUser)session.getAttribute("devUserSession")).getId());
        appInfo.setModifyDate(new Date());
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setLogoPicPath(logoPicPath);

        try {
            if (this.appInfoService.modify(appInfo)) {
                return "redirect:/dev/flatform/app/list";
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

        return "developer/appinfomodify";
    }


    @RequestMapping(
            value = {"/appview/{id}"},
            method = {RequestMethod.GET}
    )
    public String view(@PathVariable String id, Model model) {
        AppInfo appInfo = null;
        List appVersionList = null;

        try {
            appInfo = this.appInfoService.getAppInfo(Integer.parseInt(id), (String)null);
            appVersionList = this.appVersionService.getAppVersionList(Integer.parseInt(id));
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        model.addAttribute("appVersionList", appVersionList);
        model.addAttribute(appInfo);
        return "developer/appinfoview";
    }



    @RequestMapping({"/delapp.json"})
    @ResponseBody
    public Object delApp(@RequestParam String id) {
        HashMap<String, String> resultMap = new HashMap();
        if (StringUtils.isNullOrEmpty(id)) {
            resultMap.put("delResult", "notexist");
        } else {
            try {
                if (this.appInfoService.appsysdeleteAppById(Integer.parseInt(id))) {
                    resultMap.put("delResult", "true");
                } else {
                    resultMap.put("delResult", "false");
                }
            } catch (NumberFormatException var4) {
                var4.printStackTrace();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return JSONArray.toJSONString((List) resultMap);
    }

}