package com.examsys.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by twinkleStar on 2019/9/22.
 */

@Component
public class OtherUtil {


    /**
     * 发送响应流方法
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response, String fileName, HSSFWorkbook wb) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
