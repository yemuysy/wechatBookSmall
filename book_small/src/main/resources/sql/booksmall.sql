/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : booksmall

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-04-12 11:33:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) DEFAULT NULL COMMENT '名字',
  `password` varchar(36) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL COMMENT '电话',
  `address` varchar(128) DEFAULT NULL COMMENT '常用地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('10000', 'zxh', '4QrcOUm6Wau+VuBX8g+IPg==', '', '', '2019-02-18 22:10:11', '2019-02-18 22:10:11');
INSERT INTO `tb_admin` VALUES ('10001', 'zx', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, '2019-03-19 22:08:52', '2019-03-19 22:08:52');

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '书本名称',
  `author` varchar(32) NOT NULL COMMENT '书本作者',
  `price` decimal(8,2) NOT NULL COMMENT '书本单价',
  `description` text NOT NULL COMMENT '书本描述',
  `pic` text COMMENT '大图片地址(轮播图未实现）',
  `icon` varchar(128) DEFAULT NULL COMMENT '图片、小图地址',
  `stock` int(11) NOT NULL COMMENT '书本库存',
  `category_id` int(11) NOT NULL COMMENT '书本类别',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书本信息';

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('10001', '人类简史', '尤瓦尔?赫拉利', '50.80', '《人类简史：从动物到上帝》是以色列新锐历史学家尤瓦尔?赫拉利代表作品，书写从十万年前有生命迹象开始到21世纪资本、科技交织的人类发展史，将科学和历史编织在一起，从全新的角度阐述地球上智人的发展历史。\r\n', 'M00/00/00/rBIQClygh92AfetEAAAwZziaLXI477.jpg,\r\nM00/00/00/rBIQClygh_2AZLo6AAMrc8zKuu4802.jpg,\r\nM00/00/00/rBIQClygiByAIp8-AAAGgdeXzTY587.jpg,M00/00/00/rBIQClygiRKAG5iQAAT_u9YxbpY906.jpg', 'M00/00/00/rBIQClygh92AfetEAAAwZziaLXI477.jpg', '100', '4', '2019-03-31 17:32:30', '2019-04-01 17:09:15');
INSERT INTO `tb_book` VALUES ('10002', '显微镜下的大明', '马伯庸', '36.90', '本书讲述的，是六个深藏于故纸堆中的明代基层政治事件。 作者从明代的一系列罕见民间档案文书里，挖掘出这些尘封已久的故事。这些档案是中国历史中独有的奇迹，它们着眼于平民的政治生活，而且记录极为详尽。在这里，我们能看到朴实的百姓诉求、狡黠的民间智慧、肮脏的胥吏手段、微妙的官场均衡之术，从无数个真实的细节里，展现出一幅极其鲜活的政治生态图景。 这六个事件聚焦于一府一县乃至一村之内，记录的是最底层平民的真实政治生活：当遭遇税收不公时，他们如何愤起抗争；当家族权益受到损害时，他们如何兴起诉讼；当政治利益与商业利益发生矛盾，他们如何与官府周旋博弈；当朝廷要求整顿户籍，他们又是如何从中造假牟利…… 《学霸必须死——徽州丝绢案始末》：万历年间，一项不公正的税收政策在徽州府引发了旷日持久的混乱。乱民、县官、州府、户部、首辅、皇帝等诸多利益集团的博弈之局。 《笔与灰的抉择——婺源龙脉保卫战》：婺源县一场持续了六十四年的经济争端，反映了一个县级官员，是如何在重大议题上平衡一县之利害的。 《谁动了我的祖庙——杨干院律政风云》：歙县一桩民间庙产争夺案。诉讼双方在这场绵延八年的官司中各展所长，用尽心思，上演了一场精彩绝伦的嘉靖法律大戏。 《天下透明——大明第一档案库的前世今生》：一个坐落于后湖（玄武湖）中的冷门机构——大明黄册库从建立到衰败的全过程。了解明代的基层统治，是如何一步步垮掉的。 《胥吏的盛宴——彭县小吏舞弊案》：一件小到不能再小的官司，却引来了无数贪婪蚊虫的叮咬。胥吏之恶，被刻画得淋漓尽致。 《正统年间的四条冤魂》：四个无辜的清白老百姓，是如何裹挟入朝廷斗争的。 作者力图以冷静克制的零度叙事，替那些生于尘埃、死于无闻的蝼蚁之辈作传，转述他们湮没于宏达历史中的声音。想要读懂大明，想要读懂中国古代政治，不可只注目于朝堂，亦要听到最底层的呐喊。在一个个普通人的遭遇中，才蕴藏着最真实的规律。', 'M00/00/00/rBIQClygjByAZDdcAARVVRHr5m8401.jpg,M00/00/00/rBIQClygjDqAdZvrAAt6C3jTRwU278.jpg', 'M00/00/00/rBIQClygjByAZDdcAARVVRHr5m8401.jpg', '100', '4', '2019-03-31 17:45:58', '2019-04-01 17:09:13');
INSERT INTO `tb_book` VALUES ('10003', '空间简史', '托马斯·马卡卡罗', '25.80', '自人类第yi次用步伐丈量大地，探索空间的欲望便从未停止。“我们在哪里”这个看似简单的问题，从人类有空间认知以来，答案总是暂时性的，不断被新的发现更替。\r\n　　在早期文明中，先人用神话确立“哪里”的zui初答案。在青铜器时代，人们已经创造出复杂的概念地图，并且能够利用星星找到方向。在中世纪，计算和导航工具更加完善，一个新大陆的发现彻底改变了我们对“哪里”的认知，随之而来的探索迅速填满了古老羊皮纸地图上的空白。在当今时代，望远镜的镜头拉近了我们与天空的距离，新的行星被发现。行星迅速变成星系，新的理论正逐渐重塑整个宇宙。\r\n　　过往的理论或被证实，或被推翻，但我们探索空间的脚步从未停歇，“哪里”这个不断变化的概念也在等着“后来的我们”去开拓.', 'M00/00/00/rBIQClygjXmAC8LtAAFDgZPYWgs674.jpg,M00/00/00/rBIQClygjZiAKz84AAGAjuVRIZM662.jpg', 'M00/00/00/rBIQClygjXmAC8LtAAFDgZPYWgs674.jpg', '100', '4', '2019-03-31 17:50:26', '2019-04-01 17:08:12');
INSERT INTO `tb_book` VALUES ('10004', '\r\n中世纪人', '艾琳·鲍尔', '34.56', '《中世纪人》（Medieval People）初版于1924年，是艾琳·鲍尔著名代表作。\r\n《中世纪人》一书中，艾琳·鲍尔通过账本、日记、书信、记录、遗嘱及其他一些权威的文献资料，生动地再现了中世纪时6个普通人的生活。使我们得以一瞥中世纪生活的方方面面：农民生活、修道生活、羊毛交易、威尼斯与东方的贸易活动、中产阶级的家庭生活等等。\r\n《中世纪人》以之前没有的生动、细致和深刻，引人入胜地呈现了中世纪生活方方面面的丰满和绚烂的画面。书中生动的插图和作者幽默的笔触，使得这部学术著作极具可读性。无论是历史学专业的学生或老师，还是对中世纪生活感兴趣的普通人，这本书都一定能使之浮想联翩、受益不浅.', 'M00/00/00/rBIQClygjqOAHmChAAPDmvMSMTM549.jpg,M00/00/00/rBIQClygjouAMnUnAAGucbQ8lio465.jpg', 'M00/00/00/rBIQClygjqOAHmChAAPDmvMSMTM549.jpg', '100', '4', '2019-03-31 17:54:21', '2019-04-01 17:08:14');
INSERT INTO `tb_book` VALUES ('10005', '万历十五年', '黄仁宇', '32.50', '《万历十五年》内容简介：明万历十五年，即公元1587年，在中国历史上原本是极其普通的年份。作者以该年前后的史事件及生活在那个时代的人物为中心，抽丝剥茧，梳理了中国传统社会管理层面存在的种种问题，并在此基础上探索现代中国应当涉取的经验和教训。作者黄仁宇以其“大历史”观而闻名于世，《万历十五年》中这一观念初露头角，“叙事不妨细致，但是结论却要看远不顾近”。《万历十五年》自80年代初在中国大陆出版以来，好评如潮，在学术界和文化界有广泛的影响。 \r\n《万历十五年》意在说明16世纪中国社会的传统的历史背景，也就是尚未与世界潮流冲突时的侧面形态。有了这样一个历史的大失败，就可以保证冲突既开，恢复故态决无可能，因之而给中国留下了一个翻天覆地、彻底创造历史的机缘。', 'M00/00/00/rBIQClygj0mAb17-AAlPjOIbozU800.jpg', 'M00/00/00/rBIQClygj0mAb17-AAlPjOIbozU800.jpg', '100', '4', '2019-03-31 17:58:17', '2019-04-01 17:08:15');
INSERT INTO `tb_book` VALUES ('10006', '算法图解', '巴尔加瓦', '41.50', '本书示例丰富，图文并茂，以让人容易理解的方式阐释了算法，旨在帮助程序员在日常项目中更好地发挥算法的能量。书中的前三章将帮助你打下基础，带你学习二分查找、大O表示法、两种基本的数据结构以及递归等。余下的篇幅将主要介绍应用广泛的算法，具体内容包括：面对具体问题时的解决技巧，比如，何时采用贪婪算法或动态规划；散列表的应用；图算法；Kzui近邻算法。', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '100', '1', '2019-03-31 18:01:52', '2019-04-01 17:08:15');
INSERT INTO `tb_book` VALUES ('10007', '\r\nPython编程 从入门到实践', '埃里克·马瑟斯', '44.50', '本书是一本针对所有层次的Python读者而作的Python入门书。全书分两部分：首部分介绍用Python 编程所必须了解的基本概念，包括matplotlib、NumPy和Pygal等强大的Python库和工具介绍，以及列表、字典、if语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的Python 2D游戏开发，如何利用数据生成交互式的信息图，以及创建和定制简单的Web应用，并帮读者解决常见编程问题和困惑。\r\n本书是一本针对所有层次的Python读者而作的Python入门书。全书分两部分：首部分介绍用Python 编程所必须了解的基本概念，包括matplotlib、NumPy和Pygal等强大的Python库和工具介绍，以及列表、字典、if语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的Python 2D游戏开发，如何利用数据生成交互式的信息图，以及创建和定制简单的Web应用，并帮读者解决常见编程问题和困惑。\r\n本书是一本针对所有层次的Python读者而作的Python入门书。全书分两部分：首部分介绍用Python 编程所必须了解的基本概念，包括matplotlib、NumPy和Pygal等强大的Python库和工具介绍，以及列表、字典、if语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的Python 2D游戏开发，如何利用数据生成交互式的信息图，以及创建和定制简单的Web应用，并帮读者解决常见编程问题和困惑。', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '100', '1', '2019-03-31 19:37:59', '2019-04-01 17:08:16');
INSERT INTO `tb_book` VALUES ('10008', 'C Primer Plus（第6版）中文版', '史蒂芬·普拉达', '74.50', '《C Primer Plus（第6版）中文版》详细讲解了C语言的基本概念和编程技巧。\r\n\r\n《C Primer Plus（第6版）中文版》共17章。第1、2章介绍了C语言编程的预备知识。第3~~15章详细讲解了C语言的相关知识，包括数据类型、格式化输入/输出、运算符、表达式、语句、循环、字符输入和输出、函数、数组和指针、字符和字符串函数、内存管理、文件输入输出、结构、位操作等。第16章、17章介绍C预处理器、C库和高级数据表示。本书以完整的程序为例，讲解C语言的知识要点和注意事项。每章末设计了大量复习题和编程练习，帮助读者巩固所学知识和提高实际编程能力。附录给出了各章复习题的参考答案和丰富的参考资料。\r\n《C Primer Plus（第6版）中文版》可作为C语言的教材，适用于需要系统学习C语言的初学者，也适用于巩固C语言知识或希望进一步提高编程技术的程序员。', 'M00/00/00/rBIQClygqCuAZNMKAABqchIgKeA616.jpg', 'M00/00/00/rBIQClygqCuAZNMKAABqchIgKeA616.jpg', '100', '1', '2019-03-31 19:43:15', '2019-04-01 17:08:17');
INSERT INTO `tb_book` VALUES ('10009', '深入理解Java虚拟机：JVM高级特性与最佳实践（第2版）', '周志明', '24.99', '第1版两年内印刷近10次，4家网上书店的评论近4?000条，98%以上的评论全部为5星级的好评，是整个Java图书领域公认的经典著作和超级畅销书，繁体版在台湾也十分受欢迎。第2版在第1版的基础上做了很大的改进：根据最新的JDK 1.7对全书内容进行了全面的升级和补充；增加了大量处理各种常见JVM问题的技巧和实践；增加了若干与生产环境相结合的实战案例；对第1版中的错误和不足之处的修正；等等。第2版不仅技术更新、内容更丰富，而且实战性更强。', 'M00/00/00/rBIQClygqOqAFMSEAABqchIgKeA181.jpg', 'M00/00/00/rBIQClygqOqAFMSEAABqchIgKeA181.jpg', '100', '1', '2019-03-31 19:47:38', '2019-04-01 17:08:18');
INSERT INTO `tb_book` VALUES ('10010', '重构：改善既有代码的设计', '马丁·福勒', '58.80', '本书清晰揭示了重构的过程，解释了重构的原理和践方式，并给出了何时以及何地应该开始挖掘代码以求改善。书中给出了70多个可行的重构，每个重构都介绍了一种经过验证的代码变换手法的动机和技术。本书提出的重构准则将帮助你一次一小步地修改你的代码，从而减少了开发过程中的风险。', 'M00/00/00/rBIQClygqbGAXNfQAAC39f7KMVk430.jpg', 'M00/00/00/rBIQClygqbGAXNfQAAC39f7KMVk430.jpg', '100', '1', '2019-03-31 19:52:51', '2019-04-01 17:08:19');
INSERT INTO `tb_book` VALUES ('10011', '\r\nJava编程的逻辑 (Java核心技术系列) ', '马俊昌', '71.23', '写一本关于编程的书，是我大概15年前就有的一个想法，当时，我体会到了编程中数据结构的美妙和神奇，有一种收获的喜悦和分享的冲动。这种收获是我反复阅读教程十几遍，花大量时间上机练习调试得到的，这是一个比较痛苦的过程。我想，如果把我学到的知识更为清晰易懂地表达出来，其他人不就可以掌握编程容易一些，并体会到那种喜悦了吗？不过，当时感觉自己学识太浅，要学习的东西太多，想一想也就算了。', 'M00/00/00/rBIQClygqquAMZfQAAA1kZOR8x0509.jpg', 'M00/00/00/rBIQClygqquAMZfQAAA1kZOR8x0509.jpg', '100', '1', '2019-03-31 19:55:33', '2019-04-01 17:08:20');
INSERT INTO `tb_book` VALUES ('10012', '重新定义Spring Cloud实战', '许进 ', '96.80', '在微服务体系中，Spring Cloud是目前最热门的构建微服务体系的解决方案，它提供了构建微服务架构的一些基础设施。本书内容上覆盖了Spring Cloud的一些主要组件，不仅在如何使用上做了详细的介绍，也从原理上深入浅出地剖析了其中的技术要点，同时也将部分组件与周边的一些开源项目进行了对比，且提供了一些原理分析和相关的示例，是一本不可多得的Spring Cloud实战书籍。新手和有微服务实践经验的读者都能从书中得到一些不一样的收获', 'M00/00/00/rBIQClygqxuAGSi1AACLlugTJis038.jpg', 'M00/00/00/rBIQClygqxuAGSi1AACLlugTJis038.jpg', '100', '1', '2019-03-31 19:57:00', '2019-04-01 17:08:20');
INSERT INTO `tb_book` VALUES ('10013', 'Java编程思想(第4版)', '埃史尔', '98.50', '《Java编程思想(第4版)》书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究、Iava’UO系统、枚举类型、并发以及图形化用户界面等内容。这些丰富的内容，包含了Java语言基础语法以及高级特性，适合各个层次的Java程序员阅读，同时也是高等院校讲授面向对象程序设计语言以及Java语言的绝佳教材和参考书。\r\n从《Java编程思想(第4版)》一书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作。本书的作者拥有多年教学经验，对c、c++以及Java语言都有独到、深入的见解，以通俗易懂及小而直接的示例解释了一个个晦涩抽象的概念。', 'M00/00/00/rBIQClygq_SAL9rRAACRTKdXv-0517.jpg', 'M00/00/00/rBIQClygq_SAL9rRAACRTKdXv-0517.jpg', '100', '1', '2019-03-31 19:59:49', '2019-04-01 17:08:21');
INSERT INTO `tb_book` VALUES ('10014', 'Effective Java中文版（原书第3版）', '约书亚·布洛克', '97.40', '此书没有描述', 'M00/00/00/rBIQClygrVqAC3aDAABqaHFSbdo959.jpg,M00/00/00/rBIQClygrXKAL9lFAAXjfmJefUY029.jpg', 'M00/00/00/rBIQClygrVqAC3aDAABqaHFSbdo959.jpg', '100', '1', '2019-03-31 20:07:25', '2019-03-31 20:07:25');
INSERT INTO `tb_book` VALUES ('10015', 'Java并发编程实战', '盖茨', '57.20', '《Java并发编程实战》深入浅出地介绍了Java线程和并发,是一本完美的Java并发参考手册。书中从并发性和线程安全性的基本概念出发,介绍了如何使用类库提供的基本并发构建块,用于避免并发危险、构造线程安全的类及验证线程安全的规则,如何将小的线程安全类组合成更大的线程安全类,如何利用线程来提高并发应用程序的吞吐量,如何识别可并行执行的任务,如何提高单线程子系统的响应性,如何确保并发程序执行预期任务,如何提高并发代码的性能和可伸缩性等内容,最后介绍了一些高级主题,如显式锁、原子变量、非阻塞算法以及如何开发自定义的同步工具类。\r\n《Java并发编程实战》适合Java程序开发人员阅读。', 'M00/00/00/rBIQClygreSAVZwRAABTAh2NhcY130.jpg', 'M00/00/00/rBIQClygreSAVZwRAABTAh2NhcY130.jpg', '100', '1', '2019-03-31 20:10:10', '2019-03-31 20:10:10');
INSERT INTO `tb_book` VALUES ('10016', 'Spring源码深度解析（第2版）', '郝佳', '74.50', '《Spring源码深度解析（第2版）》从核心实现、企业应用和Spring Boot这3个方面，由浅入深、由易到难地对Spring源码展开了系统的讲解，包括Spring 整体架构和环境搭建、容器的基本实现、默认标签的解析、自定义标签的解析、bean的加载、容器的功能扩展、AOP、数据库连接JDBC、整合MyBatis、事务、SpringMVC、远程服务、Spring消息、Spring Boot体系原理等内容。 《Spring源码深度解析（第2版）》不仅介绍了使用Spring框架开发项目必须掌握的核心概念，还指导读者使用Spring框架编写企业级应用，并针对在编写代码的过程中如何优化代码、如何使得代码高效给出了切实可行的建议，从而帮助读者全面提升实战能力。 《Spring源码深度解析（第2版）》语言简洁，示例丰富，可帮助读者迅速掌握使用Spring进行开发所需的各种技能。本书适合于已具有一定Java编程基础的读者，以及在Java平台下进行各类软件开发的开发人员、测试人员等。', 'M00/00/00/rBIQClygrrSAAtQPAAA9sWY1U2E679.jpg', 'M00/00/00/rBIQClygrrSAAtQPAAA9sWY1U2E679.jpg', '100', '1', '2019-03-31 20:12:01', '2019-03-31 20:12:46');
INSERT INTO `tb_book` VALUES ('10017', '人生海海', '麦家', '40.60', '“他是全村ZUI出奇古怪的人，古怪的名目要扳着指头一个一个数：\r\n第·一，他当过国民党上校，是革命群众要斗争的对象。但大家一边斗争他，一边又巴结讨好他，家里出什么事都去找他拿主意。\r\n第二，说他是太监，可我们小孩子经常偷看他那个地方，好像还是满当当的，有模有样的。\r\n第三，他向来不出工，不干农活，天天空在家里看报纸，嗑瓜子，可日子过得比谁家都舒坦。还像养孩子一样养着一对猫，宝贝得不得了，简直神经病！”\r\n《人生海海》是茅盾文学奖得主麦家于2019年推出的全新长篇小说，悬念迭出，气度恢弘。故事背景跨越近一个世纪，巧用孩童视角，围绕一个待解谜团，讲述了一个人在时代中穿行缠斗的一生，离奇的故事里藏着让人悠长叹息的人生况味。\r\n人生似海，装载着时代、传奇与人心，既有日常滋生的残酷，也有时间带来的仁慈。', 'M00/00/00/rBIQClygr7KAUKmhAABsHoEFSFY086.jpg,M00/00/00/rBIQClygr9SAOIfhAAGlNt9niEQ426.jpg', 'M00/00/00/rBIQClygr7KAUKmhAABsHoEFSFY086.jpg', '100', '2', '2019-03-31 20:16:03', '2019-03-31 20:17:35');
INSERT INTO `tb_book` VALUES ('10018', '鲁滨孙漂流记', '笛福', '33.00', '热爱冒险的鲁滨孙几次出海闯荡，遇到海难被困在荒岛之上。二十八年的孤岛生活仍然未能阻止他的冒险精神。面对死亡的威胁，他凭着坚忍的毅力，在蛮荒之地白手起家，造房子、修田地、种植粮食、驯化野兽，与食人族大战，勇夺叛变水手控制的商船，历尽千辛万苦，终于脱险，得到可观财富，成为一个时代的传奇人物。', 'M00/00/00/rBIQClygsJOAWy6vAAOu_g8Azp8447.jpg,M00/00/00/rBIQClygsKqARnZtAALbRgRUY6M020.jpg', 'M00/00/00/rBIQClygsJOAWy6vAAOu_g8Azp8447.jpg', '100', '2', '2019-03-31 20:19:58', '2019-03-31 20:21:19');
INSERT INTO `tb_book` VALUES ('10019', '所有失去的都会以另一种方式归来', '耿帅', '28.30', '换一个城市是不是更快乐、离开现在的工作是不是就会有前途、结束一段关系是不是就幸福了，这些困扰的问题你都会从他的书里找到答案。他用文字来告诉每一个人，所有的困境都是来自内在的心境，单身不可怕，失恋不可怕，可怕的是失去爱的能力。\r\n这是一份你应该送给自己，并与生命中所有朋友分享的礼物。亦或，送给你在乎的人，有了Ta，世界才完整。\r\n你也可以把这本书放在枕边，放在书架，别太介意得失，要相信：所有失去的都会以另一种方式归来。', 'M00/00/00/rBIQClygsYmAf73fAAC3NuhBU5Q471.jpg,M00/00/00/rBIQClygsamARyTpAABt50mrZ3U053.jpg', 'M00/00/00/rBIQClygsYmAf73fAAC3NuhBU5Q471.jpg', '100', '3', '2019-03-31 20:24:08', '2019-03-31 20:25:23');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类目编号',
  `name` varchar(64) NOT NULL COMMENT '类目名字',
  `icon` varchar(64) DEFAULT NULL COMMENT '类目图片地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('0', '推荐', null, '2019-03-29 16:33:05', '2019-03-29 16:33:13');
