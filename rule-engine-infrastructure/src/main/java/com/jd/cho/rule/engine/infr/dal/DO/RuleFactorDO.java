package com.jd.cho.rule.engine.infr.dal.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则因子表
 *
 * @TableName rule_factor
 */
@TableName(value = "rule_factor")
@Data
public class RuleFactorDO implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 因子编码
     */
    private String factorCode;

    /**
     * 因子全编码
     */
    private String factorFullCode;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 因子类型（日期、数值、集合、布尔、文本）
     */
    private String factorType;

    /**
     * 常量类型（Input:输入，Enum:枚举，Script:脚本）
     */
    private String constantType;

    /**
     * 常量值
     */
    private String constantValue;

    /**
     * 脚本参数
     */
    private String factorScriptParam;

    /**
     * 脚本代码
     */
    private String factorScript;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（0:否，1:是）
     */
    private Integer status;

    /**
     * 是否删除 1-否，0-是
     */
    private Integer yn;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 租户
     */
    private String tenant;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RuleFactorDO other = (RuleFactorDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getFactorCode() == null ? other.getFactorCode() == null : this.getFactorCode().equals(other.getFactorCode()))
                && (this.getFactorFullCode() == null ? other.getFactorFullCode() == null : this.getFactorFullCode().equals(other.getFactorFullCode()))
                && (this.getFactorName() == null ? other.getFactorName() == null : this.getFactorName().equals(other.getFactorName()))
                && (this.getGroupCode() == null ? other.getGroupCode() == null : this.getGroupCode().equals(other.getGroupCode()))
                && (this.getFactorType() == null ? other.getFactorType() == null : this.getFactorType().equals(other.getFactorType()))
                && (this.getConstantType() == null ? other.getConstantType() == null : this.getConstantType().equals(other.getConstantType()))
                && (this.getConstantValue() == null ? other.getConstantValue() == null : this.getConstantValue().equals(other.getConstantValue()))
                && (this.getFactorScriptParam() == null ? other.getFactorScriptParam() == null : this.getFactorScriptParam().equals(other.getFactorScriptParam()))
                && (this.getFactorScript() == null ? other.getFactorScript() == null : this.getFactorScript().equals(other.getFactorScript()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getYn() == null ? other.getYn() == null : this.getYn().equals(other.getYn()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getModifyTime() == null ? other.getModifyTime() == null : this.getModifyTime().equals(other.getModifyTime()))
                && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
                && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
                && (this.getTenant() == null ? other.getTenant() == null : this.getTenant().equals(other.getTenant()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFactorCode() == null) ? 0 : getFactorCode().hashCode());
        result = prime * result + ((getFactorFullCode() == null) ? 0 : getFactorFullCode().hashCode());
        result = prime * result + ((getFactorName() == null) ? 0 : getFactorName().hashCode());
        result = prime * result + ((getGroupCode() == null) ? 0 : getGroupCode().hashCode());
        result = prime * result + ((getFactorType() == null) ? 0 : getFactorType().hashCode());
        result = prime * result + ((getConstantType() == null) ? 0 : getConstantType().hashCode());
        result = prime * result + ((getConstantValue() == null) ? 0 : getConstantValue().hashCode());
        result = prime * result + ((getFactorScriptParam() == null) ? 0 : getFactorScriptParam().hashCode());
        result = prime * result + ((getFactorScript() == null) ? 0 : getFactorScript().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getYn() == null) ? 0 : getYn().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getTenant() == null) ? 0 : getTenant().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", factorCode=").append(factorCode);
        sb.append(", factorFullCode=").append(factorFullCode);
        sb.append(", factorName=").append(factorName);
        sb.append(", groupCode=").append(groupCode);
        sb.append(", factorType=").append(factorType);
        sb.append(", constantType=").append(constantType);
        sb.append(", constantValue=").append(constantValue);
        sb.append(", factorScriptParam=").append(factorScriptParam);
        sb.append(", factorScript=").append(factorScript);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", yn=").append(yn);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", creator=").append(creator);
        sb.append(", modifier=").append(modifier);
        sb.append(", tenant=").append(tenant);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}