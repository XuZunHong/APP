package cn.appsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: APP
 * @description:
 * @author: Xiaoxu
 * @create: 2020-11-29 22:39
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataDictionary implements Serializable {
    private Integer id;
    private String typeCode;
    private String typeName;
    private Integer valueId;
    private String valueName;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
}