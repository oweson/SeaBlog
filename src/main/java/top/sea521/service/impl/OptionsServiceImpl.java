package top.sea521.service.impl;

import top.sea521.entity.Options;
import top.sea521.entity.custom.OptionsCustom;
import top.sea521.mapper.OptionsMapper;
import top.sea521.mapper.custom.OptionsMapperCustom;
import top.sea521.service.OptionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsMapperCustom optionsMapperCustom;

    @Autowired
    private OptionsMapper optionsMapper;

    /**
     * 1 查
     */
    @Override
    public OptionsCustom getOptions() throws Exception {
        Options options = optionsMapperCustom.getOptions();
        OptionsCustom optionsCustom = new OptionsCustom();
        if (options != null) {
            BeanUtils.copyProperties(options, optionsCustom);
        }
        return optionsCustom;
    }

    /**
     * 2 插
     */
    @Override
    public void insertOptions(Options options) throws Exception {
        optionsMapper.insertSelective(options);
    }

    /**
     * 3 更新
     */
    @Override
    public void updateOptions(Options options) throws Exception {
        optionsMapper.updateByPrimaryKeySelective(options);
    }
}
