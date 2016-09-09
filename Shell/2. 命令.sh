1. 用cat拼接
cat本身就是拼接的意思
cat file1 file2 file3

cat还可以从stdin读取
echo 'text stdin' | cat

tr移出空白行
cat blank.txt | tr -s '\n'
-d 删除字符串1中所有输入字符。
-s 删除所有重复出现字符序列，只保留第一个；即将重复出现字符串压缩为一个字符串。
tr用来从标准输入中通过替换或删除操作进行字符转换
（小写 --> 大写）
# cat file | tr [a-z] [A-Z] > new_file

cat -n txt#显示行号


2. find
find path
find从path开始递归查找

find . -print#当前目录，-print是打印
find .. #父目录

find /home/ -name "*.txt" -print #注意-name
-iname忽略大小写

-regex可用正则匹配路径
find . -regex ".*\(\.py\|\.sh\)$"#注意特殊字符转义

!为否
find . ! -name "*.txt" -print

-maxdepth -mindepth来规定递归深度
find . -maxdepth 1 -type f -print #打印最大深度为1的所有普通文件
find . -mindepth 3 -type f -print #打印从距离当前目录第三层开始的普通文件
-type还可以是d目录，l链接

-atime最近一次访问文件时间
-mtime最近一次修改时间
-ctime元数据比如权限，最近一次改变时间
time的单位是天

-amin -mmin -cmin单位分
find . -type f -atime +7 -print#+是指以上，-是指以下

find . -type f -size +2k #-size大小

find . -type f -size +2k -delete#删除 


3. xargs
把标准输入数据转换成命令行参数
$cat e.txt
1 2 3
4 5

$cat e.txt | xargs #把多行转为单行
1 2 3 4 5

cat e.txt | xargs -n 3 # 3行

cat e.txt | xargs -n 1 ./xx.sh #传参

find . -type f -name "*.txt" -print | xargs rm -f #和find结合使用

find / -type f -name "*.c" -print0 | xargs -0 wc -l #统计C语言文件个数，注意print0，即不附加回车


4. tr 
对标准输入的字符进行替换，删除，可以将一组字符编程另一组字符
echo "HELLO" | tr 'A-Z' 'a-z' #大写转为小写

tr可以把字符从一个集合映射到另一个集合中
echo 12345 | tr '0-9' '9843975341' #把0-9依次映射为后面的字符

cat txt | tr '\t' ' '

cat file | tr -d 'abc' #把字符集abc中包含的字符从stdin中去除


5. 校验
md5sum sha1sum命令

md5sum vdnet #32位十六进制串                                                                                                              
1840bdd908fdeb72c734b69b1c1e2a8d  vdnet

sha1sum vdnet #40个字符的十六进制串
677c83e5871b38c5b95486a8a93025f3739d28b3  vdnet


6. 排序与去重
sort uniq

sort file1 file2 ...>sort.txt

cat sort.txt | uniq>uniq.txt

sort -m file1 file2 #对合并两个排序文件，不需要重排，-m

sort -r #倒序
-n #以数字排序，默认以字母顺序

uniq -c #显示各行出现次数


7. 分隔数据
删除一个大小为100k的文件
split -b 10k datafile


8.重命名
mv 原名称 名称








