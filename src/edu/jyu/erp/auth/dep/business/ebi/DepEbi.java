package edu.jyu.erp.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.auth.dep.vo.DepModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
//切面:spring内置
//切入点:定义了该注解所在的类或接口中的所有方法
//execution(edu.jyu.erp.auth.dep.business.ebi.DepEbi.*(..))
public interface DepEbi extends BaseEbi<DepModel>{
	
}
