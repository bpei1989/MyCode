1. 计算昨天和明天的日期
#遇到时间差时间转化等问题，先考虑用timedelta
import datetime
today = datetime.date.today()
yesterday = today - datetime.timedelta(days=1)
tomorrow = today + datetime.timedelta(days=1)


2. 寻找上一个星期五
import datetime,calendar
lastFriday = datetime.date.today()
oneday = datetime.timedelta(days=1)
while lastFriday.weekday() != calendar.FRIDAY
	lastFriday -= oneday
print lastFriday, strftime('%A, %d-%b-%Y')


3. 计算日期之间隔了几周
#用datetime和dateutil模块（第三方）
from dateutil import rrule
import datetime
def weeks_between(start_date, end_date):
	weeks = rrule.rrule(rrule.WEEKLY, dtstart = start_date, until = end_date)
	return weeks.count()


4. 计算歌曲的总播放时间
#用datetime模块和内建sum函数，sum的参数需为list，sum（[1,2,3]）
import datetime
def totaltime(times):
	td = datetime.timedelta(0) #初始time
	duration = sum([datetime.timedelta(minutes=m, seconds=s) for m, s in times], td)
	return duration


5. 计算两个日期之间工作日天数
from dateutil import rrule
import datetime
def workdays(start, end, holidays=0, days_off=None):
	if days_off is None:
		days_off = 5, 6 #默认周六日
	workdays = [x for x in range(7) if x not in days_off]
	days = rrule.rrule(rrule.DAILY, dtstart=start, until=end, byweekday=workdays)
	return days.count() - holidays


6. 定时执行命令
#用sched模块
import time, os, sys, sched
#初始化sched模块的scheduler类
#第一个参数是一个可以返回时间戳的函数，第二个参数可以在定时未到达之前阻塞
schedule = sched.scheduler(time.time, time.sleep)
def perform_command(cmd, inc):
#enter四个参数分别为：间隔事件、优先级（用于同时间到达的两个事件同时执行时定序）、被调用触发的函数，
#给的参数（注意：一定要以tuple给如，如果只有一个参数就(xx,)）
	schedule.enter(inc, 0, perform_command, (cmd, inc))# re-scheduler
	os.system(cmd)
def main(cmd, inc=60):
	schedule.enter(0, 0, perform_command, (cmd, inc)) #0 right now
	schedule.run()


7. 十进制结果数学计算
#decimal仍然是浮点，decimal是十进制，不是二进制，因为float二进制有时精度有问题，所以引入十进制
#Decimal和人做乘除一样，不会出现二进制里乘除精度问题，一般用于财务计算
import decimal
d1 = decimal.Decimal('0.3')
d1/3
结果： Deciaml("0.1") #结果是十进制结果，不是默认的float

(d1/3)*3
结果： Decimal("0.3")

f1 = 0.3
f1/3 #python默认0.09999999999999992
(f1/3)*3 # 0.29999999999999


8. 周期性查看汇率并提醒
#银行网站提供报告，一个CSV，用逗号隔开
#把下面程序写入crontab可以周期性执行
import httplib
import smtplib

thresholdRate = 1.30
smtpServer = 'smtp.freebie.com'
toaddrs = 'your@corp.com'

url = 'xxx'
conn = httplib.HTTPConnection('www.bankofcanada.ca')
conn.request('GET', url)
response = conn.getresponse()
data = response.read()
start = data.index('United States Dollar') #方法返回索引，如果找到这个str
line = data[start:data.index('\n', start)]
rate = line.split(',')[-1]#string以逗号分隔的最后一个子串
if float(rate) < thresholdRate:
	msg = 'Subject: Bank of Canada exchange rate %s' % rate
	server = smtplib.SMTP(smtpServer)
	server.sendmail(fromaddr, toaddrs, msg)
	server.quit()
conn.close()


#index
str.index(str, beg=0 end=len(string))
参数
str -- 此选项指定要搜索的字符串。

beg -- 这是开始索引，默认情况下是 0。

end -- 这是结束索引，默认情况下它等于该字符串的长度。

返回值
方法返回索引，如果找到这个str；如果没有找到则抛出一个异常。
str1 = "this is string example....wow!!!";
str2 = "exam";

print str1.index(str2);
print str1.index(str2, 10);
print str1.index(str2, 40);

15
15
Exception
