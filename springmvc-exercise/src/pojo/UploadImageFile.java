package pojo;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-15 15:31
 **/
public class UploadImageFile {
    /**
     * 图片(与upload.jsp中image一致)
     */
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
