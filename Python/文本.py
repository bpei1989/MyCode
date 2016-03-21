1. 每次处理一个字符
# 调用内建的list，用字符串作为参数
thelist = list(thestring)

#也可以不创建一个列表，直接用for
for c in thestring:
	do_something_with(c)

#或者用列表推导中的for
result = [do_something_with(c) for c in thestring]

#再或者，可以用内建的map函数，每取得一个字符就调一次处理函数
result = map(do_something, thestring)


2. 字符和字符值之间的转换
#将一个字符转化为ASCII或Unicode，或反过来
#用ord和chr
print ord('a') #结果97
print chr(97) #结果a
print ord(u'\u2020') #结果8224
print repr(unichr(8224)) #结果u'\u2020'

print repr(chr(97)) #结果a
print repr(str(97)) #结果97

print map(ord, 'ciao') #结果[99, 105, 97, 111]
print ''.join(map(chr, range(97, 100)) #结果abc


3. 测试一个对象是否为字符串类型
#内建isinstance和basestring来检测是否是字符串或Unicode对象
def isAString(anobj):
	return isinstance(anobj, basestring)
	

4. 字符串对齐
#用ljust，rjust，center
print 'hey'.center(20, '+') #结果++++++++++hey++++++++++


5. 去除字符串两端的空格
#用lstrip，rstrip，strip
#还可以选择去除其他字符，不仅空格，只需提供一个字符串
s = 'xyxxyy heyyx yyx'
print '|' + s.strip('xy') + '|' #结果| heyyx |
#这个例子中最后获得的字符串的开头和结尾的空格都被保留，因为yx后面接的是一些空格，
#只有开头和结尾的x和y被移除了


6. 合并字符串
largeString = ''.join(pieces)
largeString  = '%s%s something %s yet more' % (small0, small1, small2) #将变量写入string
largeString = 'a' + 'b' + 'c' #还可以用+


7. 将字符串逐个字符或逐个词反转
revchars = astring[::-1] # 步长为-1， 逐个字符反转
revwords = astring.split() #字符串->单词列表
revwords.reverse() #反转列表
revwords = ' '.join(revwords) #单词列表->字符串
revwords = ' '.join(asring.splot()[::-1]) #一行解决按词反转


8. 检查字符串中是否出现了某字符集合中的字符
def containsAny(seq, aset):
	for c in seq:
		if c in aset: return True
	return False
	
#一个set a， a.difference(b) 返回a中所有不属于b的元素
L1 = [1, 2, 3, 3]
L2 = [1, 2, 3, 4]
set(L1).difference(L2) #结果set([ ])
set(L2).difference(L1) #结果set([4])

def containsAll(seq, aset):
	"""检查序列seq是否含有aset的所有项"""
	return not set(aset).difference(seq)
	
	
9. 替换某些字符串
Python maketrans() 方法用于创建字符映射的转换表，对于接受两个参数的最简单的调用方式，第一个参数是字符串，表示需要转换的字符，第二个参数也是字符串表示转换的目标。
注：两个字符串的长度必须相同，为一一对应的关系。

#!/usr/bin/python
# -*- coding: UTF-8 -*-

from string import maketrans   # 必须调用 maketrans 函数。

intab = "aeiou"
outtab = "12345"
trantab = maketrans(intab, outtab)

str = "this is string example....wow!!!";
print str.translate(trantab);


10. 控制大小写
#用upper lower
#还有capitalize titile
print 'one tWo thrEe'.capitalize() #结果One two three
print 'one tWo thrEe'.titile() #结果One Two Three


11. 访问子字符串
#切片
afild = theline[3:8]

#zip
#zip函数接受任意多个（包括0个和1个）序列作为参数，返回一个tuple列表。
x = [1, 2, 3]
y = [4, 5, 6]
z = [7, 8, 9]
xyz = zip(x, y, z)
print xyz

Result:
[(1, 4, 7), (2, 5, 8), (3, 6, 9)]

x = [1, 2, 3]
y = [4, 5, 6, 7]
xy = zip(x, y)
print xy

Result:
[(1, 4), (2, 5), (3, 6)]





