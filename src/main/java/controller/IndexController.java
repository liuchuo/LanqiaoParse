package controller;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
    @RequestMapping(method = {RequestMethod.GET})
    public String doGet(HttpServletRequest request) {
        File file = new File(request.getServletContext().getRealPath("/WEB-INF/classes"), Utils.getFileName());

        Set<String> schoolSet = getSchoolSet(file);
        request.setAttribute("school", schoolSet);
        return "index";
    }


    private Set<String> getSchoolSet(File file) {
        XSSFWorkbook book = null;
        try {
            Set<String> schoolSet = new TreeSet<String>();
            book = new XSSFWorkbook(new FileInputStream(file));

            Iterator<Row> rows = book.getSheetAt(0).rowIterator();
            if (rows.hasNext()) rows.next(); // skip the table head.

            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();

                String name = row.getCell(1).getStringCellValue();
                if (!schoolSet.contains(name)) {
                    schoolSet.add(name);
                }
            }
            return schoolSet;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
