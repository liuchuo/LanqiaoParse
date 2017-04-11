# lanqiaoParse

![](https://travis-ci.org/JoyHwong/LanqiaoParse.svg?branch=master)


### Note

- Web资源路径和Java资源路径问题

  在Java的命令行程序里面，文件的路径是直接在根路径下可以找到的，比如在lanqiaoParse路径下的文件file，那么File file = new File(file);就是直接在根目录下可以找到的；在web程序里面，文件一般放在WEB-INF下，那么就要File file = new File(request.getServletContext().getRealPath("/WEB-INF"), file);才能找到了

- 在用Servlet下载后，重定向到一个页面

  在Servlet里面用response.getOutputStream输出文件之后，response就被commited了，所以不能执行response.sendRedirect("path")了。解决的办法是通过`<a href="" > </a>`连接访问servlet。

### License

[GPL-3.0 License](https://github.com/JoyHwong/lanqiaoParse/blob/master/LICENSE)

### Contributor

[LewiesCheng](https://github.com/LewiesCheng)

