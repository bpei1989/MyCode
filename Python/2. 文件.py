1. 读取文件
#最方便的方法是一次性读取文件中的所有内容并放置到一个大字符串中
all_the_text = open('thefile.txt').read() #读文本文件，普通文本只需要r
all_the_data = open('abinfile', 'rb').read() #二进制文件，二进制需要b，rb

#一般为安全起见都会有完成操作后关闭文件
file_object = open('thefile.txt')
try:
	all_the_text = file_object.read()
finally:
	file_object.close()

#最简单最快的是逐行读取文本内容，并将结果发放到字符串列表中
list_of_all_the_lines = file_object.readlines() #读出的每行都带\n

#不带\n
list_of_all_the_lines = file_object.read().splitlines()
list_of_all_the_lines = file_object.read().split('\n')
list_of_all_the_lines = [L.rstrip('\n') for L in file_object]

#最简单最快逐行处理文本的方法是for（处理不是读取，最好读取是readlines）
for line in file_object:
	process line
#这样也会有\n不留的话用rstrip
line = line.rstrip('\n')
#去除所有空白符（包括\n）
line = line.rstrip()

(注意：二进制文件处理时会碰到的问题
我们使用处理二进制文件时，需要用如下方法
binfile=open(filepath,'rb')    读二进制文件

binfile=open(filepath,'wb')    写二进制文件

那么和binfile=open(filepath,'r')的结果到底有何不同呢？
不同之处有两个地方：
第一，使用'r'的时候如果碰到'0x1A'，就会视为文件结束，这就是EOF。使用'rb'则不存在这个问题。即，如果你用二进制写入再用文本读出的话，如果其中存在'0X1A'，
就只会读出文件的一部分。使用'rb'的时候会一直读到文件末尾。
第二，对于字符串x='abc\ndef'，我们可用len(x)得到它的长度为7，\n我们称之为换行符，实际上是'0X0A'。当我们用'w'即文本方式写的时候，在windows平台上会
自动将'0X0A'变成两个字符'0X0D'，'0X0A'，即文件长度实际上变成8.。当用'r'文本方式读取时，又自动的转换成原来的换行符。如果换成'wb'二进制方式来写的话，
则会保持一个字符不变，读取时也是原样读取。所以如果用文本方式写入，用二进制方式读取的话，就要考虑这多出的一个字节了。'0X0D'又称回车符。linux下不会变。
因为linux只使用'0X0A'来表示换行。)

2. 写入文件
#最方便将一个字符串写入文件
open('thefile.txt', 'w').write(all_the_text) #文本
open('abinfile', 'wb').write(all_the_data) #二进制 注意二进制需要b（wb，rb）

#最好给文件对象指定名字，方便close
file_obj = open('thefile.txt', 'w')
file_obj.write(all_the_text)
file_obj.close()

#把一个字符串列表中的字符或二进制写入文件
file_object.writelines(list_of_text_strings) #writelines用于二进制文件和文本，不仅是文本
open('abinfile', 'wb').writelines(list_of_data_strings)
#也可以把字符串拼接成长字符串，用join，再用write写入，或者用循环中写，但用writelines比这两种方式快得多


3. 搜索和替换文件中的文本
#!/usr/bin/env python
import os, sys
nargs = len(sys.argv)
if not 3<= nargs <=5:
	print "xxx"
else:
	stext = sys.argv[1]
	rtext = sys.argv[2]
	input_file = sys.stdin
	input_file = sys.stdout
	if nargs > 3:
		input_file = open(sys.argv[3])
	if nargs > 4:
		output_file = open(sys.argv[4], 'w')
	for a in input_file:
		output_file.write(s.replace(stext, rtext))
	output.close()
	input.close()

#或者用
output_file.write(input_file.read().replace(stext, rtext)) 
#需要一次读到内存里，而且维护两份，一份读一份写，速度快，耗内存，小文件可以这么些


4. 从文件中读取指定行
#用标准库中的linecache模块
import linecache
theline = linecache.getline(thefilepath, desired_line_number)


5. 计算文件的行数
#对于不大的文件，最简单的方式是每行读入一个列表，计算列表的长度
count = len(open(thefilepath, 'rU').readlines()) #rU指定通用换行符转换，无论你的代码在什么平台上运行，各种换行符都被映射成 '\n'

#对于很大的文件，可以用循环计数来解决
count = -1
for count, line in enumerate(open(thefilepath, 'rU')):
	pass
count += 1
#enumerate 函数用于遍历序列中的元素以及它们的下标
#for i,j in enumerate(('a','b','c')):
#	print i,j
#0 a
#1 b
#2 c

#如果换行标记是'\n'如windows，而不是什么其他的符号，则可以用下面的方法
count = 0
thefile = open(thefilepath, 'rb')
while True:
	buffer = thefile.read(8192*1024)
		if not buffer:
			bread
		count += buffer.count('\n')
thefile.close()


6. 处理文件中的每个词
#完成这个任务最好的办法是用两重循环，一个处理行，一个处理一行中的每个词
for line in open(thefilepath):
	for word in line.split():
		dosomethingwith(word)

#上面程序假定词是一串非空的字符，并由空白字符隔开，即单词，如果词的定义不同则需用正则
import re
re_word = re.compile(r"[\w'-']+")
for line in open(thefilepath):
	for word in re_word.finditer(line):
		dosomethingwith(word.group(0))
