package com.project.questapp.response;

import com.project.questapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    private Long id;
    private Long userId;
    private Long postId;

    public LikeResponse(Like entity){
        this.id = id;
        this.userId= entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
