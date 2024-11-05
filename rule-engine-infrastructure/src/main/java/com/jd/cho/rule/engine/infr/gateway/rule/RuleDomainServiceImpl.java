package com.jd.cho.rule.engine.infr.gateway.rule;

import com.jd.cho.rule.engine.domain.model.RuleDef;
import com.jd.cho.rule.engine.domain.rule.RuleDomainService;
import com.jd.cho.rule.engine.infr.dal.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenhonghao12
 * @version 1.0
 */
@Service
public class RuleDomainServiceImpl implements RuleDomainService {

    @Resource
    private CustomerMapper customerMapper;


    @Override
    public List<String> allScenes() {
        return null;
    }

    @Override
    public long addScene() {
        return 0;
    }

    @Override
    public List<RuleDef> ruleDefQuery(List<String> ruleCodes) {
        return null;
    }
}
