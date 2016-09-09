sed "s/Tom/Tommy/" 1.txt #把Tom的名字改成Tommy
sed "/Mike/ d" 1.txt #删除包含Mike的行
sed "1,3d" 1.txt #删除一到三行
sed "/^$/ d" 1.txt #删除所有空白行
sed "5,10! d" 1.txt #显示5-10行


a.txt
Mike Harrington:[510] 548-1278:250:100:175
Christian Dobbins:[408] 538-2358:155:90:201
Susan Dalsass:[206] 654-6279:250:60:50
Archie McNichol:[206] 548-1348:250:100:175
Jody Savage:[206] 548-1278:15:188:150
Guy Quigley:[916] 343-6410:250:100:175
Dan Savage:[406] 298-7744:450:300:275

awk -F ':' '{print $2}' a.txt #显示所有电话号码
awk -F ':' '/Dan/ {print $2}' a.txt #显示Dan的电话号码
awk -F ':' '/Susan/ {print $1, $2}' a.txt #显示Susan的名字和电话号码
awk -F '[:| ]' '$2 ~ /^D/ {print $2}' a.txt #显示所有以D开头的姓，注意$2 ~ /^D/，即第二部分匹配D开头，类似perl语法
awk -F '[:| ]' '$1 ~ /^[C|E]/ {print $1}' a.txt #显示所有以一个C或E开头的名
awk 'length($1) == 4 {print $1}' a.txt #显示所有只有四个字符的名，注意，$1，$2等可以作为函数或正则匹配参数和对象
awk -F '[:| ]' '$3 ~ /\[916\]/ {print $1,$2}' a.txt #显示所有区号为916的人名，注意方括号用转义字符

写一个awk的脚本,它的作用:
	 - 显示Savage的全名和电话号码
	 - 显示Chet的捐款
	 - 显示所有头一个月捐款$250的人名.
awk -F '[:| ]' '{if($2=="Savage") {print $1,$2,$3,$4} else if ($1=="Chet") {print $5,$6,$7} else if ($5=="250") {print $1,$2}}' 1.txt #注意if-else