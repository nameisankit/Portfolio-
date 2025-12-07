package in.ankitparmar.portfolio.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class FileUploadService {

    private final Cloudinary cloudinary;

    public FileUploadService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) return null;

        Map result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "portfolio/profile",
                        "resource_type", "image"
                )
        );
        return (String) result.get("secure_url");
    }

   public String uploadResume(MultipartFile file) throws Exception {
    if (file == null || file.isEmpty()) return null;

    Map result = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap(
                    "folder", "portfolio/resume",
                    "resource_type", "raw",
                    "public_id", "Ankit_Parmar_Resume",
                    "overwrite", true,
                    "format", "pdf"
            )
    );
    return (String) result.get("secure_url");
}


        public String uploadAboutImage(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) return null;

        Map result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "portfolio/about",
                        "resource_type", "image"
                )
        );
        return (String) result.get("secure_url");
    }

}
