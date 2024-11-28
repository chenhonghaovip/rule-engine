package com.jd.cho.rule.engine.common.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.google.common.collect.Lists;
import com.jd.cho.rule.engine.common.dict.Dict;
import com.jd.cho.rule.engine.common.util.AtomicLoginUserUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 对租户id进行增强处理，只对规则引擎涉及的表进行过滤
 *
 * @author chenhonghao12
 * @version 1.0
 */
@Configuration
public class TenantConfig {

    private static final List<String> TABLE_NAME = Lists.newArrayList("rule_pack", "rule_factor", "rule_factor_group", "rule_scene", "rule_scene_action");

    @Bean
    public MybatisPlusInterceptor tenantInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            /**
             * 获取租户id
             * @return 租户id
             */
            @Override
            public Expression getTenantId() {
                String tenant = AtomicLoginUserUtil.getLoginUserInfo().getTenant();
                if (StringUtils.isNotBlank(tenant)) {
                    return new StringValue(tenant);
                }
                return new NullValue();
            }

            /**
             * 获取多租户标识的字段名（重写了该方法，便于进行实际业务的个性化字段定义，源码中其实也是默认为tenent_id）
             * @return 租户字段code
             */
            @Override
            public String getTenantIdColumn() {
                return Dict.TENANT;
            }

            /**
             * 过滤不需要根据tenant隔离的表
             * @param tableName 表名
             * @return 是否需要拼多租户条件
             */
            @Override
            public boolean ignoreTable(String tableName) {
                return !TABLE_NAME.contains(tableName);
            }
        }));
        return interceptor;
    }
}
