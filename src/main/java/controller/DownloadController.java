package controller;

import beans.SchoolReward;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping(value = "/download")
public class DownloadController {
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        String[] checkBox = request.getParameterValues("schoolName");
        if (checkBox.length == 0) {
            return "forward:index?school=null";
        } else {
            Set<String> selectedSchoolName = new TreeSet<String>();
            for (String t : checkBox) {
                selectedSchoolName.add(t);
            }

            File file = new File(request.getServletContext().getRealPath("/WEB-INF/classes"), Utils.getFileName());
            Workbook book = generateRewardFile(generateSchoolReward(selectedSchoolName, file));
            File outputFile = new File("LanQiao.xlsx");
            try {
                book.write(new FileOutputStream(outputFile));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fileDownload(response, outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "forward:download";
        }
    }

    private void fileDownload(HttpServletResponse response, File file) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            OutputStream outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer);
                i = bufferedInputStream.read(buffer);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        }

    }

    private XSSFWorkbook generateRewardFile(Map<String, SchoolReward> map) {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("sheet1");

        int index = 0;
        Row row = sheet.createRow(index++);
        Cell cell = row.createCell(0);
        cell.setCellValue("学校名称");
        cell = row.createCell(1);
        cell.setCellValue("竞赛科目名称及组别");
        cell = row.createCell(2);
        cell.setCellValue("分科获奖人数");
        cell = row.createCell(3);
        cell.setCellValue("一等奖数量");
        cell = row.createCell(4);
        cell.setCellValue("占获奖总数%");
        cell = row.createCell(5);
        cell.setCellValue("二等奖数量");
        cell = row.createCell(6);
        cell.setCellValue("占获奖总数%");
        cell = row.createCell(7);
        cell.setCellValue("三等奖数量");
        cell = row.createCell(8);
        cell.setCellValue("占获奖总数%");

        CellStyle style = book.createCellStyle();
        style.setDataFormat(book.createDataFormat().getFormat("0.00%"));

        for (String s : map.keySet()) {
            if (!s.equals("")) {
                SchoolReward reward = map.get(s);
                if (reward.getTotalCppA() != 0) {
                    row = sheet.createRow(index++);
                    int[] t = reward.getCppA();
                    setValue(row, s, "C/C++程序设计大学 A 组省赛", t[0], t[1], t[2], style);
                }

                if (reward.getTotalCppB() != 0) {
                    row = sheet.createRow(index++);
                    int[] t = reward.getCppB();
                    setValue(row, s, "C/C++程序设计大学 B 组省赛", t[0], t[1], t[2], style);
                }

                if (reward.getTotalCppC() != 0) {
                    row = sheet.createRow(index++);
                    int[] t = reward.getCppC();
                    setValue(row, s, "C/C++程序设计大学 C 组省赛", t[0], t[1], t[2], style);
                }

                if (reward.getTotalJavaA() != 0) {
                    row = sheet.createRow(index++);
                    int[] t = reward.getJavaA();
                    setValue(row, s, "JAVA 软件开发大学 A 组省赛", t[0], t[1], t[2], style);
                }

                if (reward.getTotalJavaB() != 0) {
                    row = sheet.createRow(index++);
                    int[] t = reward.getJavaB();
                    setValue(row, s, "JAVA 软件开发大学 B 组省赛", t[0], t[1], t[2], style);
                }

                if (reward.getTotalJavaC() != 0) {
                    row = sheet.createRow(index++);
                    int[] t = reward.getJavaC();
                    setValue(row, s, "JAVA 软件开发大学 C 组省赛", t[0], t[1], t[2], style);
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            sheet.autoSizeColumn(i);
        }

        return book;
    }

    private void setValue (Row row, String school, String subject, int one, int two, int three, CellStyle style) {
        Cell cell = row.createCell(0);
        cell.setCellValue(school);
        cell = row.createCell(1);
        cell.setCellValue(subject);
        cell = row.createCell(2);

        int sum = one + two + three;
        cell.setCellValue(sum);
        cell = row.createCell(3);
        cell.setCellValue(one);
        cell = row.createCell(4);
        cell.setCellValue(one * 1.0 / sum);
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue(two);
        cell = row.createCell(6);
        cell.setCellValue(two * 1.0 / sum);
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue(three);
        cell = row.createCell(8);
        cell.setCellValue(three * 1.0 / sum);
        cell.setCellStyle(style);
    }


    private Map<String, SchoolReward> generateSchoolReward(Set<String> selectedSchoolName, File file) {
        Map<String, SchoolReward> map = new TreeMap<String, SchoolReward>();
        XSSFWorkbook book = null;
        try {
            book = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = book.getSheetAt(0);

            Iterator<Row> rows = sheet.rowIterator();
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                String school = row.getCell(1).getStringCellValue();
                if (!selectedSchoolName.contains(school)) {
                if (map.get(school) == null) {
                    map.put(school, new SchoolReward());
                } else {
                    String subject = row.getCell(4).getStringCellValue();
                    String award = row.getCell(5).getStringCellValue();
                    if (subject.equals("C/C++程序设计大学 A 组省赛")) {
                        if (award.equals("一等奖")) {
                            map.get(school).setCppAIncrementOne(0);
                        } else if (award.equals("三等奖")) {
                            map.get(school).setCppAIncrementOne(2);
                        } else if (award.equals("二等奖")) {
                            map.get(school).setCppAIncrementOne(1);
                        }
                    } else if (subject.equals("C/C++程序设计大学 B 组省赛")) {
                        if (award.equals("一等奖")) {
                            map.get(school).setCppBIncrementOne(0);
                        } else if (award.equals("三等奖")) {
                            map.get(school).setCppBIncrementOne(2);
                        } else if (award.equals("二等奖")) {
                            map.get(school).setCppBIncrementOne(1);
                        }
                    } else if (subject.equals("C/C++程序设计大学 C 组省赛")) {
                        if (award.equals("一等奖")) {
                            map.get(school).setCppCIncrementOne(0);
                        } else if (award.equals("三等奖")) {
                            map.get(school).setCppCIncrementOne(2);
                        } else if (award.equals("二等奖")) {
                            map.get(school).setCppCIncrementOne(1);
                        }
                    } else if (subject.equals("JAVA 软件开发大学 A 组省赛")) {
                        if (award.equals("一等奖")) {
                            map.get(school).setJavaAIncrementOne(0);
                        } else if (award.equals("三等奖")) {
                            map.get(school).setJavaAIncrementOne(2);
                        } else if (award.equals("二等奖")) {
                            map.get(school).setJavaAIncrementOne(1);
                        }
                    } else if (subject.equals("JAVA 软件开发大学 B 组省赛")) {
                        if (award.equals("一等奖")) {
                            map.get(school).setJavaBIncrementOne(0);
                        } else if (award.equals("三等奖")) {
                            map.get(school).setJavaBIncrementOne(2);
                        } else if (award.equals("二等奖")) {
                            map.get(school).setJavaBIncrementOne(1);
                        }
                    } else if (subject.equals("JAVA 软件开发大学 C 组省赛")) {
                        if (award.equals("一等奖")) {
                            map.get(school).setJavaCIncrementOne(0);
                        } else if (award.equals("三等奖")) {
                            map.get(school).setJavaCIncrementOne(2);
                        } else if (award.equals("二等奖")) {
                            map.get(school).setJavaCIncrementOne(1);
                        }
                    }
                }
                }
            }
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
        return map;
    }
}