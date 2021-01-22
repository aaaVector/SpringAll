/**
 * <p>Title: litis</p>
 * <p>Description: redis的全方位开发运维平台</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * @author Bryan.Zhang
 * @email 47483522@qq.com
 * @Date 2017-11-28
 */
package com.example.liteflow.nodes;

import com.yomahub.liteflow.core.NodeCondComponent;
import org.springframework.stereotype.Component;

@Component("cond")
public class CondComponent extends NodeCondComponent {

	@Override
	public String processCond() throws Exception {
		return "b";
	}
}
