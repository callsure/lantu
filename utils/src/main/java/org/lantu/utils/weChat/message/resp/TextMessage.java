package org.lantu.utils.weChat.message.resp;

/**
 * Created by runshu.lin on 2019/3/9.
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
