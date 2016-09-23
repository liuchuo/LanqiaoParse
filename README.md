# lanqiaoParse

![](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg) [![](https://img.shields.io/aur/license/yaourt.svg)](https://github.com/JoyHwong/lanqiaoParse/blob/master/LICENSE)

### Get Started

- Eclipse
  ​

  ![](img/Snip20160918_12.png)
  ![](img/Snip20160918_13.png)
  ![](img/Snip20160918_14.png)
  ​

- Intellij Idea
  ![](img/Snip20160918_7.png)
  ![](img/Snip20160918_8.png)
  ![](img/Snip20160918_9.png)
  ![](img/Snip20160918_10.png)
  ![](img/Snip20160918_11.png)
  ​

### Principle

基于[Apache POI](http://poi.apache.org/)的Java命令行程序


### Note

- Web资源路径和Java资源路径问题

  在Java的命令行程序里面，文件的路径是直接在根路径下可以找到的，比如在lanqiaoParse路径下的文件file，那么File file = new File(file);就是直接在根目录下可以找到的；在web程序里面，文件一般放在WEB-INF下，那么就要File file = new File(request.getServletContext().getRealPath("/WEB-INF"), file);才能找到了

- 在用Servlet下载后，重定向到一个页面

  在Servlet里面用response.getOutputStream输出文件之后，response就被commited了，所以不能执行response.sendRedirect("path")了。解决的办法是通过`<a href="" > </a>`连接访问servlet。

### License

[GPL-3.0 License](https://github.com/JoyHwong/lanqiaoParse/blob/master/LICENSE)

### Contributor

[LewiesCheng](https://github.com/LewiesCheng)

