package com.bfdelivery.cavaliers.background.server.bean.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Panoo on 2017/8/6.
 */
public class OrderList {


	/**
	 * current_page : 1
	 * data : [{"number":"UmW8cMwHuoGx","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":1,"product_amount":381373,"service_amount":411,"total_amount":34852,"coupon_amount":26283,"coupon_id":0,"pay_amount":462771,"remark":"一呼四应！Sun是太阳，Java是月亮。因为网络，地球如村！深入成就深度","tags":"阳光总在风雨后","distribute_time":"VQgr0g1kHb","distribute_status":0,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24","order_products":[{"id":97,"product_id":689,"name":"科技有限公司之毛秀兰","num":9,"packing_box_num":0,"packing_box_price":6842,"specs":[],"price":31865,"remark":"网络有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":97,"name":"楚博","sex":2,"mobile":"18399877155","post_code":"052300","province":"台湾省","city":"西宁","area":"滨城区","detail":"只溶在口，不溶在手。","longitude":"119.713124","latitude":"58.445796","tag":"家里"},"distribute":{"id":97,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"0ZipaM8iTXtA","shop_id":1,"user_id":1,"status":0,"pay_status":0,"pay_type":1,"product_amount":212711,"service_amount":460,"total_amount":46280,"coupon_amount":32913,"coupon_id":0,"pay_amount":359995,"remark":"如同情人的手Sun是太阳，Java是月亮。立邦漆：处处放光彩！灵活，让篮球场不再是一个平面","tags":"卫浴出出进进的快感","distribute_time":"3YjzZdAhMB","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":72,"product_id":439,"name":"网络有限公司之程东","num":3,"packing_box_num":3,"packing_box_price":4984,"specs":[],"price":92973,"remark":"传媒有限公司科技有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":72,"name":"祝岩","sex":0,"mobile":"13258145428","post_code":"843400","province":"陕西省","city":"北京","area":"东丽区","detail":"一呼天下应","longitude":"131.141160","latitude":"145.844133","tag":"家里"},"distribute":{"id":72,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"irBVWWW9sub9","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":1,"product_amount":404782,"service_amount":168,"total_amount":37349,"coupon_amount":44565,"coupon_id":0,"pay_amount":386176,"remark":"让无力者有力，让悲观者前行以帽取人！新飞广告做的好，不好新飞冰箱好中国移动通信，沟通从心开始！","tags":"创新就是生活","distribute_time":"nXDpIUJ0oc","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":67,"product_id":978,"name":"传媒有限公司之温勇","num":10,"packing_box_num":0,"packing_box_price":7452,"specs":[],"price":60847,"remark":"传媒有限公司网络有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":67,"name":"沙明霞","sex":2,"mobile":"13683387322","post_code":"852600","province":"湖北省","city":"乌鲁木齐","area":"南湖区","detail":"健康成就未来。","longitude":"18.546283","latitude":"34.258162","tag":"办事处"},"distribute":{"id":67,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"w7Hv9pdU7GdU","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":0,"product_amount":340921,"service_amount":387,"total_amount":17928,"coupon_amount":23494,"coupon_id":0,"pay_amount":284189,"remark":"爱的就是你!建筑无限生活永远的绿色，永远的秦池。阳光总在风雨后","tags":"支起网络世界","distribute_time":"Ij3eEoOqUf","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":62,"product_id":537,"name":"传媒有限公司之迟伦","num":5,"packing_box_num":3,"packing_box_price":3966,"specs":[],"price":14147,"remark":"信息有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":62,"name":"巩佳","sex":2,"mobile":"15590829825","post_code":"126300","province":"上海市","city":"武汉","area":"金平区","detail":"谁让我心动？","longitude":"124.717675","latitude":"110.098632","tag":"家里"},"distribute":{"id":62,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"oc5gdgVnwuYX","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":0,"product_amount":23835,"service_amount":119,"total_amount":35877,"coupon_amount":38821,"coupon_id":0,"pay_amount":133940,"remark":"更多选择、更多欢笑一呼四应！减脂减肥，其实是一种生活态度人类失去联想，世界将会怎样？","tags":"众里寻他千百度，想要几度就几度","distribute_time":"F0UDFV4FXj","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":60,"product_id":130,"name":"信息有限公司之练艳","num":9,"packing_box_num":0,"packing_box_price":4272,"specs":[],"price":45158,"remark":"信息有限公司传媒有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":60,"name":"简杨","sex":0,"mobile":"15521090943","post_code":"603000","province":"四川省","city":"西宁","area":"长寿区","detail":"众里寻他千百度，想要几度就几度","longitude":"27.767156","latitude":"136.855536","tag":"办事处"},"distribute":{"id":60,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"DWwuMym685WR","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":0,"product_amount":236075,"service_amount":169,"total_amount":21045,"coupon_amount":10937,"coupon_id":0,"pay_amount":121682,"remark":"做女人挺好！我们一直在努力！柯达：串起生活每一刻聆听并不代表沉默，有时安静也是一种力量。","tags":"明天的明天，你还会送我\u201c水晶之恋\u201d吗？","distribute_time":"5J1CytI3BP","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":56,"product_id":147,"name":"科技有限公司之鲍强","num":2,"packing_box_num":0,"packing_box_price":8846,"specs":[],"price":55091,"remark":"信息有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":56,"name":"丘依琳","sex":0,"mobile":"13568589731","post_code":"467200","province":"新疆维吾尔自治区","city":"南京","area":"淄川区","detail":"弹指一挥间，世界皆互联","longitude":"7.886370","latitude":"177.025963","tag":"办事处"},"distribute":{"id":56,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"SNxfWxQq5dKR","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":1,"product_amount":330455,"service_amount":109,"total_amount":18631,"coupon_amount":19311,"coupon_id":0,"pay_amount":431550,"remark":"鹤舞白沙没有最人类失去联想，世界将会怎样？没有蛀牙-佳洁士","tags":"让无力者有力，让悲观者前行","distribute_time":"kIxTkZJCQi","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":43,"product_id":958,"name":"网络有限公司之陶正业","num":2,"packing_box_num":2,"packing_box_price":9204,"specs":[],"price":75085,"remark":"网络有限公司网络有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":43,"name":"余楼","sex":0,"mobile":"18397806379","post_code":"158700","province":"宁夏回族自治区","city":"杭州","area":"大东区","detail":"钻石恒久远，一颗永流传。","longitude":"11.574143","latitude":"117.683491","tag":"办事处"},"distribute":{"id":43,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"5ks3BUYitt4b","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":0,"product_amount":48020,"service_amount":315,"total_amount":16876,"coupon_amount":26318,"coupon_id":0,"pay_amount":225571,"remark":"尝尝欢笑，经常麦当劳一切尽在把握支起网络世界共创美的前程，共度美的人生。","tags":"彩信发送动人一刻","distribute_time":"Fvqr22PAP2","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":42,"product_id":80,"name":"科技有限公司之蔺凤兰","num":5,"packing_box_num":2,"packing_box_price":9974,"specs":[],"price":1074,"remark":"信息有限公司网络有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":42,"name":"田鹏程","sex":0,"mobile":"13758233702","post_code":"361400","province":"辽宁省","city":"兰州","area":"上街区","detail":"不断创新，因为专心","longitude":"122.645063","latitude":"137.078240","tag":"办事处"},"distribute":{"id":42,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"fpglSiwXP2yM","shop_id":1,"user_id":1,"status":0,"pay_status":0,"pay_type":1,"product_amount":209323,"service_amount":435,"total_amount":31369,"coupon_amount":19013,"coupon_id":0,"pay_amount":318248,"remark":"洁婷245再大的动作也不要紧由我天地宽更多选择、更多欢笑人人都为礼品愁，我送北极海狗油。","tags":"不要让男人一手把握","distribute_time":"UhPgszBQ2r","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":41,"product_id":610,"name":"网络有限公司之褚利","num":8,"packing_box_num":3,"packing_box_price":5243,"specs":[],"price":83154,"remark":"科技有限公司科技有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":41,"name":"郎文娟","sex":0,"mobile":"15627686003","post_code":"405700","province":"内蒙古自治区","city":"香港","area":"魏都区","detail":"聪明演绎，无处不在！","longitude":"40.198219","latitude":"110.628109","tag":"公司"},"distribute":{"id":41,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"5FGB26VlmcXJ","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":0,"product_amount":497466,"service_amount":436,"total_amount":49353,"coupon_amount":46260,"coupon_id":0,"pay_amount":48233,"remark":"盛满青春的秘密！金利来\u2014-男人的世界！请记住，上帝并不是十全十美的，它给汽车预备了备件，而人没有。网易，网聚人的力量！","tags":"一直被模拟,从未被超越","distribute_time":"GwnhWEUCtX","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":40,"product_id":574,"name":"传媒有限公司之温君","num":5,"packing_box_num":3,"packing_box_price":5476,"specs":[],"price":43430,"remark":"科技有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":40,"name":"娄松","sex":2,"mobile":"15794004373","post_code":"768400","province":"贵州省","city":"银川","area":"清河区","detail":"中国网通","longitude":"107.006951","latitude":"121.357413","tag":"办事处"},"distribute":{"id":40,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"ZrHvru6ickPB","shop_id":1,"user_id":1,"status":0,"pay_status":0,"pay_type":0,"product_amount":171516,"service_amount":438,"total_amount":29860,"coupon_amount":23279,"coupon_id":0,"pay_amount":296471,"remark":"如同情人的手别吻我，我怕修。想知道\u201c清嘴\u201d的味道吗？由我天地宽","tags":"庄重一生，吉祥一生。","distribute_time":"1He00ChN42","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":39,"product_id":83,"name":"传媒有限公司之全洪","num":2,"packing_box_num":1,"packing_box_price":3181,"specs":[],"price":11664,"remark":"科技有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":39,"name":"龚明","sex":2,"mobile":"13363540349","post_code":"348100","province":"云南省","city":"贵阳","area":"清河区","detail":"我是、我行、我素","longitude":"112.747386","latitude":"41.125053","tag":"办事处"},"distribute":{"id":39,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"IAMwIsNklKTq","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":0,"product_amount":383758,"service_amount":490,"total_amount":21373,"coupon_amount":28970,"coupon_id":0,"pay_amount":54155,"remark":"更多选择、更多欢笑不信，死给你看！弹指一挥间，世界皆互联要想皮肤好，早晚用大宝。","tags":"人头马一开，好事自然来。","distribute_time":"RIBvEPSYpe","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":35,"product_id":442,"name":"传媒有限公司之崔博涛","num":2,"packing_box_num":0,"packing_box_price":9436,"specs":[],"price":20946,"remark":"网络有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":35,"name":"栗梅","sex":2,"mobile":"15283655165","post_code":"776200","province":"安徽省","city":"拉萨","area":"高明区","detail":"有线的价值","longitude":"145.389004","latitude":"38.391636","tag":"公司"},"distribute":{"id":35,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"YHis4gvTZOcZ","shop_id":1,"user_id":1,"status":0,"pay_status":0,"pay_type":0,"product_amount":409173,"service_amount":217,"total_amount":22136,"coupon_amount":38629,"coupon_id":0,"pay_amount":340640,"remark":"如同情人的手一品黄山天高云淡如同情人的手爱的就是你!","tags":"创意似金，敬业如牛","distribute_time":"7mDHGfg5TR","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":28,"product_id":614,"name":"科技有限公司之戴明霞","num":3,"packing_box_num":3,"packing_box_price":4712,"specs":[],"price":84142,"remark":"科技有限公司网络有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":28,"name":"林颖","sex":2,"mobile":"18151621189","post_code":"256800","province":"甘肃省","city":"昆明","area":"金水区","detail":"大众甲克虫汽车：想想还是小的好","longitude":"179.479818","latitude":"128.209449","tag":"家里"},"distribute":{"id":28,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"TLtjBydjqKwf","shop_id":1,"user_id":1,"status":0,"pay_status":1,"pay_type":0,"product_amount":381974,"service_amount":261,"total_amount":13896,"coupon_amount":24154,"coupon_id":0,"pay_amount":378711,"remark":"不断创新，因为专心有家就有联合利华只要有梦想庄重一生，吉祥一生。","tags":"听世界，打天下","distribute_time":"RPdIyyGlSN","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":25,"product_id":811,"name":"网络有限公司之聂欣","num":5,"packing_box_num":1,"packing_box_price":6892,"specs":[],"price":83139,"remark":"科技有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":25,"name":"司腊梅","sex":0,"mobile":"14509050398","post_code":"828500","province":"重庆市","city":"沈阳","area":"安次区","detail":"当之无愧","longitude":"50.304946","latitude":"114.413234","tag":"家里"},"distribute":{"id":25,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}},{"number":"VMFl2cs5KMgA","shop_id":1,"user_id":1,"status":0,"pay_status":0,"pay_type":0,"product_amount":209371,"service_amount":215,"total_amount":44311,"coupon_amount":30846,"coupon_id":0,"pay_amount":288558,"remark":"如同情人的手事业我一定争取，对你我从未放弃!鄂尔多斯羊绒衫暖和全世界永远的绿色，永远的秦池。","tags":"小身材，大味道。","distribute_time":"4oM94E4V6F","distribute_status":0,"created_at":"2017-08-21 15:16:23","updated_at":"2017-08-21 15:16:23","order_products":[{"id":18,"product_id":684,"name":"科技有限公司之陆建明","num":10,"packing_box_num":3,"packing_box_price":9682,"specs":[],"price":33944,"remark":"信息有限公司传媒有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}],"shop":{"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25},"address":{"order_id":18,"name":"徐丽华","sex":2,"mobile":"15715836155","post_code":"701300","province":"北京市","city":"重庆","area":"清浦区","detail":"自然最健康，绿色好心情","longitude":"120.820362","latitude":"37.070371","tag":"办事处"},"distribute":{"id":18,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}}]
	 * from : 1
	 * last_page : 2
	 * next_page_url : http://bf.laowaishuosha.com/v1/distributer/orders?page=2
	 * path : http://bf.laowaishuosha.com/v1/distributer/orders
	 * per_page : 15
	 * prev_page_url : null
	 * to : 15
	 * total : 22
	 */

