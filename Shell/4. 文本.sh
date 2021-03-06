1. 正则表达
主要注意下面
^行开头
$行结尾
[]集合中任一字符
[^]除此集合中的任意一个字符，与^截然不同
[-]范围
? 0-1次
+ 1或多次
* 0或多次
() 匹配字符串可有可无，比如ma(tri)?匹配max或matirx
(n) n次
(n,) 多于n次
(m,n) 最小m次，最大n次
| 或，匹配左边或右边
\ 转义

\b 单词边界 \bcool 匹配cool，不匹配coolant
\B 非单词边界
\d 单个数字字符
\D 单个非数字字符
\w 单个字符
\W 单个非字符
\n 换行符
\s 单个空白字符
\S 单个非空白字符
\r 回车


2. grep
grep "xxx" a # 单文件搜索
grep "xxx" a b c # 多文件

egrep支持正则表达
echo this is a line. | egrep "[a-z]+\." #注意转义和+
grep -r #递归，显示匹配的数量
-i 忽略大小写
打印匹配之前的三行
grep "x" -A 3
-B之后


3. cut
把文本按列切分，可以指定分隔符
cut -f 2,3 file #显示第二列和第三列


4. 统计文件中词频
awk，grep，关联数组
#!/bin/bash
if [ $# -ne 1 ];
then
echo "Usage: $0 filename";
exit -1
fi

filename=$1 

#注意-o是输出的意思，几乎所有命令都有-o 
#\b是匹配单词的意思，\是续行，过长的命令都会用这个，和转义一个标志符
#awk对每一行都执行{}中所包含的内容,不用做循环处理，awk会对每一个处理
#awk就是把文件逐行的读入，以空格为默认分隔符将每行切片，切开的部分再进行各种分析处理
#传过来文本，在awk里，先通过分隔符（-F指定，比如-F ':'），然后会以$n标记
egrep -o "\b[(:alpha:)]+\b" filename | \
awk '{ count[$0]++}
END{ printf("%-14s%d\n","Word","Count"};
for(ind in count){
printf("%-14s%d\n",ind,count[ind])}
}'
结果：
$./word_freq.sh word
Word	Count
this	1 
what	2


5. sed
sed，stream editer，一般用于替换给定文本中的字符串，可以利用正则表达进行匹配
sed 's/pattern/replace_string/' file
sed -i 's/text/replace' file #-i是把结果存到原文件
sed -i's/text/replace/g' file #g是把每处符合的都替换，默认替换每行第一个

/在sed中是定界符，可以使用任意定界符
sed 's:text:replace"g'
定界符出现在要匹配字符串的内部则必须转义

sed有很多典型的用法
sed '/^$/d' file #移出空白行 d是删除的意思
$echo this is | sed 's/\w\+/[&]/g' #&是匹配样式的字符串，注意转义写法
[this] [is]
sed xxx | sed xxx #组合
一般sed单引号，但是在需要变量值的时候需要双引号
$text=hello
$echo hello world | sed "s/$text/HELLO"


6. awk
awk 'BEGIN{print "start"} pattern {commands} END{print "end"}' file
awk由BEGIN，END和匹配语句块组成，任何一部分都可以不出现，脚本一般在单引号或双引号中

awk 'BEGIN { i=0 } { i++ } END{ print i }' filename

awk的工作原理：
先执行BEGIN{ commands }语句块，再从文件或stdin中读取一行，执行pattern{ commands }语句块，重复这个过程直到文件全部读取完毕，
最后执行END{ commands }语句块
echo -e "line1\nline2" | awk 'BEGING{print "Start"} {print} END{print "End"}'
结果：
Start
line1
line2
End

{}有些像一个循环，对文件中每一行迭代
一般把变量的初始化，比如var=0,放在BEGING中，在END中一般放入打印结果的语句，中间是循环
BEGING
循环1
循环2
...
END

NR：记录数量，在执行过程中对应于当前行号
NF：字段数量，在执行过程中对应于当前行的字段数
$0: 执行过程中当前行的文本内容
$1: 第一个字段的文本内容
$2: 第二个字段的文本内容
例：
#注意各个部分的意思，尤其是$0, $1, $n， NR, NF
echo -e "line1 f2 f3\nline2 f4 f5" | \
awk '{
	print "Line no: "NR", No of fields:"NF," "$0="$0, "$1="$1, "$2="$2, "$3="$3
}'
结果：
Line no:1,No of fields:3 $0=line1 f2 f3 $1=line1 $2=f2 $3=f3
Line no:2,No of fields:3 $0=line2 f2 f3 $1=line2 $2=f4 $3=f4
可以用print $NF打印最后一个字段，用$(NF-1)打印倒数第二个字段

