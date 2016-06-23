package com.hongzhi.zswh.app_v3.me.entity;

/**   Twitter : @taylorwang789 
 * Creat time : May 5, 2016    4:26:16 PM
 */
public class FollowersEntity {
    private String  follower_id ;
    private String  user_image ;
    private String  user_name ;
    private String  follow ;
    
	public String getFollower_id() {
		return follower_id;
	}
	public void setFollower_id(String follower_id) {
		this.follower_id = follower_id;
	}
	public String getUser_image() {
		return user_image;
	}
	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getFollow() {
		return follow;
	}
	public void setFollow(String follow) {
		this.follow = follow;
	}
    
}
