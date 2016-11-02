package qunzai.present.been;

import java.util.List;

/**
 * Created by dllo on 16/11/1.
 */
public class SOneHeaderBean {




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
         * next_url : http://api.liwushuo.com/v2/columns?limit=20&offset=20
         */

        private PagingBean paging;
        /**
         * author : 小礼君
         * banner_image_url : http://img03.liwushuo.com/image/161021/94zi25nsv.jpg-w300
         * banner_webp_url : http://img03.liwushuo.com/image/161021/94zi25nsv.jpg?imageView2/2/w/300/q/85/format/webp
         * category : 美护
         * cover_image_url : http://img02.liwushuo.com/image/161021/jcl39ij7c.jpg-w720
         * cover_webp_url : http://img02.liwushuo.com/image/161021/jcl39ij7c.jpg?imageView2/2/w/720/q/85/format/webp
         * created_at : 1472807883
         * description : 口碑超好的护肤品推荐，性价比才是王道
         * id : 100
         * order : 0
         * post_published_at : 1477969200
         * status : 0
         * subtitle :
         * title : 双11-护肤预售会场
         * updated_at : 1477983515
         */

        private List<ColumnsBean> columns;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<ColumnsBean> getColumns() {
            return columns;
        }

        public void setColumns(List<ColumnsBean> columns) {
            this.columns = columns;
        }

        public static class PagingBean {
            private String next_url;

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }

        public static class ColumnsBean {
            private String author;
            private String banner_image_url;
            private String banner_webp_url;
            private String category;
            private String cover_image_url;
            private String cover_webp_url;
            private int created_at;
            private String description;
            private int id;
            private int order;
            private int post_published_at;
            private int status;
            private String subtitle;
            private String title;
            private int updated_at;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getBanner_image_url() {
                return banner_image_url;
            }

            public void setBanner_image_url(String banner_image_url) {
                this.banner_image_url = banner_image_url;
            }

            public String getBanner_webp_url() {
                return banner_webp_url;
            }

            public void setBanner_webp_url(String banner_webp_url) {
                this.banner_webp_url = banner_webp_url;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public String getCover_webp_url() {
                return cover_webp_url;
            }

            public void setCover_webp_url(String cover_webp_url) {
                this.cover_webp_url = cover_webp_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getPost_published_at() {
                return post_published_at;
            }

            public void setPost_published_at(int post_published_at) {
                this.post_published_at = post_published_at;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }
        }
    }
}