seq 3 | awk 'BEGING{ sum=0; print "Summation:" }
{ print $1"+"; sum+=$1 } END {print "=='; print sum }'
结果：
1+
2+
3+
==
6

-v可以传递外部值
var = 10
echo | awk -v variable=$var '{print variable}'

还有一种更灵活的方式
var1="a"; var2="b"
echo | awk '{ print v1,v2 }' v1=$var1 v2=$var2
结果： a b
这种方式是变量之间用空格隔开，以键值对v1=$var1 v2=$var2作为awk的命令行，紧随BEGING，{}，END之后，是在这几个语句后面，即引号后

getline可以用于只想读一行的情况，放在BEGING中
seq 3 | awk 'BEGING {getline; print "Read first line", $0} {print $0}'
结果：
Read first line 1
2
3
#注意print的写法，打印$可以直接放在引号外面，引起来的都是内容，而不是变量

过滤
awk 'NR < 5' #行小于5
awk 'NR==1,NR==4' #行号在1到5之间
awk '/linux/' #正则匹配

定界符
awk -F: '{print $NF}' /etc/passwd #-F指定定界符
或awk 'BEGING { FS=":" } {print $NF}' /etc/passwd


7. 替换文本
sed 最常用的选项
-i 直接修改读取文档内容
-n 只显示处理的那一行

最常用命令 
s取代，d删除，还有a新增之类的都不太用
g全部，而不只是第一个
sed -i 's/John/Sam/g' nameList


8. 对文本每行迭代
shile read line;
do
echo $line
done < file.txt

子shell：
cat file | (while read line; do echo $line; done)

#打印每个单词
for w in $line
do
echo $w
done

#打印单词里的字母,注意单词长度的取法
for((i=0;i<$(#word);i++))
do
echo ${workd:i:1};
done


9. 按列合并
paste f1 f2


10. 打印第n个单词或列
awk '{print $5}' file
ls -l | awk '{print $1": " $3}' #注意只有打印字符部分需要引号，直接写$x就能打印变量


11. 打印不同行或样式之间的文本
awk 'NR==M, NR==n' filename #打印M行到N行之间的文本
awk '/start_pattern/, /pattern_end/' file #打印处于两个pattern正则表达式之间的文本


12. 以逆序的形式打印
awk可以，tac本来就是做这个的，cat的反写
tac f1 f2
seq 3 | tac
3
2
1

13. 电子邮件正则匹配
egrep -o '[A-Za-z0-9.]+@[A-Za-z0-9.]+\.[a-zA-Z]{2,4}' mail.txt

14.打印文件中某样式前n行或后n行
grep a.txt | head -n 10 #取前十行
grep -A 3 "Cameron Diaz" rank.txt #打印匹配之后的三行
grep -B 3 "Cameron Diaz" rank.txt #打印匹配之前的三行
-C打印周围的n行


14. 移出包含某关键字的句子
sed，用空白替换匹配的句子
sed 's/ [^.]*mobile phones[^.]*\.//g' list #移出包含mobile phones
两个点表示句号，句子之间的分隔符


15. awk模拟head tail tac
awk 'NR <= 10' filename #模仿head -10， NR:已经读取的记录数，NF:记录域个数，比如a b就是两个，空格是分隔符

#NR是当前行号，$0是当前行，对10求余后存到数组中，最后再打印出来，比如文件一共99行，则
#99%10=9,第10个元素存99行结果，之前的会被后面的覆盖，保证最后打印的是末尾的10行
awk '{ buffer[NR % 10] = $0; } END { for(i=1;i<11;i++) { print buffer[i%10] } }' file #模仿tail - 10

#把所有文本存入数组，然后倒着打印，模仿tac
awk '{ buffer[NR] = $0; } END {for(i=NR; i>0 ;i--) {print buffer[i] } }' filename

#注意对NR的运用，awk中NR，NF，-F很重要


16. 文本操作
#通过起始位置和长度生成子串
string=abcdefgh
echo ${string:4}
结果：efgh

echo ${string:4:2} #第二个字符开始，打印2个字符
ef

echo ${string:(-1)}
h

echo ${string:(-2):2}
gh


