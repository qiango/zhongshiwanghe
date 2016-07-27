package com.hongzhi.zswh.util.basic.dictionaryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by taylor on 7/27/16.
 * twitter: @taylorwang789
 */
@Service("dictionaryservice")
public class DictionaryService {
    @Autowired
    private  DictionaryDao dao ;

    public List<Dictionary>  dictionaries() {
        return  dao.listAllDictionary();
    }

}
