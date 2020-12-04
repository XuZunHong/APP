package cn.appsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: APP
 * @description: APP版本信息实体类
 * @author: Xiaoxu
 * @create: 2020-11-29 19:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppVersion implements Serializable {
    private Integer id;//主键id
    private Integer appId;//app的id号
    private String versionNo;//版本号
    private String versionInfo;//版本介绍
    private Integer publishStatus;//发布状态
    private String downloadLink;//下载链接
    private BigDecimal versionSize;//版本大小
    private Integer createdBy;//创建者
    private Date creationDate;//创建时间
    private Integer modifyBy;//更新者
    private Date modifyDate;//更新时间
    private String apkLocPath;//apk的服务器存储路径
    private String appName;//app的名字
    private String publishStatusName;//app状态名
    private String apkFileName;//apk文档的文件名

}