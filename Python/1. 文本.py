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


12. 替换字符串某些子串
#该函数返回一个输入字符串的拷贝，该拷贝中的所有能在字典中找到的
#子串都被替换为字典中对应值
import re
def multiple_replace(text, adict):
	rx = re.compile('|'.join(map(re.escape, adict)))
	def one_xlat(match): 
		return adict[match.group(0)]
	return rx.sub(one_xlat, text)
	
上面这个函数首先根据想要匹配的key创建一个正则表达式，这个正则表达式的
写法:a1|a2|...|aN，由N个需要被替换的字符串组成，并由竖线隔开，然后不直接
给re.sub传递用于替换的字符串，而是传入一个回调函数，这样匹配一次re.sub就会调用
这个回调函数，并将re.MatchObject实例作为唯一参数传递给该回调函数，并期望该
回调函数返回作为替换的字符串

re.escape(string)
返回一个字符串, 其中的所有非字母数字字符都带有反斜杠。

python中的map、filter、reduce函数 
1. map函数
map函数会根据提供的函数对指定序列做映射。
map函数的定义：
map(function, sequence[, sequence, ...]) -> list
通过定义可以看到，这个函数的第一个参数是一个函数，剩下的参数是一个或多个序列，返回值是一个集合。
function可以理解为是一个一对一或多对一函数，map的作用是以参数序列中的每一个元素调用function函数，返回包含每次function函数返回值的list。
比如要对一个序列中的每个元素进行平方运算：
map(lambda x: x ** 2, [1, 2, 3, 4, 5])
返回结果为：
[1, 4, 9, 16, 25]
在参数存在多个序列时，会依次以每个序列中相同位置的元素做参数调用function函数。
比如要对两个序列中的元素依次求和。
map(lambda x, y: x + y, [1, 3, 5, 7, 9], [2, 4, 6, 8, 10])
map返回的list中第一个元素为，参数序列1的第一个元素加参数序列2中的第一个元素(1 + 2)，
list中的第二个元素为，参数序列1中的第二个元素加参数序列2中的第二个元素(3 + 4)，
依次类推，最后的返回结果为：
[3, 7, 11, 15, 19]
要注意function函数的参数数量，要和map中提供的集合数量相匹配。
如果集合长度不相等，会以最小长度对所有集合进行截取。
当函数为None时，操作和zip相似：
map(None, [1, 3, 5, 7, 9], [2, 4, 6, 8, 10])
返回结果为：
[(1, 2), (3, 4), (5, 6), (7, 8), (9, 10)]

2. filter函数
filter函数会对指定序列执行过滤操作。
filter函数的定义：
filter(function or None, sequence) -> list, tuple, or string
function是一个谓词函数，接受一个参数，返回布尔值True或False。
filter函数会对序列参数sequence中的每个元素调用function函数，最后返回的结果包含调用结果为True的元素。
返回值的类型和参数sequence的类型相同
比如返回序列中的所有偶数：
def is_even(x):
return x & 1 != 0

