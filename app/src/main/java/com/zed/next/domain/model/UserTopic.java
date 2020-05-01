package com.zed.next.domain.model;

public class UserTopic {
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_type() {
        return topic_type;
    }

    public void setTopic_type(String topic_type) {
        this.topic_type = topic_type;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public String getTopic_status() {
        return topic_status;
    }

    public void setTopic_status(String topic_status) {
        this.topic_status = topic_status;
    }

    public String getTopic_poster() {
        return topic_poster;
    }

    public void setTopic_poster(String topic_poster) {
        this.topic_poster = topic_poster;
    }

    public TopicDoneStat getTopicDoneStat() {
        return topicDoneStat;
    }

    public void setTopicDoneStat(TopicDoneStat topicDoneStat) {
        this.topicDoneStat = topicDoneStat;
    }

    public TopicNextStat getTopicNextStat() {
        return topicNextStat;
    }

    public void setTopicNextStat(TopicNextStat topicNextStat) {
        this.topicNextStat = topicNextStat;
    }


    String _id;
    String user_id;
    String topic_id;
    String topic_type;
    String topic_title;
    String topic_status;
    String topic_poster;
    TopicDoneStat topicDoneStat;
    TopicNextStat topicNextStat;

}
