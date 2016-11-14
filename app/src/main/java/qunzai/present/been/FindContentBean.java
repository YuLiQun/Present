package qunzai.present.been;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.util.List;

/**
 * Created by dllo on 16/11/14.
 */
public class FindContentBean {


    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    /**
     * code : 200
     * data : {"words":[{"cnt":1,"word":"婚礼d"},{"cnt":2,"word":"三D笔"},{"cnt":4,"word":"杯子D"},{"cnt":2,"word":"宝宝成长D"},{"cnt":296,"word":"3D"},{"cnt":4,"word":"3d"},{"cnt":1,"word":"3D"},{"cnt":10,"word":"3D金属"},{"cnt":21,"word":"3D打印"},{"cnt":5,"word":"3d手链"},{"cnt":3,"word":"3D打印照片"},{"cnt":4,"word":"3D立体树"},{"cnt":4,"word":"3D灯"},{"cnt":8,"word":"3D立体"},{"cnt":5,"word":"3D手机眼睛"},{"cnt":3,"word":"3d打印笔"},{"cnt":5,"word":"3D手机眼镜"},{"cnt":1,"word":"3D壁画"},{"cnt":7,"word":"3D打印笔"},{"cnt":2,"word":"3D涂鸦打印机"},{"cnt":3,"word":"3D涂鸦打印笔"},{"cnt":3,"word":"3D金属拼图"},{"cnt":2,"word":"木3d模型"},{"cnt":4,"word":"3D泡沫护肤品"},{"cnt":2,"word":"3D玫瑰泡沫护肤品"}]}
     * message : OK
     */

    private int code;
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
        /**
         * cnt : 1
         * word : 婚礼d
         */

        private List<WordsBean> words;

        public List<WordsBean> getWords() {
            return words;
        }

        public void setWords(List<WordsBean> words) {
            this.words = words;
        }

        public static class WordsBean {
            private int cnt;
            private String word;

            public int getCnt() {
                return cnt;
            }

            public void setCnt(int cnt) {
                this.cnt = cnt;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }
        }
    }
}
