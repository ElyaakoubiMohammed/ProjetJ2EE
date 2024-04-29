package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CommentId;
    private String Titre;
    private String FeedBack;
    private String CommentType;

    public Comment(Long commentId, String titre, String feedBack, String commentType) {
        CommentId = commentId;
        Titre = titre;
        FeedBack = feedBack;
        CommentType = commentType;
    }
    public Comment()
    {

    }

    public Long getCommentId() {
        return CommentId;
    }

    public void setCommentId(Long commentId) {
        CommentId = commentId;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getFeedBack() {
        return FeedBack;
    }

    public void setFeedBack(String feedBack) {
        FeedBack = feedBack;
    }

    public String getCommentType() {
        return CommentType;
    }

    public void setCommentType(String commentType) {
        CommentType = commentType;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "CommentId=" + CommentId +
                ", Titre='" + Titre + '\'' +
                ", FeedBack='" + FeedBack + '\'' +
                ", CommentType='" + CommentType + '\'' +
                '}';
    }
}
