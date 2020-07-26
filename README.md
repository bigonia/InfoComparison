## InfoComparison
抓取[巨潮网](http://www.cninfo.com.cn/new/commonUrl/pageOfSearch?url=disclosure/list/search&startDate=2020-06-05&endDate=2020-06-05#sse/1)和[上交所](http://www.sse.com.cn/disclosure/listedinfo/announcement/)的公告信息；

#### 需求描述：
- 实时抓取两个网站的公告信息；
- 当一个网站漏发某条公告时系统发出丢失提醒（A列表已发布了公告，B在1分钟后还未能发布）；
- 漏发的公告补发时系统发出补足提醒（A列表已发布了公告，B在多久后才有）；
- 每日统计：两个网站发布总量和漏发数量、漏发公告明细（即公告内容和信息源）
- 以上系统提醒均以邮件的方式自动发送到指定邮箱，邮件内容也需指定格式；
- 系统进程被杀死后可以在一段时间后自动拉起；

六月四日，论文告一段落。整理一下已完成部分和更新readme。

#### 系统结构
###### 包结构：com.infoc.
- data：主要用户原始数据的抓取和处理等；
- entity:存放实例化公告信息的实体类；使用枚举类定义了公告的数据源和状态（漏发等）；
- test：开发时的模块测试；
- utils：工具和一些配置信息；

#### 完成进度

######  截止至6.4
- 完成抓取上交所的原始数据：返回为html,请求方式为get；url:http://www.sse.com.cn/disclosure/listedinfo/bulletin/s_docdatesort_desc_2019openpdf.htm ;
- 完成抓取巨潮网的原始数据：返回为json，请求方式为post：
url:http://www.cninfo.com.cn/new/hisAnnouncement/query ;
参数：pageNum=1&seDate=2020-06-04~2020-06-04&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&sortName=&sortType=&isHLtitle=true 注意参数需要再次配置，一次无法传回所有数据；
	还有一个get方法:http://www.cninfo.com.cn/new/index/getAnnouces?type=sh,但是数据不全
- 初步定义实体类，后面看情况修改；
- 原始数据的处理，公告内容解析后以数组的形式存储在系统；
- ...

######  6.9
- 优化部分代码和功能，无新功能实现 ;
- 删除部分无用类和代码、注释；
- post请求的参数无需全部添加，大部分可以删除；
- 配置类的更新；

######  ...
- 尝试运行程序，完成对比功能；
- 实时对比，可以把两个数据源抓取到的数据分别放入两个数组，随后比较，需要重新equals和hashcode；
- 方法太笨重，换个思路，将第一组抓取到的数据放入数组，第二组每抓取一次数据，判断数组中是否存在，不存在则对其进行处理；
- 巨潮网的url又不对劲了，前前后后换了好几次了：url：http://www.cninfo.com.cn/new/disclosure；
	参数:pageNum=1&column=sse_latest&pageSize=30&sortName=&sortType=&clusterFlag=false
- 之前抓取时是抓取的次日的公告，但是突然发现巨潮网查询不到当日和次日的信息，很迷惑！

#### 具体实现总结

- url类取得连接、用流的方式取得巨潮网返回的json数据，将流传入的数据解析为String，随后转为JSONObject类型；
- 直接使用jsoup接收上交所返回的html，解析为document类型；
- 随后处理document和JSONObject，实例化公告（Announcement）对象，存入集合；
- ...

#### 未实现

- 灵活读取原始数据：两个网站的数据抓取可以统一到一个方法下；
- 计划将url、参数、邮件地址等数据放入配置文件，由utils包下的配置类读取，修改时只需要修改配置文件；
- 自动邮件提醒；
- 线程实现；

#### 近期完成
- 实时对比：1要考虑到数据量如果比较庞大的问题（这次数据量不算大、时间有限，不考虑了）、2需要计时器来判断是否超时、3数据是否要放到本地，还是一直在缓存中实时更新、
- 巨潮网不是一次返回所有的数据，需要配置post参数，系统还无法自动配置参数生成当日日期和下一页数据；

#### 遇到的问题
- post请求，参数传递没有问题、数据获取没有问题，但是拿到的数据总是不全，或者不是当天的数据，或是有看起来很奇怪的错误数据。猜测可能是：1.url还是有问题；2.参数有问题，请求的参数我只配置了几个，还有几个参数不知道是用来干嘛的，全部置空。
- post请求时，每次请求返回30组数据，参数pageNum配置页数，但是pageNum一旦超过一百，服务器就无法正常返回数据。
- 程序运行过慢；因为url传回的数据一次很少，所以要多次调用。
- 抓取到的数据总是出现问题。。。