filter(is_even, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
返回结果为：
[1, 3, 5, 7, 9]
如果function参数为None，返回结果和sequence参数相同。
filter还可以过滤字符串中的数字，字母等，isdigit

3.reduce函数
reduce函数，reduce函数会对参数序列中元素进行累积。
reduce函数的定义：
reduce(function, sequence[, initial]) -> value
function参数是一个有两个参数的函数，reduce依次从sequence中取一个元素，和上一次调用function的结果做参数再次调用function。
第一次调用function时，如果提供initial参数，会以sequence中的第一个元素和initial作为参数调用function，否则会以序列sequence中的前两个元素做参数调用function。
reduce(lambda x, y: x + y, [2, 3, 4, 5, 6], 1)
结果为21(  (((((1+2)+3)+4)+5)+6)  )
reduce(lambda x, y: x + y, [2, 3, 4, 5, 6])
结果为20
注意function函数不能为None。





python re
这个模块提供了与 Perl 相似l的正则表达式匹配操作。Unicode字符串也同样适用。

 

正则表达式使用反斜杠" \ "来代表特殊形式或用作转义字符，这里跟Python的语法冲突，因此，Python用" \\\\ "表示正则表达式中的" \ "，因为正则表达式中如果要匹配" \ "，需要用\来转义，变成" \\ "，而Python语法中又需要对字符串中每一个\进行转义，所以就变成了" \\\\ "。

 

上面的写法是不是觉得很麻烦，为了使正则表达式具有更好的可读性，Python特别设计了原始字符串(raw string)，需要提醒你的是，在写文件路径的时候就不要使用raw string了，这里存在陷阱。raw string就是用'r'作为字符串的前缀，如 r"\n"：表示两个字符"\"和"n"，而不是换行符了。Python中写正则表达式时推荐使用这种形式。

 

绝大多数正则表达式操作与 模块级函数或RegexObject方法 一样都能达到同样的目的。而且不需要你一开始就编译正则表达式对象，但是不能使用一些实用的微调参数。

 

1.正则表达式语法

        为了节省篇幅，这里不再叙述了。

 

2.martch和search的区别

        Python提供了两种不同的原始操作：match和search。match是从字符串的起点开始做匹配，而search（perl默认）是从字符串做任意匹配。

 

        注意：当正则表达式是' ^ '开头时，match与search是相同的。match只有当且仅当被匹配的字符串开头就能匹配 或 从pos参数的位置开始就能匹配 时才会成功。如下：

>>> import re 
>>> re.match("c", "abcdef") 
>>> re.search("c","abcdef") 
<_sre.SRE_Match object at 0x00A9A988>

>>> re.match("c", "cabcdef") 
<_sre.SRE_Match object at 0x00A9AB80>

>>> re.search("c","cabcdef") 
<_sre.SRE_Match object at 0x00AF1720>

>>> patterm = re.compile("c") 
>>> patterm.match("abcdef") 
>>> patterm.match("abcdef",1) 
>>> patterm.match("abcdef",2) 
<_sre.SRE_Match object at 0x00A9AB80>

3.模块内容

re.compile(pattern, flags=0)

 

编译正则表达式，返回RegexObject对象，然后可以通过RegexObject对象调用match()和search()方法。

 

prog = re.compile(pattern)

result = prog.match(string)

跟

result = re.match(pattern, string)

是等价的。

 

第一种方式能实现正则表达式的重用。

 

re.search(pattern, string, flags=0)

 

在字符串中查找，是否能匹配正则表达式。返回_sre.SRE_Match对象，如果不能匹配返回None。

 

re.match(pattern, string, flags=0)

 

字符串的开头是否能匹配正则表达式。返回_sre.SRE_Match对象，如果不能匹配返回None。

 

re.split(pattern, string, maxsplit=0)

 

通过正则表达式将字符串分离。如果用括号将正则表达式括起来，那么匹配的字符串也会被列入到list中返回。maxsplit是分离的次数，maxsplit=1分离一次，默认为0，不限制次数。

>>> re.split('\W+', 'Words, words, words.') 
['Words', 'words', 'words', ''] 
>>> re.split('(\W+)', 'Words, words, words.') 
['Words', ', ', 'words', ', ', 'words', '.', ''] 
>>> re.split('\W+', 'Words, words, words.', 1) 
['Words', 'words, words.'] 
>>> re.split('[a-f]+', '0a3B9', flags=re.IGNORECASE)

 

注意：我使用的Python是2.6，查看源代码发现split()并没有flags的参数，2.7才增加。这种问题我发现不止一次了，官方的文档 跟 源码不一致的现象，如果发现异常，应该去源码中找找原因。

 

如果在字符串的开始或结尾就匹配，返回的list将会以空串开始或结尾。

>>> re.split('(\W+)', '...words, words...') 
['', '...', 'words', ', ', 'words', '...', '']

 

如果字符串不能匹配，将会返回整个字符串的list。

>>> re.split("a","bbb") 
['bbb']

 

re.findall(pattern, string, flags=0)

 

找到 RE 匹配的所有子串，并把它们作为一个列表返回。这个匹配是从左到右有序地返回。如果无匹配，返回空列表。

>>> re.findall("a","bcdef") 
[]

>>> re.findall(r"\d+","12a32bc43jf3") 
['12', '32', '43', '3']

 

re.finditer(pattern, string, flags=0)

 

找到 RE 匹配的所有子串，并把它们作为一个迭代器返回。这个匹配是从左到右有序地返回。如果无匹配，返回空列表。

>>> it = re.finditer(r"\d+","12a32bc43jf3") 
>>> for match in it: 
              print match.group()

12 
32 
43 
3

 

re.sub(pattern, repl, string, count=0, flags=0)

 

找到 RE 匹配的所有子串，并将其用一个不同的字符串替换。可选参数 count 是模式匹配後替换的最大次数；count 必须是非负整数。缺省值是 0 表示替换所有的匹配。如果无匹配，字符串将会无改变地返回。

 

re.subn(pattern, repl, string, count=0, flags=0)

 

与re.sub方法作用一样，但返回的是包含新字符串和替换执行次数的两元组。

 

re.escape(string)

 

对字符串中的非字母数字进行转义

 

re.purge()

 

清空缓存中的正则表达式

 

4.正则表达式对象

 

re.RegexObject

 

re.compile()返回RegexObject对象

 

re.MatchObject

 

group()返回被 RE 匹配的字符串

start()返回匹配开始的位置

end()返回匹配结束的位置

span()返回一个元组包含匹配 (开始,结束) 的位置

 

5.编译标志

编译标志让你可以修改正则表达式的一些运行方式。在 re 模块中标志可以使用两个名字，一个是全名如 IGNORECASE，一个是缩写，一字母形式如 I。（如果你熟悉 Perl 的模式修改，一字母形式使用同样的字母；例如 re.VERBOSE的缩写形式是 re.X。）多个标志可以通过按位 OR-ing 它们来指定。如 re.I | re.M 被设置成 I 和 M 标志：

I 
IGNORECASE

使匹配对大小写不敏感；字符类和字符串匹配字母时忽略大小写。举个例子，[A-Z]也可以匹配小写字母，Spam 可以匹配 "Spam", "spam", 或 "spAM"。这个小写字母并不考虑当前位置。

L 
LOCALE

影响 "w, "W, "b, 和 "B，这取决于当前的本地化设置。

locales 是 C 语言库中的一项功能，是用来为需要考虑不同语言的编程提供帮助的。举个例子，如果你正在处理法文文本，你想用 "w+ 来匹配文字，但 "w 只匹配字符类 [A-Za-z]；它并不能匹配 "é" 或 "?"。如果你的系统配置适当且本地化设置为法语，那么内部的 C 函数将告诉程序 "é" 也应该被认为是一个字母。当在编译正则表达式时使用 LOCALE 标志会得到用这些 C 函数来处理 "w 後的编译对象；这会更慢，但也会象你希望的那样可以用 "w+ 来匹配法文文本。

M 
MULTILINE

(此时 ^ 和 $ 不会被解释; 它们将在 4.1 节被介绍.)

使用 "^" 只匹配字符串的开始，而 $ 则只匹配字符串的结尾和直接在换行前（如果有的话）的字符串结尾。当本标志指定後， "^" 匹配字符串的开始和字符串中每行的开始。同样的， $ 元字符匹配字符串结尾和字符串中每行的结尾（直接在每个换行之前）。

S 
DOTALL

使 "." 特殊字符完全匹配任何字符，包括换行；没有这个标志， "." 匹配除了换行外的任何字符。

X 
VERBOSE

该标志通过给予你更灵活的格式以便你将正则表达式写得更易于理解。当该标志被指定时，在 RE 字符串中的空白符被忽略，除非该空白符在字符类中或在反斜杠之後；这可以让你更清晰地组织和缩进 RE。它也可以允许你将注释写入 RE，这些注释会被引擎忽略；注释用 "#"号 来标识，不过该符号不能在字符串或反斜杠之後。

最后：如果能用字符串的方法，就不要选择正则表达式，因为字符串方法更简单快速。


13. Unicode
#将Unicode转为普通python字符串，encode
unicodestring = u"Hello world"
uft8string = unicodestring.encode("utf-8")
asxiistring = unicodestring.encode("ascii")

#将普通python字符串转化为Unicode，decode
plainstring1 = unicode(utf8string, "utf-8")
plainstring2 = unicode(asciistring, "ascii")
assert plainstring1 == plainstring2


