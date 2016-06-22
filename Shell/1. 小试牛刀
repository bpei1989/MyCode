1. #!/bin/bash
/bin/bash是Bash的路径


2.cat /proc/$PID/environ
用于查看进程运行时的环境变量

pgrep gedit
获取gedit的进程号

然后cat /proc/gedit进程号/environ
结果是name=value格式，用\0分隔

cat /proc/12501/environ | tr '\0' '\n'
以回车作为分隔符

环境变量是未在当前进程定义，而从父进程中继承而来的变量
export命令可设置环境变量

HTTP_PROXY定义了上网连那个服务器
HTTP_PROXY=http://192.168.0.2:3128
export HTTP_PROXY
之后从当前shell脚本执行的任何程序都会继承这个变量

配置path
export PATH="$PATH:/home/user/bin"
或
PATH="$PATH:/home/user/bin"
export PATH

echo $PATH 或 echo ${PATH}


3. 获取变量的长度
len = ${#var}

var=123
echo ${#var}
3
len是字符串所包含的字符数


4. echo $SHELL
获取当前shell版本
或 echo $0


5. 检查是否为root
root的UID 0
if [ $UID -ne 0 ]; then
echo None root user.
else
echo "root user"
fi


6. 运算
let (()) []执行基本的算数操作，expr和bc用于高级运算
#!/bin/bash
no1=4;
no2=5;
let result = no1+no2;
echo $result

let no1++
let no1--

result = $[no1 + no2] #与let等价
result = $[$no1 + 5] #注意$
result = $((no1 + 5))
#expr可以进行运算
result = `expr 3+4`
result = $(expr $no1 + 5)

以上只支持整数，不支持浮点
bc支持浮点和高级运算
echo "4 * 0.32" | bc

no=54;
result=`echo "$no * 1.5" | bc`
echo $result
81.0

scale用于精度
echo "scale=2;3/8" | bc
0.37

obase ibase进制转换
#!/bin/bash

no=100
echo "obase=2;$no" | bc
1100100
no=1100100
echo "obase=10;ibase=2;$no" | bc
100

平方根
echo "sqrt(100)" | bc
echo "10^10" | bc


7. 文件描述符和重定向
>清空 >>append
0 stdin
1 stdout
2 stderr
>等同于1>，>>类似
当一个命令发生错误并退回时，它会返回一个非0的退出状态；而当命令成功完成
则会为0，命令执行后立即查询$?可看出命令是否执行成功，echo $?

/dev/null是一个特殊的设备文件，这个文件接收的任何数据都会被丢弃

tee可实现一面把数据重定向到文件，另一方面提供一份重定向数据的副本作为后续命令的stdin

cat a | tee out.txt | cat -n

默认情况下tee会清空文件，用-a可以变为append

重定向可以像从stdin那样从文件中读取数据：
cmd < file

exec命令可以创建自定义的文件描述符

<从文件中读取 >写入 >>追加模式写入
$exec 3<input.txt #使用文件描述符3打开并读取文件
$echo this is a test line > input.txt
$exec 3<input.txt
现在就可以用文件描述符3了
$cat < &3
this is a test line

$exec 4>output.txt #写入
$echo newline > &4
$cat output.txt
newline

exec 5>>input.txt
echo appended line >&5
cat input.txt
newline
appneded line


8. 数组
一列值来定义数组：
array_var=(1 2 3 4 5 6)

还可以用索引-值
array_var[0]="test1"
array_var[1]="test2"
array_var[2]="test3"

$echo ${array_var[0]}
test1

index=2
$ echo ${array_var[$index]}
test3

$echo ${array_var[*]}#以清单的方式打印
test1 test2 test3

$echo ${#array_var[*]}#打印数组的长度
3


9. 关联数组
可以用任意文本作为数组索引，普通数组中只能用整数为索引
$declare -A array#声明关联数组
有两种方法将元素添加到关联数组中
array=([index1]=val1 [index2]=val2)
array[index1]=val1
array[index2]=val2

例：
declare -A fruits_value
fruits_value=([Apple]="100" [banana]="90")
echo "Apple ${fruits_value[apple]}"
结果：Apple 100

数组索引列表(一般数组也可以用下面方法，注意是打印索引，不是数组内容)
echo ${!array[*]}
echo ${!array[@]}

echo ${!fruits_value[*]}
结果：apple banana


10. 别名
alias new_command='command sequence'

alias install='sudo apt-get install'

alias的结果是暂时的，终端关闭就失效了，可以把它放入~/.bashrc里
echo 'alias cmd="command seq"' >> ~/.bashrc
删除别名可以用unalias，如果存到文件里，就从bashrc里删除

\可以对别名转义，执行原本的命令，而不是别名
$\command
（可以用来防止恶意的别名，比如把ls别名成rm -rf /）


11. 读取时间
$date
Wed Jun 22 07:49:04 EDT 2016

$date +%s#从1970-1-1秒数

$date --date "Wed Jun 22 07:49:04 EDT 2016" +%s 
1466596144


12. sleep
sleep xsecond

#!/bin/bash
count=0;
while true;
do
if [ $count -lt 40 ];
then let count++;
sleep 1;
echo -n $count;
else exit 0;
fi
done

注意程序的结构，比如do done if fi， 分号then let


13. 调试脚本
bash -x script.sh
-x打印执行的每一行命令以及当前状态

-x会打印到stdout，如果需要部分打印则可以用
set -x 在执行时显示参数和命令
set +x 禁止调试
set -v 命令读取时显示输入
set +v 禁止打印输入

for in in {1..6}
do
set -x
echo $i
set +x
done
只有在-x +x才打印出来

可以通过环境变量_DEBUG来建立调试风格
#!/bin/bash
function DEBUG(){
["$_DEBUG" == "on"] && $@ || :
}

for i in {1..10}
do
DEBUG echo $i
done

$_DEBUG=on ./script.sh
每行需要打印调试信息的语句前加上DEBUG，:命令是不进行任何操作
#注意程序的环境变量用法，把环境变量传入程序是在前面，里面直接用就行

#!/bin/bash 改成#!/bin/bash -xv，这样直接开启调试
调试会打印每一步的结果


14. 函数和参数
function fname(){
xxx
}
fname(){
xxx
}
可以用function也可以直接写函数
直接用函数的名字就可以调用
$fname

fname arg1 arg2#传递参数
访问函数参数的方法
fname(){
	echo $1, $2; #访问参数1和2
	echo "$@" #以列表方式一次打印所有参数
	echo "$*"; #类似于$@,但参数被看作单个，不是列表
	return 0; 
}

$@用的最多，因为返回的是参数数组

shell支持递归
F(){
	echo $1;
	F hello;
	sleep 1;
}

在/etc/security/limits.conf设置最大进程数，防止进程过多

函数可以与环境变量一样导出，export，函数的作用域可扩展到子进程，
export -f fname

检测命令是否执行成功
#!/bin/bash
CMD='xxx'
status
$CMD
if [ $? -eq 0 ];
then
echo "$CMD executed successfully"
else
echo "$CMD error"
fi


15. 管道
cmd1 | cmd2 | cmd3

ls | cat -n > out.txt

cmd_output=$(COMMANDS)#读取命令序列的输出
cmd_output=$(ls | cat -n)#()子shell进程
echo $cmd_output

反引用可用于存储命令输出
cmd_output=`COMMANDS`#与上类似的功能
cmd_output=`ls | cat -n`
echo $cmd_output

用shell生成一个独立进程，()生成一个子shell
pwd;
(cd /bin; ls);
pwd
#()由于是子shell不会影响到主进程的目录

""保留换行符
cat a.txt
1
2
3

out=$(cat a.txt)
结果：1 2 3

out="$(cat a.txt)"
echo $out
结果：
1
2
3


16. read
不需要回车就能读取，普通都需要回车后才能输入
read -n number_of_chars var

read -n 2 var
echo var

read -p "Enter: " var #显示提示信息
read -d ":" var
hello: #var配设置为hello，以：结束


17. 分隔符和迭代器
内部字段分隔符，IFS，很有用，IFS环境变量用于存定界符

#!/bin/bash
line="root:x:0:0:root:/root:/bin/bash"
oldIFS=$IFS
IFS=":"
count=0
for item in $line;
do
[ $count -eq 0 ] && user=$item;
[ $count -eq 6 ] && shell=$item;
let count++;
done;
IFS=$oldIFS
echo $user\'s shell is $shell;


18. for
多种for写法
for var in list;
do
command
done

echo {1..50}#1到50的数字列表
echo {a..z}
for i in {a..z}
do
action;
done

for((i=0;i<10;i++)){
#注意是两个括号
}

while xxx
do
done


until,循环到条件为真
x=0
until [ $x -eq 9 ];#方括弧两边都有空格
do let x++;
done

19. 比较
if condition;
then
command;
elif condition;
then
command;
else
command
fi
#注意分号的地方

用逻辑可能更简洁
[ condition ] && action #如果condition为true则会执行action
[ condition ] || action #如果condition为false则会执行action

[ xx -q yy -a bb -ge cc ]注意空格不然会出错
-gt -lt -ge大于等于 -le小于等于
-a and  -o or

[ -f $file ] #如果file为文件路径或文件名则为真
[ -x $var ] #如果var可执行则返回真
-d 目录
-e 文件存在
-c 字符设备文件路径
-b 块设备文件路径
-w 可写文件
-r 文件可读
-L 符号链接

字符串比较用双中括号，因为有时候单括号会出错，避免出错就用双[]
[[ $str1 = $str2 ]]#str1和str2是否包含同样的字符，有空格，如果没空格则为覆盖，注意shell中的空格，很敏感
[[ $str1 == $str2 ]] #与上面相同
[[ $str1 != $str2 ]]
[[ $str1 > $str2 ]]
[[ $str1 < $str2 ]]
[[ -z $str ]] #str是空字符串返回true
[[ -n $str ]] #str非空则返回true
还可以用&&之类的逻辑 if [[ -n $str]] && [[ -z $str1 ]]
