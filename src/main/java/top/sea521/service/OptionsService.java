package top.sea521.service;

import top.sea521.entity.Options;
import top.sea521.entity.custom.OptionsCustom;



public interface OptionsService {
    /**
     *  1 获得基本信息
     */
    OptionsCustom getOptions() throws Exception;

    /**
     * 2 新建基本信息
     */
    void insertOptions(Options options) throws Exception;

    /**
     * 3  更新基本信息
     */
    void updateOptions(Options options) throws Exception;
}
