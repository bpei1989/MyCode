1. 下载
wget url 
-t 3 表示重试3次
-c 表示从上次断点处下载
wget --user username --password pass url
wget --mirror xxx.com #镜像
还有一些其他选项，比如限制速度，层级，递归之类的，具体用法可查

curl的结果需要重定向到文件中，因为默认标准输出
curl url > output


2. lynx
lynx -dump url#将网页以ASCII的形式下载到文件中


3. cURL
支持http，https，ftp，post，cookie，认证，扩展头部，偏移量
curl 会把下载文件输出到stdout，把进度信息输出到stderr， --silent可不显示进度信息
curl -C -url #上次断点处继续传
curl --referer http://google.com http://slynux.org #从google为参照页，跳转到后面的链接

curl http://example.cn --cookie "user=slynux;pass=hack" #cookie
curl URL --cookie-jar cookiefile #从文件里读cookie
curl URL --user-agent "Mozilla/5.0" #代理
curl -H "Host: www.slynux.org" -H "Accept-language: en" url #-H加头部
curl -u user:pass http://xxx
curl -u user http://www.x.com #需要密码提示

curl -I http://www.x.com #打印头部信息


4. 命令访问Gmail
#!/bin/bash
username="xxx"
password="yyy"

SHOW_COUNT=5

curl -u $username:$password --silent "https://mail.google.com/mail/feed/atom" | \
tr -d '\n' | sed 's:</entry>:\n:g' | \
sed 's/.*<titile>\(.*\)<\/titile.*<author><name>\([^<]*\)<\/name><email>'
\([^<]*\).*/Author: \2 [\3] \nSubject: \1\n/' | \
head -n $(( $SHOW_COUNT * 3 ))
#可以不深究以上的意思，很少用这么复杂的，不过其中的几个用法需要注意
#转义，圆括号斜杠需要转义，长命令多行写法

用sed awk消除不必要的细节，sed替换各种尖括号，通过正则，其中，需要注意的是转义


5. curl模仿post
<form action="http://book.sarathlaksham.com/lsc/mlogs/submit.php"
method="post" >

<input type="text" name="host" value="HOSTNAME">
<input type="text" name="user" value="USER">
<input type="submit" >
</form?

curl http://book.sarathlaksham.com/lsc/mlogs/submit.php -d "host=testhost&user=slynux" -o a.html
#-d发送请求，键值对之间用&分隔,-o保存至文件

