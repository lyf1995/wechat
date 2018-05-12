package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.CommodityDao;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by Administrator on 2018/5/12 0012.
 */
@Controller
@RequestMapping(value = "/image/")
public class ImageController {
    @Autowired
    private CommodityDao commodityDao;
    @ResponseBody
    @RequestMapping(path = "/mainImage", method={RequestMethod.POST})
    public JSONObject addDish(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        JSONObject jsonOut = new JSONObject();
        System.out.println(id);
        String path = null;// 文件路径
        String json = "";

        if (file1!=null) {// 判断上传的文件是否为空

            String type = null;// 文件类型
            String fileName = file1.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:"+fileName);
            // 判断文件类型
            type = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
//                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    //项目绝对路径
                    String mallPath = "D:\\lyf\\毕设\\WeChat-mall\\static\\images\\"+fileName;
                    String managePath = "D:\\lyf\\毕设\\WeChat-mall-manage\\static\\images\\"+fileName;
                    String toMysql = "/static/images/"+fileName;
                    // 自定义的文件名称
//                    String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                    // 设置存放图片文件的路径
//                    path = realPath+/*System.getProperty("file.separator")+*/fileName;
                    System.out.println("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    try {
                        file1.transferTo(new File(mallPath));
                        file2.transferTo(new File(managePath));
                        commodityDao.updateMainImage(toMysql, id);
                        StaticOptionCode.setResult(jsonOut,0,toMysql,true,"");
                    }catch (Exception e){

                    }
                    System.out.println("文件成功上传到指定目录下");
                }
                json = "{\"res\":1}";
            }else {
                System.out.println("不是我们想要的文件类型,请按要求重新上传");
                //return null;
                json = "{\"res\":0}";
            }
        }else {
            System.out.println("文件类型为空");
            //return null;
            json = "{\"res\":0}";
        }
        return jsonOut;
    }
}
