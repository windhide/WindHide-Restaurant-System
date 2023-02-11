package com.windhide.employee.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static com.alibaba.nacos.client.utils.EnvUtil.LOGGER;

@RestController
public class UploadController {
    @Value("${file-save-path}")
    private String fileSavePath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @RequestMapping("/upload/{childPath}")
    public String upload(@RequestParam("file") MultipartFile file, @PathVariable("childPath") String childPath) {
        String tempPath = fileSavePath + childPath + "/";
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try{
            if (!file.isEmpty()){
                // 打印文件基础信息
                LOGGER.info("文件名字 == >>"+file.getName());
                LOGGER.info("文件上传时的名字 == >>"+file.getOriginalFilename());
                LOGGER.info("文件类型 == >>"+file.getContentType());
                LOGGER.info("文件大小 == >>"+file.getSize());
                // 文件输出地址
                new File(tempPath).mkdirs();
                File writeFile = new File(tempPath, newName); // 文件上传的路径是上面自定义的，uploadService.getFileName()是按照时间编辑的名字
                file.transferTo(writeFile);
            }
            return newName;
        } catch (Exception e){
            e.printStackTrace();
            return "false" ;
        }
    }

    /**
     * 文件删除
     */
    @RequestMapping("/uploadRemove/{childPath}/{imgName}")
    public String uploadDelete (@PathVariable("childPath") String childPath,@PathVariable("imgName") String imgName){
        String tempPath = fileSavePath + childPath + "/";
        File file = new File(tempPath+imgName);
        if (file == null || !file.exists()) {
            LOGGER.info("没有这个文件 == >> "+tempPath+imgName);
            return "没有名字为"+imgName+"的文件";
        }
        //获取目录下子文件
        File[] files = file.listFiles();
        file.delete();
        System.out.println("已删除目录名：" + tempPath+file.getName());
        return "删除文件"+imgName+"成功";
    }

}


