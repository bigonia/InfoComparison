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

#### 已完成

######  截止至6.4
- 完成抓取上交所的原始数据：返回为html,请求方式为get；url: ;
- 完成抓取巨潮网的原始数据：返回为json，请求方式为post：url:http://www.cninfo.com.cn/new/hisAnnouncement/query ;参数：pageNum=1&seDate=2020-06-04~2020-06-04&pageSize=30&column=szse&tabName=fulltext&plate=sh&stock=&searchkey=&secid=&category=&trade=&sortName=&sortType=&isHLtitle=true 注意参数需要再次配置，一次无法传回所有数据；
- 初步定义实体类，后面看情况修改；
- 原始数据的处理，公告内容解析后以lis的形式存储在系统；
- ...

######  6.9
- 优化部分代码和功能，无新功能实现 ;
- 删除部分无用类和代码、注释；
- post请求的参数无需全部添加，大部分可以删除；
- 配置类的更新；


#### 具体实现

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