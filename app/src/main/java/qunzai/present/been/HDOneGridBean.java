package qunzai.present.been;

import java.util.List;

/**
 * Created by dllo on 16/11/7.
 */
public class HDOneGridBean {




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
         * ad_monitors : null
         * brand_id : null
         * brand_order : null
         * category_id : 13
         * cover_image_url : http://img03.liwushuo.com/image/161030/uwh2ot4cr_w.jpg-w720
         * cover_webp_url : http://img03.liwushuo.com/image/161030/uwh2ot4cr_w.jpg?imageView2/2/w/720/q/85/format/webp
         * created_at : 1477833470
         * description : 超级韩范儿的一款宽松毛衣，设计上迎合了前后不对称、侧边开衩两大时尚元素，上身效果令身材更显纤细，高领的款式保暖又时髦，质感十足的低饱和度棕色也是毛衣的亮点之一，既然毫无槽点，快入手一件吧。
         * editor_id : 1134
         * favorites_count : 938
         * id : 1073583
         * image_urls : ["http://img03.liwushuo.com/image/161028/ggak7p3qb.jpg-w720","http://img03.liwushuo.com/image/161028/obitlc8e4.jpg-w720","http://img03.liwushuo.com/image/161028/wqqqzvizi.jpg-w720","http://img01.liwushuo.com/image/161028/ts621rkbf.jpg-w720","http://img01.liwushuo.com/image/161028/flh9ywxvm.jpg-w720","http://img03.liwushuo.com/image/161030/uwh2ot4cr_w.jpg-w720"]
         * keywords :
         * name : 韩都衣舍·宽松高领侧开叉毛衣
         * post_ids : []
         * price : 168.00
         * purchase_id : 538849767088
         * purchase_shop_id : 58501945
         * purchase_status : 1
         * purchase_type : 2
         * purchase_url : http://s.click.taobao.com/t?e=m%3D2%26s%3DKLo8ol%2F4TFocQipKwQzePOeEDrYVVa64yK8Cckff7TVRAdhuF14FMXeM5cyCd%2FHJJ1gyddu7kN%2BtgmtnxDX9deVMA5qBABUs5mPg1WiM1P5OS0OzHKBZzQIomwaXGXUs78FqzS29vh5nPjZ5WWqolN%2FWWjML5JdM90WyHry0om2eywYYT8u7ldd0XxLsMlgIU5vUpG11wMPPZMUUR31Kpg%3D%3D
         * short_description :
         * subcategory_id : 178
         * updated_at : 1477833470
         * url : liwushuo:///page?type=item&page_action=navigation&item_id=1073583
         * webp_urls : ["http://img03.liwushuo.com/image/161028/ggak7p3qb.jpg?imageView2/2/w/720/q/85/format/webp","http://img03.liwushuo.com/image/161028/obitlc8e4.jpg?imageView2/2/w/720/q/85/format/webp","http://img03.liwushuo.com/image/161028/wqqqzvizi.jpg?imageView2/2/w/720/q/85/format/webp","http://img01.liwushuo.com/image/161028/ts621rkbf.jpg?imageView2/2/w/720/q/85/format/webp","http://img01.liwushuo.com/image/161028/flh9ywxvm.jpg?imageView2/2/w/720/q/85/format/webp","http://img03.liwushuo.com/image/161030/uwh2ot4cr_w.jpg?imageView2/2/w/720/q/85/format/webp"]
         */

        private List<RecommendItemsBean> recommend_items;
        /**
         * ad_monitors : null
         * content_type : 1
         * content_url : http://www.liwushuo.com/posts/1043420/content
         * cover_image_url : http://img02.liwushuo.com/image/160518/geu5xgn5f.jpg-w720
         * cover_webp_url : http://img02.liwushuo.com/image/160518/geu5xgn5f.jpg?imageView2/2/w/720/q/85/format/webp
         * created_at : 1463616000
         * editor_id : 1023
         * feature_list : []
         * id : 1043420
         * label_ids : []
         * likes_count : 32053
         * published_at : 1463616000
         * share_msg : 有了好的食材，还需要好的烹煮，一口好锅，便是您煎、炒、蒸、煮、涮、炸的利器！当你尝试为自己亦或是家人做一顿美餐时，缔造的不仅是味觉的快乐，更是幸福生活的美好，美好的事物总是能给我们美好的享受，这些设计走心的锅具让人超有食欲，超有爱。看到如此高颜值的锅，还在乎里面的美食吗，让我们开动吧！！！
         * short_title : 锅具
         * status : 0
         * template :
         * title : 第15期 | 吃货的福利，好锅让你尽享美食的天堂
         * updated_at : 1463481464
         * url : liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1043420
         */

        private List<RecommendPostsBean> recommend_posts;