	private int current_page;
	private int from;
	private int last_page;
	private String next_page_url;
	private String path;
	private int per_page;
	private String prev_page_url;
	private int to;
	private int total;
	private List<DataBean> data;

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getLast_page() {
		return last_page;
	}

	public void setLast_page(int last_page) {
		this.last_page = last_page;
	}

	public String getNext_page_url() {
		return next_page_url;
	}

	public void setNext_page_url(String next_page_url) {
		this.next_page_url = next_page_url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPer_page() {
		return per_page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public String getPrev_page_url() {
		return prev_page_url;
	}

	public void setPrev_page_url(String prev_page_url) {
		this.prev_page_url = prev_page_url;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * number : UmW8cMwHuoGx
		 * shop_id : 1
		 * user_id : 1
		 * status : 0
		 * pay_status : 1
		 * pay_type : 1
		 * product_amount : 381373
		 * service_amount : 411
		 * total_amount : 34852
		 * coupon_amount : 26283
		 * coupon_id : 0
		 * pay_amount : 462771
		 * remark : 一呼四应！Sun是太阳，Java是月亮。因为网络，地球如村！深入成就深度
		 * tags : 阳光总在风雨后
		 * distribute_time : VQgr0g1kHb
		 * distribute_status : 0
		 * created_at : 2017-08-21 15:16:24
		 * updated_at : 2017-08-21 15:16:24
		 * order_products : [{"id":97,"product_id":689,"name":"科技有限公司之毛秀兰","num":9,"packing_box_num":0,"packing_box_price":6842,"specs":[],"price":31865,"remark":"网络有限公司信息有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}]
		 * shop : {"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25}
		 * address : {"order_id":97,"name":"楚博","sex":2,"mobile":"18399877155","post_code":"052300","province":"台湾省","city":"西宁","area":"滨城区","detail":"只溶在口，不溶在手。","longitude":"119.713124","latitude":"58.445796","tag":"家里"}
		 * distribute : {"id":97,"status":1,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}
		 */

		private String number;
		private int shop_id;
		private int user_id;
		private int status;
		private int pay_status;
		private int pay_type;
		private int product_amount;
		private int service_amount;
		private int total_amount;
		private int coupon_amount;
		private int coupon_id;
		private int pay_amount;
		private String remark;
		private String distribute_time;
		private int distribute_status;
		private String created_at;
		private String updated_at;
		private ShopBean shop;
		private AddressBean address;
		private DistributeBean distribute;
		private List<OrderProductsBean> order_products;

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public int getShop_id() {
			return shop_id;
		}

		public void setShop_id(int shop_id) {
			this.shop_id = shop_id;
		}

		public int getUser_id() {
			return user_id;
		}

		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getPay_status() {
			return pay_status;
		}

		public void setPay_status(int pay_status) {
			this.pay_status = pay_status;
		}

		public int getPay_type() {
			return pay_type;
		}

		public void setPay_type(int pay_type) {
			this.pay_type = pay_type;
		}

		public int getProduct_amount() {
			return product_amount;
		}

		public void setProduct_amount(int product_amount) {
			this.product_amount = product_amount;
		}

		public int getService_amount() {
			return service_amount;
		}

		public void setService_amount(int service_amount) {
			this.service_amount = service_amount;
		}

		public int getTotal_amount() {
			return total_amount;
		}

		public void setTotal_amount(int total_amount) {
			this.total_amount = total_amount;
		}

		public int getCoupon_amount() {
			return coupon_amount;
		}

		public void setCoupon_amount(int coupon_amount) {
			this.coupon_amount = coupon_amount;
		}

		public int getCoupon_id() {
			return coupon_id;
		}

		public void setCoupon_id(int coupon_id) {
			this.coupon_id = coupon_id;
		}

		public int getPay_amount() {
			return pay_amount;
		}

		public void setPay_amount(int pay_amount) {
			this.pay_amount = pay_amount;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getDistribute_time() {
			return distribute_time;
		}

		public void setDistribute_time(String distribute_time) {
			this.distribute_time = distribute_time;
		}

		public int getDistribute_status() {
			return distribute_status;
		}

		public void setDistribute_status(int distribute_status) {
			this.distribute_status = distribute_status;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getUpdated_at() {
			return updated_at;
		}

		public void setUpdated_at(String updated_at) {
			this.updated_at = updated_at;
		}

		public ShopBean getShop() {
			return shop;
		}

		public void setShop(ShopBean shop) {
			this.shop = shop;
		}

		public AddressBean getAddress() {
			return address;
		}

		public void setAddress(AddressBean address) {
			this.address = address;
		}

		public DistributeBean getDistribute() {
			return distribute;
		}

		public void setDistribute(DistributeBean distribute) {
			this.distribute = distribute;
		}

		public List<OrderProductsBean> getOrder_products() {
			return order_products;
		}

		public void setOrder_products(List<OrderProductsBean> order_products) {
			this.order_products = order_products;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class ShopBean {
			/**
			 * id : 1
			 * name : 鸿睿思博加盟店
			 * banner : http://lorempixel.com/640/480/?25977
			 * description : 为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！
			 * longitude : 123.452878
			 * latitude : 41.725355
			 * address : 事业我一定争取，对你我从未放弃!
			 * phone : 18767863835
			 * status : 0
			 * send_amount : 4556
			 * service_amount : 6730
			 * sell_times : {"friday":[["13 30","15 15"],["23 30","23 15"]]}
			 * average_minutes : 25
			 */

			private int id;
			private String name;
			private String banner;
			private String description;
			private double longitude;
			private double latitude;
			private String address;
			private String phone;
			private int status;
			private int send_amount;
			private int service_amount;
			//			private SellTimesBean sell_times;
			private int average_minutes;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getBanner() {
				return banner;
			}

			public void setBanner(String banner) {
				this.banner = banner;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public double getLongitude() {
				return longitude;
			}

			public void setLongitude(double longitude) {
				this.longitude = longitude;
			}

			public double getLatitude() {
				return latitude;
			}

			public void setLatitude(double latitude) {
				this.latitude = latitude;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			public int getStatus() {
				return status;
			}

			public void setStatus(int status) {
				this.status = status;
			}

			public int getSend_amount() {
				return send_amount;
			}

			public void setSend_amount(int send_amount) {
				this.send_amount = send_amount;
			}

			public int getService_amount() {
				return service_amount;
			}

			public void setService_amount(int service_amount) {
				this.service_amount = service_amount;
			}

//			public SellTimesBean getSell_times() {
//				return sell_times;
//			}
//
//			public void setSell_times(SellTimesBean sell_times) {
//				this.sell_times = sell_times;
//			}

			public int getAverage_minutes() {
				return average_minutes;
			}

			public void setAverage_minutes(int average_minutes) {
				this.average_minutes = average_minutes;
			}

			public static class SellTimesBean {
				private List<List<String>> friday;

				public List<List<String>> getFriday() {
					return friday;
				}

				public void setFriday(List<List<String>> friday) {
					this.friday = friday;
				}
			}
		}

		public static class AddressBean {
			/**
			 * order_id : 97
			 * name : 楚博
			 * sex : 2
			 * mobile : 18399877155
			 * post_code : 052300
			 * province : 台湾省
			 * city : 西宁
			 * area : 滨城区
			 * detail : 只溶在口，不溶在手。
			 * longitude : 119.713124
			 * latitude : 58.445796
			 * tag : 家里
			 */

			private int order_id;
			private String name;
			private int sex;
			private String mobile;
			private String post_code;
			private String province;
			private String city;
			private String area;
			private String detail;
			private String house_number;
			private double longitude;
			private double latitude;
			private String tag;

			public int getOrder_id() {
				return order_id;
			}

			public void setOrder_id(int order_id) {
				this.order_id = order_id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getSex() {
				return sex;
			}

			public void setSex(int sex) {
				this.sex = sex;
			}

			public String getMobile() {
				return mobile;
			}

			public void setMobile(String mobile) {
				this.mobile = mobile;
			}

			public String getPost_code() {
				return post_code;
			}

			public void setPost_code(String post_code) {
				this.post_code = post_code;
			}

			public String getProvince() {
				return province;
			}

			public void setProvince(String province) {
				this.province = province;
			}

			public String getCity() {
				return city;
			}

			public void setCity(String city) {
				this.city = city;
			}

			public String getArea() {
				return area;
			}

			public void setArea(String area) {
				this.area = area;
			}

			public String getDetail() {
				return detail;
			}

			public void setDetail(String detail) {
				this.detail = detail;
			}

			public String getHouse_number() {
				return house_number;
			}

			public void setHouse_number(String house_number) {
				this.house_number = house_number;
			}

			public double getLongitude() {
				return longitude;
			}

			public void setLongitude(double longitude) {
				this.longitude = longitude;
			}

			public double getLatitude() {
				return latitude;
			}

			public void setLatitude(double latitude) {
				this.latitude = latitude;
			}

			public String getTag() {
				return tag;
			}

			public void setTag(String tag) {
				this.tag = tag;
			}
		}

		public static class DistributeBean {
			/**
			 * id : 97
			 * status : 1
			 * distributer_id : 1
			 * distributer_name : 配送员姓名
			 * deleted_at : null
			 * created_at : 2017-08-21 15:16:24
			 * updated_at : 2017-08-21 15:16:24
			 */

			private int id;
			private int status;
			private int distributer_id;
			private String distributer_name;
			private String deleted_at;
			private String created_at;
			private String updated_at;
			private String distributer_phone;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getStatus() {
				return status;
			}

			public void setStatus(int status) {
				this.status = status;
			}

			public int getDistributer_id() {
				return distributer_id;
			}

			public void setDistributer_id(int distributer_id) {
				this.distributer_id = distributer_id;
			}

			public String getDistributer_name() {
				return distributer_name;
			}

			public void setDistributer_name(String distributer_name) {
				this.distributer_name = distributer_name;
			}

			public String getDeleted_at() {
				return deleted_at;
			}

			public void setDeleted_at(String deleted_at) {
				this.deleted_at = deleted_at;
			}

			public String getCreated_at() {
				return created_at;
			}

			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}

			public String getUpdated_at() {
				return updated_at;
			}

			public void setUpdated_at(String updated_at) {
				this.updated_at = updated_at;
			}

			public String getDistributer_phone() {
				return distributer_phone;
			}

			public void setDistributer_phone(String distributer_phone) {
				this.distributer_phone = distributer_phone;
			}
		}

		public static class OrderProductsBean {
			/**
			 * id : 97
			 * product_id : 689
			 * name : 科技有限公司之毛秀兰
			 * num : 9
			 * packing_box_num : 0
			 * packing_box_price : 6842
			 * specs : []
			 * price : 31865
			 * remark : 网络有限公司信息有限公司
			 * created_at : 2017-08-21 15:16:24
			 * updated_at : 2017-08-21 15:16:24
			 */

			private int id;
			private int product_id;
			private String name;
			private int num;
			private int packing_box_num;
			private int packing_box_price;
			private int price;
			private String remark;
			private String created_at;
			private String updated_at;
			private List<?> specs;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getProduct_id() {
				return product_id;
			}

			public void setProduct_id(int product_id) {
				this.product_id = product_id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getNum() {
				return num;
			}

			public void setNum(int num) {
				this.num = num;
			}

			public int getPacking_box_num() {
				return packing_box_num;
			}

			public void setPacking_box_num(int packing_box_num) {
				this.packing_box_num = packing_box_num;
			}

			public int getPacking_box_price() {
				return packing_box_price;
			}

			public void setPacking_box_price(int packing_box_price) {
				this.packing_box_price = packing_box_price;
			}

			public int getPrice() {
				return price;
			}

			public void setPrice(int price) {
				this.price = price;
			}

			public String getRemark() {
				return remark;
			}

			public void setRemark(String remark) {
				this.remark = remark;
			}

			public String getCreated_at() {
				return created_at;
			}

			public void setCreated_at(String created_at) {
				this.created_at = created_at;
			}

			public String getUpdated_at() {
				return updated_at;
			}

			public void setUpdated_at(String updated_at) {
				this.updated_at = updated_at;
			}

			public List<?> getSpecs() {
				return specs;
			}

			public void setSpecs(List<?> specs) {
				this.specs = specs;
			}
		}
	}
}
