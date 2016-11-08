package qunzai.present.been;

import java.util.List;

/**
 * Created by dllo on 16/11/8.
 */
public class HDCommentBean {

    /**
     * code : 200
     * data : {"comments":[{"content":"好看","created_at":1478563634,"id":518916,"images":[],"item_id":1073228,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/160916/80e3e8ad2_a-w180","can_mobile_login":true,"guest_uuid":null,"id":7234974,"nickname":"你好有故事的人","role":0}},{"content":"模特的嘴唇看起来好奇怪...","created_at":1477542296,"id":517110,"images":[],"item_id":1073228,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/151231/0c5abc599_a-w180","can_mobile_login":false,"guest_uuid":null,"id":5892758,"nickname":"      -","role":0}},{"content":"很好看","created_at":1477537268,"id":517094,"images":[],"item_id":1073228,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/151212/ef666b56a_a-w180","can_mobile_login":false,"guest_uuid":null,"id":1231697,"nickname":"何必十八","role":0}}],"paging":{"next_url":"http://api.liwushuo.com/v2/items/1073228/comments?limit=20&offset=20"}}
     * message : OK
     */

    private int code;
    /**
     * comments : [{"content":"好看","created_at":1478563634,"id":518916,"images":[],"item_id":1073228,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/160916/80e3e8ad2_a-w180","can_mobile_login":true,"guest_uuid":null,"id":7234974,"nickname":"你好有故事的人","role":0}},{"content":"模特的嘴唇看起来好奇怪...","created_at":1477542296,"id":517110,"images":[],"item_id":1073228,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/151231/0c5abc599_a-w180","can_mobile_login":false,"guest_uuid":null,"id":5892758,"nickname":"      -","role":0}},{"content":"很好看","created_at":1477537268,"id":517094,"images":[],"item_id":1073228,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/151212/ef666b56a_a-w180","can_mobile_login":false,"guest_uuid":null,"id":1231697,"nickname":"何必十八","role":0}}]
     * paging : {"next_url":"http://api.liwushuo.com/v2/items/1073228/comments?limit=20&offset=20"}
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
        /**
         * next_url : http://api.liwushuo.com/v2/items/1073228/comments?limit=20&offset=20
         */

        private PagingBean paging;
        /**
         * content : 好看
         * created_at : 1478563634
         * id : 518916
         * images : []
         * item_id : 1073228
         * reply_to_id : null
         * show : true
         * status : 1
         * user : {"avatar_url":"http://img02.liwushuo.com/avatar/160916/80e3e8ad2_a-w180","can_mobile_login":true,"guest_uuid":null,"id":7234974,"nickname":"你好有故事的人","role":0}
         */

        private List<CommentsBean> comments;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
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

        public static class CommentsBean {
            private String content;
            private int created_at;
            private int id;
            private int item_id;
            private Object reply_to_id;
            private boolean show;
            private int status;
            /**
             * avatar_url : http://img02.liwushuo.com/avatar/160916/80e3e8ad2_a-w180
             * can_mobile_login : true
             * guest_uuid : null
             * id : 7234974
             * nickname : 你好有故事的人
             * role : 0
             */

            private UserBean user;
            private List<?> images;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public Object getReply_to_id() {
                return reply_to_id;
            }

            public void setReply_to_id(Object reply_to_id) {
                this.reply_to_id = reply_to_id;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }

            public static class UserBean {
                private String avatar_url;
                private boolean can_mobile_login;
                private Object guest_uuid;
                private int id;
                private String nickname;
                private int role;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public Object getGuest_uuid() {
                    return guest_uuid;
                }

                public void setGuest_uuid(Object guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }
        }
    }
}
