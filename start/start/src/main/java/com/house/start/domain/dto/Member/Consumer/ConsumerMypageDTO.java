package com.house.start.domain.dto.Member.Consumer;

import com.house.start.domain.UploadFile;
import com.house.start.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConsumerMypageDTO {
    private Long id;
    private String name;
    private int likes_size;
    private String uploadFileName;
    private UploadFile uploadFile;

    public ConsumerMypageDTO(Member memeber) {
        this.id = memeber.getId();
        this.name = memeber.getName();
        this.likes_size = memeber.getLikes().size();
        if (memeber.getUploadFile() == null) {
            this.uploadFileName = null;
            this.uploadFile = null;
        }else {
            this.uploadFileName = memeber.getUploadFile().getStoreFileName();
            this.uploadFile = memeber.getUploadFile();
        }
    }
}