INSERT INTO `tb_category` VALUES ('1', '计算机', '', '2019-02-17 19:08:38', '2019-02-17 19:09:01');
INSERT INTO `tb_category` VALUES ('2', '文学', null, '2019-03-29 16:33:36', '2019-03-31 20:21:43');
INSERT INTO `tb_category` VALUES ('3', '心理学', null, '2019-03-29 16:33:48', '2019-03-29 16:33:48');
INSERT INTO `tb_category` VALUES ('4', '历史', null, '2019-03-29 16:34:06', '2019-03-29 16:34:06');
INSERT INTO `tb_category` VALUES ('5', '其他', null, '2019-04-01 09:19:01', '2019-04-01 09:19:01');

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL COMMENT '主表id',
  `book_id` varchar(32) NOT NULL COMMENT '书本id',
  `book_name` varchar(64) NOT NULL COMMENT '商品名称',
  `book_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `book_quantity` int(11) NOT NULL COMMENT '商品数量',
  `book_icon` varchar(512) DEFAULT NULL COMMENT '商品小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES ('DETAIL155417181961755', 'MASTER155417181961669', '10006', '算法图解', '41.50', '3', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-02 10:23:39', '2019-04-02 10:23:39');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155417181961761', 'MASTER155417181961669', '10007', '\r\nPython编程 从入门到实践', '44.50', '4', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-02 10:23:39', '2019-04-02 10:23:39');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155419600231611', 'MASTER155419600231697', '10006', '算法图解', '41.50', '3', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-02 17:06:42', '2019-04-02 17:06:42');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155419600231639', 'MASTER155419600231697', '10007', '\r\nPython编程 从入门到实践', '44.50', '6', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-02 17:06:42', '2019-04-02 17:06:42');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422317069414', 'MASTER155422317069354', '10009', '深入理解Java虚拟机：JVM高级特性与最佳实践（第2版）', '24.99', '3', 'M00/00/00/rBIQClygqOqAFMSEAABqchIgKeA181.jpg', '2019-04-03 00:39:30', '2019-04-03 00:39:30');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422327431201', 'MASTER155422327431252', '10007', '\r\nPython编程 从入门到实践', '44.50', '4', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-03 00:41:14', '2019-04-03 00:41:14');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422358220728', 'MASTER155422358220764', '10011', '\r\nJava编程的逻辑 (Java核心技术系列) ', '71.23', '4', 'M00/00/00/rBIQClygqquAMZfQAAA1kZOR8x0509.jpg', '2019-04-03 00:46:22', '2019-04-03 00:46:22');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422359471287', 'MASTER155422359471282', '10012', '重新定义Spring Cloud实战', '96.80', '4', 'M00/00/00/rBIQClygqxuAGSi1AACLlugTJis038.jpg', '2019-04-03 00:46:34', '2019-04-03 00:46:34');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422360609300', 'MASTER155422360609326', '10006', '算法图解', '41.50', '3', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-03 00:46:46', '2019-04-03 00:46:46');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422382608036', 'MASTER155422382608054', '10007', '\r\nPython编程 从入门到实践', '44.50', '4', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-03 00:50:26', '2019-04-03 00:50:26');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422389136400', 'MASTER155422389136450', '10007', '\r\nPython编程 从入门到实践', '44.50', '3', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-03 00:51:31', '2019-04-03 00:51:31');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422394597211', 'MASTER155422394597281', '10013', 'Java编程思想(第4版)', '98.50', '4', 'M00/00/00/rBIQClygq_SAL9rRAACRTKdXv-0517.jpg', '2019-04-03 00:52:25', '2019-04-03 00:52:25');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422416622226', 'MASTER155422416622207', '10008', 'C Primer Plus（第6版）中文版', '74.50', '3', 'M00/00/00/rBIQClygqCuAZNMKAABqchIgKeA616.jpg', '2019-04-03 00:56:06', '2019-04-03 00:56:06');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422433885326', 'MASTER155422433885285', '10006', '算法图解', '41.50', '3', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-03 00:58:58', '2019-04-03 00:58:58');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155422490537470', 'MASTER155422490537474', '10006', '算法图解', '41.50', '5', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-03 01:08:25', '2019-04-03 01:08:25');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155425217378668', 'MASTER155425217378573', '10006', '算法图解', '41.50', '10', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-03 08:42:53', '2019-04-03 08:42:53');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155438340551805', 'MASTER155438340551726', '10007', '\r\nPython编程 从入门到实践', '44.50', '5', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-04 21:10:05', '2019-04-04 21:10:05');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155438710749666', 'MASTER155438710749677', '10006', '算法图解', '41.50', '5', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-04 22:11:47', '2019-04-04 22:11:47');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155438726064700', 'MASTER155438726064701', '10008', 'C Primer Plus（第6版）中文版', '74.50', '3', 'M00/00/00/rBIQClygqCuAZNMKAABqchIgKeA616.jpg', '2019-04-04 22:14:20', '2019-04-04 22:14:20');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155438726064716', 'MASTER155438726064701', '10006', '算法图解', '41.50', '3', 'M00/00/00/rBIQClygkHyAYVnrAAEVUrjZE-s077.jpg', '2019-04-04 22:14:20', '2019-04-04 22:14:20');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155444732104887', 'MASTER155444732104782', '10007', '\r\nPython编程 从入门到实践', '44.50', '1', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-05 14:55:21', '2019-04-05 14:55:21');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155448332994006', 'MASTER155448332994091', '10007', '\r\nPython编程 从入门到实践', '44.50', '5', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-06 00:55:29', '2019-04-06 00:55:29');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155448332994073', 'MASTER155448332994091', '10008', 'C Primer Plus（第6版）中文版', '74.50', '4', 'M00/00/00/rBIQClygqCuAZNMKAABqchIgKeA616.jpg', '2019-04-06 00:55:29', '2019-04-06 00:55:29');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155448353633684', 'MASTER155448353633685', '10007', '\r\nPython编程 从入门到实践', '44.50', '3', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-06 00:58:56', '2019-04-06 00:58:56');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155448649400108', 'MASTER155448649400061', '10008', 'C Primer Plus（第6版）中文版', '74.50', '2', 'M00/00/00/rBIQClygqCuAZNMKAABqchIgKeA616.jpg', '2019-04-06 01:48:14', '2019-04-06 01:48:14');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155448677292420', 'MASTER155448677292444', '10007', '\r\nPython编程 从入门到实践', '44.50', '3', 'M00/00/00/rBIQClygp4KAZlWrAADhQ_26WYc824.jpg', '2019-04-06 01:52:52', '2019-04-06 01:52:52');
INSERT INTO `tb_order_detail` VALUES ('DETAIL155448699348493', 'MASTER155448699348481', '10011', '\r\nJava编程的逻辑 (Java核心技术系列) ', '71.23', '2', 'M00/00/00/rBIQClygqquAMZfQAAA1kZOR8x0509.jpg', '2019-04-06 01:56:33', '2019-04-06 01:56:33');

-- ----------------------------
-- Table structure for tb_order_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_master`;
CREATE TABLE `tb_order_master` (
  `id` varchar(32) NOT NULL,
  `user_name` varchar(32) NOT NULL COMMENT '买家名字',
  `user_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `user_address` varchar(128) NOT NULL COMMENT '买家地址',
  `user_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `amount` decimal(8,2) NOT NULL COMMENT '订单总额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0：新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0：未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_buyer_openid` (`user_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of tb_order_master
-- ----------------------------
INSERT INTO `tb_order_master` VALUES ('MASTER155417181961669', '夜幕', '13112361981', '咋回事打算', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '302.50', '0', '1', '2019-04-02 10:23:39', '2019-04-02 10:23:39');
INSERT INTO `tb_order_master` VALUES ('MASTER155419600231697', '夜幕1', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '391.50', '0', '1', '2019-04-02 17:06:42', '2019-04-02 17:06:42');
INSERT INTO `tb_order_master` VALUES ('MASTER155422317069354', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '74.97', '0', '1', '2019-04-03 00:39:30', '2019-04-03 00:39:30');
INSERT INTO `tb_order_master` VALUES ('MASTER155422327431252', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '178.00', '0', '1', '2019-04-03 00:41:14', '2019-04-03 00:41:14');
INSERT INTO `tb_order_master` VALUES ('MASTER155422358220764', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '284.92', '0', '1', '2019-04-03 00:46:22', '2019-04-03 00:46:22');
INSERT INTO `tb_order_master` VALUES ('MASTER155422359471282', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '387.20', '0', '1', '2019-04-03 00:46:34', '2019-04-03 00:46:34');
INSERT INTO `tb_order_master` VALUES ('MASTER155422360609326', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '124.50', '0', '1', '2019-04-03 00:46:46', '2019-04-03 00:46:46');
INSERT INTO `tb_order_master` VALUES ('MASTER155422382608054', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '178.00', '0', '1', '2019-04-03 00:50:26', '2019-04-03 00:50:26');
INSERT INTO `tb_order_master` VALUES ('MASTER155422389136450', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '133.50', '0', '1', '2019-04-03 00:51:31', '2019-04-03 00:51:31');
INSERT INTO `tb_order_master` VALUES ('MASTER155422394597281', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '394.00', '0', '1', '2019-04-03 00:52:25', '2019-04-03 00:52:25');
INSERT INTO `tb_order_master` VALUES ('MASTER155422416622207', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '223.50', '0', '1', '2019-04-03 00:56:06', '2019-04-03 00:56:06');
INSERT INTO `tb_order_master` VALUES ('MASTER155422433885285', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '124.50', '0', '1', '2019-04-03 00:58:58', '2019-04-03 00:58:58');
INSERT INTO `tb_order_master` VALUES ('MASTER155422490537474', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '207.50', '0', '1', '2019-04-03 01:08:25', '2019-04-03 01:08:25');
INSERT INTO `tb_order_master` VALUES ('MASTER155425217378573', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '415.00', '0', '1', '2019-04-03 08:42:53', '2019-04-03 08:42:53');
INSERT INTO `tb_order_master` VALUES ('MASTER155438340551726', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '222.50', '0', '1', '2019-04-04 21:10:05', '2019-04-04 21:10:05');
INSERT INTO `tb_order_master` VALUES ('MASTER155438710749677', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '207.50', '0', '1', '2019-04-04 22:11:47', '2019-04-04 22:11:47');
INSERT INTO `tb_order_master` VALUES ('MASTER155438726064701', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '348.00', '0', '1', '2019-04-04 22:14:20', '2019-04-04 22:14:20');
INSERT INTO `tb_order_master` VALUES ('MASTER155444732104782', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '44.50', '0', '1', '2019-04-05 14:55:21', '2019-04-05 14:55:21');
INSERT INTO `tb_order_master` VALUES ('MASTER155448332994091', '张三', '020-81167888', '广东省 广州市 海珠区 新港中路397号', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '520.50', '0', '1', '2019-04-06 00:55:29', '2019-04-06 00:55:29');
INSERT INTO `tb_order_master` VALUES ('MASTER155448353633685', '张三', '020-81167888', '广东省 广州市 海珠区 新港中路397号', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '133.50', '0', '1', '2019-04-06 00:58:56', '2019-04-06 00:58:56');
INSERT INTO `tb_order_master` VALUES ('MASTER155448649400061', '张三', '020-81167888', '广东省 广州市 海珠区 新港中路397号', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '149.00', '0', '1', '2019-04-06 01:48:14', '2019-04-06 01:48:14');
INSERT INTO `tb_order_master` VALUES ('MASTER155448677292444', '张三', '020-81167888', '广东省 广州市 海珠区 新港中路397号', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '133.50', '0', '1', '2019-04-06 01:52:52', '2019-04-06 01:52:52');
INSERT INTO `tb_order_master` VALUES ('MASTER155448699348481', '张鑫鸿', '13112361981', '广东省 珠海市 金湾区 珠海大道南侧 广东科学技术职业学院', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', '142.46', '0', '1', '2019-04-06 01:56:33', '2019-04-06 01:56:33');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(32) DEFAULT NULL COMMENT '名字',
  `phone` char(11) DEFAULT NULL COMMENT '电话',
  `address` varchar(128) DEFAULT NULL COMMENT '常用地址',
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `avatar_url` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_buyer_openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('8', '夜幕', '13112361981', '广东省珠海市金湾区', 'oCbqW5CzNavxZ3buA3wsiw-tFfF0', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eommichqcyfprmn2K5nicotdxXJelRdEnSTXJoyJwGwiahkoLFJj1EaZia73PeOFjEBMvZ4UUaKDl80dg/132', '2019-04-02 09:51:59', '2019-04-03 00:36:05');
