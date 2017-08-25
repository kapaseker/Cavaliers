package com.bfdelivery.cavaliers.background.server.bean.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Panoo on 2017/8/6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetail {


	/**
	 * number : paiKZ6DLvkG6
	 * shop_id : 1
	 * user_id : 1
	 * status : 0
	 * pay_status : 0
	 * pay_type : 0
	 * product_amount : 432548
	 * service_amount : 378
	 * total_amount : 34051
	 * coupon_amount : 39180
	 * coupon_id : 0
	 * pay_amount : 401667
	 * remark : 彩信发送动人一刻波导手机，手机中的战斗机幸福生活SOHU：足迹生活每一天
	 * tags : 灵活，让篮球场不再是一个平面
	 * distribute_time : 25107uruk1
	 * distribute_status : 0
	 * created_at : 2017-08-21 15:16:24
	 * updated_at : 2017-08-21 15:16:24
	 * order_products : [{"id":98,"product_id":898,"name":"传媒有限公司之伍文娟","num":8,"packing_box_num":0,"packing_box_price":4232,"specs":[],"price":68750,"remark":"科技有限公司传媒有限公司","created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}]
	 * shop : {"id":1,"name":"鸿睿思博加盟店","banner":"http://lorempixel.com/640/480/?25977","description":"为了她的节日，献上您纯金般的心！倾诉冬日暖语晚报，不晚报以帽取人！","longitude":"123.452878","latitude":"41.725355","address":"事业我一定争取，对你我从未放弃!","phone":"18767863835","status":0,"send_amount":4556,"service_amount":6730,"sell_times":{"friday":[["13 30","15 15"],["23 30","23 15"]]},"average_minutes":25}
	 * address : {"order_id":98,"name":"郑建","sex":1,"mobile":"18028840099","post_code":"022400","province":"黑龙江省","city":"成都","area":"沙市区","detail":"一呼天下应","longitude":"31.213005","latitude":"99.242325","tag":"公司"}
	 * distribute : {"id":98,"status":2,"distributer_id":1,"distributer_name":"配送员姓名","deleted_at":null,"created_at":"2017-08-21 15:16:24","updated_at":"2017-08-21 15:16:24"}
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
	private String tags;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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
		private SellTimesBean sell_times;
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

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAddress() {
			return address;
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

		public SellTimesBean getSell_times() {
			return sell_times;
		}

		public void setSell_times(SellTimesBean sell_times) {
			this.sell_times = sell_times;
		}

		public int getAverage_minutes() {
			return average_minutes;
		}

		public void setAverage_minutes(int average_minutes) {
			this.average_minutes = average_minutes;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
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

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AddressBean {
		/**
		 * order_id : 98
		 * name : 郑建
		 * sex : 1
		 * mobile : 18028840099
		 * post_code : 022400
		 * province : 黑龙江省
		 * city : 成都
		 * area : 沙市区
		 * detail : 一呼天下应
		 * longitude : 31.213005
		 * latitude : 99.242325
		 * tag : 公司
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

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DistributeBean {
		/**
		 * id : 98
		 * status : 2
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
		private Object deleted_at;
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

		public Object getDeleted_at() {
			return deleted_at;
		}

		public void setDeleted_at(Object deleted_at) {
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
		 * id : 98
		 * product_id : 898
		 * name : 传媒有限公司之伍文娟
		 * num : 8
		 * packing_box_num : 0
		 * packing_box_price : 4232
		 * specs : []
		 * price : 68750
		 * remark : 科技有限公司传媒有限公司
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
//		private List<?> specs;

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

	}
}