#finditer函数和findall函数的区别是，findall返回所有匹配的字符串，并存为一个列表，
#而finditer则并不直接返回这些字符串，而是返回一个迭代器
#>>> s=’111 222 333’
#>>> for i in re.finditer(r’\d+’ , s ):
#print i.group(),i.span() #打印每次得到的字符串和起始结束位置
#结果是
#111 (0, 3)
#222 (4, 7)
#333 (8, 11)


7. 随机输入输出
#随机读取用seek
#每条记录长48字节，二进制文件中读取第7条记录的方法（只是用于二进制）：
thefile = open('abinfile', 'rb')
record_size = 48
record_number = 6
thefile.seek(record_size * record_number)
buffer = thefile.read(record_size)
#记录从0开始，第七条下标是6


8. 更新随机存取文件
#给定一个包含很多固定长度记录的大二进制文件，需要读取其中一条记录，修改这条记录
#的某些字段值，然后写到原文件中
import struct
format_string = '8l' #一条记录是8个4字节整数
thefile = open('abinfile', 'r+b')
record_size = struct.calcsize(format_string)
record_numberb
thefile.seek(record_size * record_number)
buffer = thefile.read(record_size)
fields = list(struct.unpack(format_string, buffer))
#计算，修改后
buffer = struct.pack(format_string, *fileds) #*是对fileds list内每个元素都执行
thefile.seek(record_size * record_number)
thefile.write(buffer)
thefile.close()

struct
了解c语言的人，一定会知道struct结构体在c语言中的作用，它定义了一种结构，里面包含不同类型的数据(int,char,bool等等)，方便对某一结构对象进行处理。而在网络通信当中，
大多传递的数据是以二进制流（binary data）存在的。当传递字符串时，不必担心太多的问题，而当传递诸如int、char之类的基本数据的时候，就需要有一种机制将某些特定的结构
体类型打包成二进制流的字符串然后再网络传输，而接收端也应该可以通过某种机制进行解包还原出原始的结构体数据。python中的struct模块就提供了这样的机制，该模块的主要
作用就是对python基本类型值与用python字符串格式表示的C struct类型间的转化

stuct模块提供了很简单的几个函数
pack(fmt, v1, v2, ...)    按照给定的格式(fmt)，把数据封装成字符串(实际上是类似于c结构体的字节流)
unpack(fmt, string)       按照给定的格式(fmt)解析字节流string，返回解析出来的tuple
calcsize(fmt)             计算给定的格式(fmt)占用多少字节的内存

import struct
import binascii
values = (1, 'abc', 2.7)
#s是fmt，格式化字符串
s = struct.Struct('I3sf') #I unsigned int, s char, 3三个， f float， 具体映射信息查表
packed_data = s.pack(*values) #*对每个在values这个list里的元素都执行pack
unpacked_data = s.unpack(packed_data)

print 'Original values:', values
print 'Format string :', s.format
print 'Uses :', s.size, 'bytes'
print 'Packed Value :', binascii.hexlify(packed_data)
print 'Unpacked Type :', type(unpacked_data), ' Value:', unpacked_data

结果：
Original values: (1, 'abc', 2.7) 
Format string : I3sf 
Uses : 12 bytes 
Packed Value : 0100000061626300cdcc2c40 
Unpacked Type : <type 'tuple'>  Value: (1, 'abc', 2.700000047683716)


9. 从zip文件中读数据
#用标准库中的zipfile模块
import zipfile
z = zipfile.ZipFile("zipfile.zip", "r")
for filename in z.namelist():
	print 'File"', filename
	bytes = z.read(filename)
	print 'has', len(bytes), 'bytes'


10. cStringIO
StringIO的行为与file对象非常像，但它不是磁盘上文件，而是一个内存里的“文件”，我们可以将操作磁盘文件那样来操作StringIO。一个简单的例子，让你对StringIO有一个感性的认识：
1 	# coding=gbk
2 	 
3 	import   StringIO ,   cStringIO ,   sys
4 	 
5 	s   =   StringIO . StringIO ( " JGood   is   a   handsome   boy " )
6 	s . write ( " JGood   is   a   handsome   boy   /r/n " )
7 	s . write ( ' okkkk中国 ' )
8 	s . seek ( 0 )
9 	print   s . read ( )
10 	 
11 	# 最后4个字节
12 	s . seek ( - 4 ,   2 )
13 	print   s . read ( )
14 	 
15 	# ----   结果   ----
16 	# JGood   is   a   handsome   boy  
17 	# okkkk中国
18 	# 中国
通过例子，我们看到了StringIO的行为，基本与file一致。StringIO提供了一个方法，可以方便的获取其中的数据：StringIO. getvalue()。如果使用read方法获取其中的数据，必须通过seek先设置"文件指针"的位置。
Python标准模块中还提供了一个cStringIO模块，它的行为与StringIO基本一致，但运行效率方面比StringIO更好。但使用 cStringIO模块时，有几个注意点： 
1. cStringIO.StringIO不能作为基类被继承；
2. 创建cStringIO.StringIO对象时，如果初始化函数提供了初始化数据，新生成的对象是只读的。所以下面的代码是错误的：s = cStringIO.StringIO("JGood/n"); s.write("OOOKKK");

#处理内存中的zip文件，这个文件以字符串传进来
import cStringIO, zipfile
class ZipString(ZipFile): #继承自ZipFile
	def __init__(self, datastring):
		ZipFile.__init__(self, cStringIO.StringIO(datastring))

#处理后可以new一个，然后调用zipfile的函数来处理，就像处理文件一样，因为StringIO已经把字符串变成了类似于内存文件，可以直接以文件的方法处理


