package qunzai.present.been;

import java.util.List;

/**
 * Created by dllo on 16/11/4.
 */
public class FindTitleBean {

    /**
     * code : 200
     * data : {"hot_words":["笔记本","香水","零食","围巾","生日","情侣","手机壳","保温杯"],"placeholder":"选份走心好礼送给Ta"}
     * message : OK
     */

    private int code;
    /**
     * hot_words : ["笔记本","香水","零食","围巾","生日","情侣","手机壳","保温杯"]
     * placeholder : 选份走心好礼送给Ta
     */

    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private String placeholder;
        private List<String> hot_words;

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public List<String> getHot_words() {
            return hot_words;
        }

        public void setHot_words(List<String> hot_words) {
            this.hot_words = hot_words;
        }
    }
}
