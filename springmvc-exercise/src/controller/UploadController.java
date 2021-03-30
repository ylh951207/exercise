package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.UploadImageFile;
import tools.RandomUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author: yanglh
 * @ className: UploadController
 * @ description: 上传控制层
 * @ create 2021-03-15 15:48
 **/
@Controller
public class UploadController {

    @RequestMapping("/uploadImage")
    public ModelAndView upload(HttpServletRequest request, UploadImageFile file)throws IOException, IllegalStateException, SecurityException{
        String dictionary = "12345";
        String name = RandomUtils.generateRandomString (dictionary, 8);
        String newFileName = name + ".jpg";
        File newFile = new File(request.getServletContext().getRealPath("/image" ), newFileName);
        newFile.getParentFile().mkdirs();
        file.getImage().transferTo(newFile);
        ModelAndView mav1 = new ModelAndView ("showUploadFile");
        mav1.addObject ("imageName", newFileName);
        return mav1;
    }
}
