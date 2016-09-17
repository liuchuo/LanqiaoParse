package com.joyhwong;

import beans.Reward;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by JoyHwong on 8/23/16.
 * copyright @ 2016 All right reserved.
 * follow me on github https://github.com/JoyHwong
 */
public class Parse {
    public static void main(String[] args) {
        // 创建一个map按照学校的名字来存储学校各个奖项的记录
        SortedMap<String, Reward> rewards = new TreeMap<String, Reward>();

        // 获取需要进行分析的源文件
        File excelFile = new File("2016年软件类-江苏赛区获奖名单.xlsx");
        try {
            // 创建一个工作薄表单对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(excelFile));
            // 获取工作薄中得第一张工作表
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            // 从工作表中获取工作表的行指针
            Iterator<Row> rows = xssfSheet.rowIterator();
            // 跳过文件的前三行无用信息
            rows.next();rows.next();rows.next();
            while (rows.hasNext()) {
                // 获取工作表中得一行
                XSSFRow row = (XSSFRow) rows.next();
                // 获取行迭代器
                Iterator<Cell> cells = row.cellIterator();
                cells.next(); // 跳过第一个格子(江苏)
                XSSFCell cell = (XSSFCell) cells.next(); // 获取格子的内容
                if (rewards.get(cell.getStringCellValue()) == null) {
                    rewards.put(cell.getStringCellValue(), new Reward());
                }

                String school = cell.getStringCellValue(); // 获取学校名

                cells.next();cells.next(); // 跳过准考证号和姓名

                String subject = cells.next().getStringCellValue(); // 获取科目值
                String score = cells.next().getStringCellValue(); // 获取奖项

                // 按照各组各奖项分别放到对应的数组中
                Reward reward = rewards.get(school);

                if (subject.equals("C/C++程序设计大学 A 组省赛")) {
                    if (score.equals("一等奖")) {
                        reward.addCa(0);
                    } else if (score.equals("二等奖")) {
                        reward.addCa(1);
                    } else if (score.equals("三等奖")) {
                        reward.addCa(2);
                    }
                } else if (subject.equals("C/C++程序设计大学 B 组省赛")) {
                    if (score.equals("一等奖")) {
                        reward.addCb(0);
                    } else if (score.equals("二等奖")) {
                        reward.addCb(1);
                    } else if (score.equals("三等奖")) {
                        reward.addCb(2);
                    }
                } else if (subject.equals("JAVA 软件开发大学 A 组省赛")) {
                    if (score.equals("一等奖")) {
                        reward.addJa(0);
                    } else if (score.equals("二等奖")) {
                        reward.addJa(1);
                    } else if (score.equals("三等奖")) {
                        reward.addJa(2);
                    }
                } else if (subject.equals("JAVA 软件开发大学 B 组省赛")) {
                    if (score.equals("一等奖")) {
                        reward.addJb(0);
                    } else if (score.equals("二等奖")) {
                        reward.addJb(1);
                    } else if (score.equals("三等奖")) {
                        reward.addJb(2);
                    }
                }
                rewards.put(school, reward);
            }

            // 将所有的数据处理完后,将表格打印出来
            printTable(rewards);
            xssfWorkbook.close();
            
            new StuInBJ();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTable (SortedMap<String, Reward> rewards) {

        String sheetName = "sheet1";
        XSSFWorkbook xssfWorkBook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkBook.createSheet(sheetName);
        int k = 0;
        XSSFRow row;
        XSSFCell cell;
        row = xssfSheet.createRow(k++);
        cell = row.createCell(0);
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

        CellStyle style = xssfWorkBook.createCellStyle();
        style.setDataFormat(xssfWorkBook.createDataFormat().getFormat("0.00%"));

        for (String s : rewards.keySet()) {
            if (!s.equals("")) {
                Reward reward = rewards.get(s);

                if (reward.getCat() != 0) {
                    row = xssfSheet.createRow(k++);
                    cell = row.createCell(0);
                    cell.setCellValue(s);
                    cell = row.createCell(1);
                    cell.setCellValue("C/C++程序设计大学 A 组省赛");
                    cell = row.createCell(2);
                    cell.setCellValue(reward.getCat());

                    int [] t = reward.getCa();
                    cell = row.createCell(3);
                    cell.setCellValue(t[0]);
                    cell = row.createCell(4);
                    cell.setCellValue(t[0] * 1.0 / reward.getCat());
                    cell.setCellStyle(style);
                    cell = row.createCell(5);
                    cell.setCellValue(t[1]);
                    cell = row.createCell(6);
                    cell.setCellValue(t[1] * 1.0 / reward.getCat());
                    cell.setCellStyle(style);
                    cell = row.createCell(7);
                    cell.setCellValue(t[2]);
                    cell = row.createCell(8);
                    cell.setCellValue(t[2] * 1.0 / reward.getCat());
                    cell.setCellStyle(style);
                }

                if (reward.getCbt() != 0) {
                    row = xssfSheet.createRow(k++);
                    cell = row.createCell(0);
                    cell.setCellValue(s);
                    cell = row.createCell(1);
                    cell.setCellValue("C/C++程序设计大学 B 组省赛");
                    cell = row.createCell(2);
                    cell.setCellValue(reward.getCbt());

                    int [] t = reward.getCb();
                    cell = row.createCell(3);
                    cell.setCellValue(t[0]);
                    cell = row.createCell(4);
                    cell.setCellValue(t[0] * 1.0 / reward.getCbt());
                    cell.setCellStyle(style);
                    cell = row.createCell(5);
                    cell.setCellValue(t[1]);
                    cell = row.createCell(6);
                    cell.setCellValue(t[1] * 1.0 / reward.getCbt());
                    cell.setCellStyle(style);
                    cell = row.createCell(7);
                    cell.setCellValue(t[2]);
                    cell = row.createCell(8);
                    cell.setCellValue(t[2] * 1.0 / reward.getCbt());
                    cell.setCellStyle(style);
                }


                if (reward.getJat() != 0) {
                    row = xssfSheet.createRow(k++);
                    cell = row.createCell(0);
                    cell.setCellValue(s);
                    cell = row.createCell(1);
                    cell.setCellValue("JAVA 软件开发大学 A 组省赛");
                    cell = row.createCell(2);
                    cell.setCellValue(reward.getJat());

                    int [] t = reward.getJa();
                    cell = row.createCell(3);
                    cell.setCellValue(t[0]);
                    cell = row.createCell(4);
                    cell.setCellValue(t[0] * 1.0 / reward.getJat());
                    cell.setCellStyle(style);
                    cell = row.createCell(5);
                    cell.setCellValue(t[1]);
                    cell = row.createCell(6);
                    cell.setCellValue(t[1] * 1.0 / reward.getJat());
                    cell.setCellStyle(style);
                    cell = row.createCell(7);
                    cell.setCellValue(t[2]);
                    cell = row.createCell(8);
                    cell.setCellValue(t[2] * 1.0/ reward.getJat());
                    cell.setCellStyle(style);
                }


                if (reward.getJbt() != 0) {
                    row = xssfSheet.createRow(k++);
                    cell = row.createCell(0);
                    cell.setCellValue(s);
                    cell = row.createCell(1);
                    cell.setCellValue("JAVA 软件开发大学 B 组省赛");
                    cell = row.createCell(2);
                    cell.setCellValue(reward.getJbt());

                    int [] t = reward.getJb();
                    cell = row.createCell(3);
                    cell.setCellValue(t[0]);
                    cell = row.createCell(4);
                    cell.setCellValue(t[0] * 1.0 / reward.getJbt());
                    cell.setCellStyle(style);
                    cell = row.createCell(5);
                    cell.setCellValue(t[1]);
                    cell = row.createCell(6);
                    cell.setCellValue(t[1] * 1.0 / reward.getJbt());
                    cell.setCellStyle(style);
                    cell = row.createCell(7);
                    cell.setCellValue(t[2]);
                    cell = row.createCell(8);
                    cell.setCellValue(t[2] * 1.0 / reward.getJbt());
                    cell.setCellStyle(style);
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            xssfSheet.autoSizeColumn(i);
        }

        File file = new File("result.xlsx");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xssfWorkBook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        try {
			xssfWorkBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
