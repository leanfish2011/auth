package com.tim.auth.ao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author：tim
 * @date：20-4-12 上午9:46
 * @description：github登录进入的用户信息
 */
@Data
public class GithubUser {

  @JsonProperty(value = "login")
  private String login;

  @JsonProperty(value = "id")
  private Integer id;

  @JsonProperty(value = "node_id")
  private String nodeId;

  @JsonProperty(value = "avatar_url")
  private String avatarUrl;

  @JsonProperty(value = "gravatar_id")
  private String gravatarId;

  @JsonProperty(value = "url")
  private String url;

  @JsonProperty(value = "html_url")
  private String htmlUrl;

  @JsonProperty(value = "followers_url")
  private String followersUrl;

  @JsonProperty(value = "following_url")
  private String followingUrl;

  @JsonProperty(value = "gists_url")
  private String gistsUrl;

  @JsonProperty(value = "starred_url")
  private String starredUrl;

  @JsonProperty(value = "subscriptions_url")
  private String subscriptionsUrl;

  @JsonProperty(value = "organizations_url")
  private String organizationsUrl;

  @JsonProperty(value = "repos_url")
  private String reposUrl;

  @JsonProperty(value = "events_url")
  private String eventsUrl;

  @JsonProperty(value = "received_events_url")
  private String receivedEventsUrl;

  @JsonProperty(value = "type")
  private String type;

  @JsonProperty(value = "site_admin")
  private Boolean siteAdmin;

  @JsonProperty(value = "name")
  private String name;

  @JsonProperty(value = "company")
  private String company;

  @JsonProperty(value = "blog")
  private String blog;

  @JsonProperty(value = "location")
  private String location;

  @JsonProperty(value = "email")
  private String email;

  @JsonProperty(value = "hireable")
  private String hireable;

  @JsonProperty(value = "bio")
  private String bio;

  @JsonProperty(value = "public_repos")
  private Integer publicRepos;

  @JsonProperty(value = "public_gists")
  private Integer publicGists;

  @JsonProperty(value = "followers")
  private Integer followers;

  @JsonProperty(value = "following")
  private Integer following;

  @JsonProperty(value = "created_at")
  private String createdAt;

  @JsonProperty(value = "updated_at")
  private String updatedAt;
}
