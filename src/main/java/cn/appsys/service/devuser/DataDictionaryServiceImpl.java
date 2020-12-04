package cn.appsys.service.devuser;

import cn.appsys.dao.datadictionary.DataDictionaryMapper;
import cn.appsys.pojo.DataDictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: APP
 * @description:
 * @author: Xiaoxu
 * @create: 2020-11-29 23:11
 **/
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Resource
    private DataDictionaryMapper mapper;

    public DataDictionaryServiceImpl() {
    }

    public List<DataDictionary> getDataDictionaryList(String typeCode) throws Exception {
        return this.mapper.getDataDictionaryList(typeCode);
    }
}
