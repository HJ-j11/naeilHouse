package com.house.start.domain.dto.Member.Consumer;

import com.house.start.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConsumerMypageDTO {
    private String name;
    private int likes_size;
    private String uploadFileName;

    public ConsumerMypageDTO(Member memeber) {
        this.name = memeber.getName();
        this.likes_size = memeber.getLikes().size();
        if (memeber.getUploadFile() == null) {
            this.uploadFileName = null;
        }else{
            this.uploadFileName = memeber.getUploadFile().getStoreFileName();
        }
    }
}
