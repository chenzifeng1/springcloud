package com.czf.program.programpractice.controller;

import com.alibaba.fastjson.JSONObject;
import com.czf.program.programpractice.entity.User;
import com.czf.program.programpractice.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.controller
 * @ClassName: FileController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/23 18:27
 * @Version: 1.0
 */
@RequestMapping("/file")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);


    @PostMapping("/upload")
    public JSONObject uploadFile(MultipartFile file, HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = JsonUtils.request2Json(httpServletRequest);
        /*这里需要把文件名存到数据库的对应字段中去，存储对象可以从HttpServletRequest中获取
        用户标识尽量不要从表单中获取，因为表单数据可信度并不高，容易被伪造，我们可以从Session中获取。
        这里需要前端将User的信息封装到Session的属性中*/
        User user = (User) httpServletRequest.getSession().getAttribute("User");
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "/static/upload");
            logger.info("upload:" + upload);
            file.transferTo(new File(upload + "/" + file.getOriginalFilename()));

        } catch (IllegalStateException e) {
            logger.error("文件或用户信息错误", e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