        public List<RecommendItemsBean> getRecommend_items() {
            return recommend_items;
        }

        public void setRecommend_items(List<RecommendItemsBean> recommend_items) {
            this.recommend_items = recommend_items;
        }

        public List<RecommendPostsBean> getRecommend_posts() {
            return recommend_posts;
        }

        public void setRecommend_posts(List<RecommendPostsBean> recommend_posts) {
            this.recommend_posts = recommend_posts;
        }

        public static class RecommendItemsBean {
            private Object ad_monitors;
            private Object brand_id;
            private Object brand_order;
            private int category_id;
            private String cover_image_url;
            private String cover_webp_url;
            private int created_at;
            private String description;
            private int editor_id;
            private int favorites_count;
            private int id;
            private String keywords;
            private String name;
            private String price;
            private String purchase_id;
            private String purchase_shop_id;
            private int purchase_status;
            private int purchase_type;
            private String purchase_url;
            private String short_description;
            private int subcategory_id;
            private int updated_at;
            private String url;
            private List<String> image_urls;
            private List<?> post_ids;
            private List<String> webp_urls;

            public Object getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(Object ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public Object getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(Object brand_id) {
                this.brand_id = brand_id;
            }

            public Object getBrand_order() {
                return brand_order;
            }

            public void setBrand_order(Object brand_order) {
                this.brand_order = brand_order;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
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

            public int getEditor_id() {
                return editor_id;
            }

            public void setEditor_id(int editor_id) {
                this.editor_id = editor_id;
            }

            public int getFavorites_count() {
                return favorites_count;
            }

            public void setFavorites_count(int favorites_count) {
                this.favorites_count = favorites_count;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPurchase_id() {
                return purchase_id;
            }

            public void setPurchase_id(String purchase_id) {
                this.purchase_id = purchase_id;
            }

            public String getPurchase_shop_id() {
                return purchase_shop_id;
            }

            public void setPurchase_shop_id(String purchase_shop_id) {
                this.purchase_shop_id = purchase_shop_id;
            }

            public int getPurchase_status() {
                return purchase_status;
            }

            public void setPurchase_status(int purchase_status) {
                this.purchase_status = purchase_status;
            }

            public int getPurchase_type() {
                return purchase_type;
            }

            public void setPurchase_type(int purchase_type) {
                this.purchase_type = purchase_type;
            }

            public String getPurchase_url() {
                return purchase_url;
            }

            public void setPurchase_url(String purchase_url) {
                this.purchase_url = purchase_url;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public int getSubcategory_id() {
                return subcategory_id;
            }

            public void setSubcategory_id(int subcategory_id) {
                this.subcategory_id = subcategory_id;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<String> getImage_urls() {
                return image_urls;
            }

            public void setImage_urls(List<String> image_urls) {
                this.image_urls = image_urls;
            }

            public List<?> getPost_ids() {
                return post_ids;
            }

            public void setPost_ids(List<?> post_ids) {
                this.post_ids = post_ids;
            }

            public List<String> getWebp_urls() {
                return webp_urls;
            }

            public void setWebp_urls(List<String> webp_urls) {
                this.webp_urls = webp_urls;
            }
        }

        public static class RecommendPostsBean {
            private Object ad_monitors;
            private int content_type;
            private String content_url;
            private String cover_image_url;
            private String cover_webp_url;
            private int created_at;
            private int editor_id;
            private int id;
            private int likes_count;
            private int published_at;
            private String share_msg;
            private String short_title;
            private int status;
            private String template;
            private String title;
            private int updated_at;
            private String url;
            private List<?> feature_list;
            private List<?> label_ids;

            public Object getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(Object ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public int getContent_type() {
                return content_type;
            }

            public void setContent_type(int content_type) {
                this.content_type = content_type;
            }

            public String getContent_url() {
                return content_url;
            }

            public void setContent_url(String content_url) {
                this.content_url = content_url;
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

            public int getEditor_id() {
                return editor_id;
            }

            public void setEditor_id(int editor_id) {
                this.editor_id = editor_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public int getPublished_at() {
                return published_at;
            }

            public void setPublished_at(int published_at) {
                this.published_at = published_at;
            }

            public String getShare_msg() {
                return share_msg;
            }

            public void setShare_msg(String share_msg) {
                this.share_msg = share_msg;
            }

            public String getShort_title() {
                return short_title;
            }

            public void setShort_title(String short_title) {
                this.short_title = short_title;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<?> getFeature_list() {
                return feature_list;
            }

            public void setFeature_list(List<?> feature_list) {
                this.feature_list = feature_list;
            }

            public List<?> getLabel_ids() {
                return label_ids;
            }

            public void setLabel_ids(List<?> label_ids) {
                this.label_ids = label_ids;
            }
        }
    }
}
