package cn.appsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: APP
 * @description: APP类别实体类
 * @author: Xiaoxu
 * @create: 2020-11-29 19:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppCategory implements Serializable {
    private Integer id;//主键id
    private String categoryCode;//分类编码
    private String categoryName;//分类名称
    private Integer parentId;//父节点id
    private Integer createdBy;//创建者
    private Date creationDate;//创建时间
    private Integer modifyBy;//更新者
    private Date modifyDate;//更新时间
}