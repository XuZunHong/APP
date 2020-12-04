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
 * @description: APP信息实体类
 * @author: Xiaoxu
 * @create: 2020-11-29 17:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AppInfo implements Serializable {
    private Integer id;//主键id
    private String softwareName;//软件名字√
    private String APKName;//APK名称√
    private String supportROM;//支持ROM
    private String interfaceLanguage;//界面语言
    private Date updateDate;//更新时间
    private BigDecimal softwareSize;//软件大小√
    private Integer devId;//开发者id
    private String appInfo;//APP应用简介
    private Integer status;//状态
    private Date onSaleDate;//上架时间
    private Date offSaleDate;//下架时间
    private Integer categoryLevel3;//三级分类
    private Integer downloads;//下载次数
    private Integer flatformId;//平台id√
    private Integer createdBy;//创建者
    private Date creationDate;//创建时间
    private Integer modifyBy;//更新者
    private Date modifyDate;//更新时间
    private String statusName;//状态名字（data_dictionary中的valueName）
    private String flatformName;//所属平台名字√
    private String categoryLevel3Name;//三级分类类型名称
    private String devName;//开发者名称
    private Integer categoryLevel1;//一级分类
    private Integer categoryLevel2;//二级分类
    private String categoryLevel1Name;//一级分类类型名称
    private String categoryLevel2Name;//二级分类类型名称
    private String logoPicPath;//LOGO图片url路径
    private String logoLocPath;//LOGO图片的服务器存储路径
    private Integer versionId;//最新版本id
    private String versionNo;//版本号
}