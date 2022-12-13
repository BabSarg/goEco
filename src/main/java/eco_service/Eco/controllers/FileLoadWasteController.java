package eco_service.Eco.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class FileLoadWasteController {

    @GetMapping(
            value = "/waste/load/image/{photoId}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("photoId")String photoId) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\Babken_Sargsyan\\Desktop\\Eco\\Eco\\images\\waste\\" + photoId));
        return inputStream.readAllBytes();

    }
}
