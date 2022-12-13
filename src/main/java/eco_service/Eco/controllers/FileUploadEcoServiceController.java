package eco_service.Eco.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.UUID;

@Controller
public class FileUploadEcoServiceController {

    @PostMapping("/ecoservice/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Babken_Sargsyan\\Desktop\\Eco\\Eco\\images\\eco_service\\" + fileName)) {
            outputStream.write(file.getBytes());
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
