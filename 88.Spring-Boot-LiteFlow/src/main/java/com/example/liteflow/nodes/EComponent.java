/**
 * <p>Title: liteflow</p>
 * <p>Description: 轻量级的组件式流程框架</p>
 * @author Bryan.Zhang
 * @email weenyc31@163.com
 * @Date 2020/4/1
 */
package com.example.liteflow.nodes;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("e")
public class EComponent extends NodeComponent {

	@Override
	public void process() {
		try {
			Thread.sleep(120L);
			System.out.println("E:" + this.getSlot().getOutput("a"));
			this.getSlot().setOutput(this.getNodeId(), "E component output");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Eomponent executed!");

	}

}
