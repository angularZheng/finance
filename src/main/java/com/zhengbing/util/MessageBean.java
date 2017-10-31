package com.zhengbing.util;

/**
 * Created by zhengbing on 2017-10-31.
 */
public class MessageBean {

        private String title;
        private String content;
        private String extraInfo;

        public MessageBean() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
        }

        public MessageBean(String title, String content, String extraInfo) {
            this.title = title;
            this.content = content;
            this.extraInfo = extraInfo;
        }
}
