package com.bfdelivery.cavaliers.background.server.bean.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Panoo on 2017/8/6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetail {

	/**
	 * number : 000100000003
	 * shop_id : 1
	 * user_id : 3
	 * order_num : 1
	 * status : 2
	 * pay_status : 1
	 * pay_type : 1
	 * product_amount : 60
	 * service_amount : 10
	 * total_amount : 70
	 * coupon_amount : 0
	 * coupon_id : 0
	 * pay_amount : 70
	 * remark :
	 * tags : []
	 * distribute_time : 16:20
	 * distribute_status : 1
	 * can_take_coupons : 0
	 * created_at : 2017-10-06 17:53:37
	 * updated_at : 2017-10-09 14:40:18
	 * order_products : [{"id":2,"product_id":3,"name":"卡布奇诺","num":2,"packing_box_num":0,"packing_box_price":0,"specs":[{"name":"杯型","options":[{"value":"大杯","add_price":20}]}],"price":30,"remark":"","created_at":"2017-10-06 17:53:37","updated_at":"2017-10-06 17:53:37"}]
	 * shop : {"id":1,"name":"星巴克","banner":"https://bf.laowaishuosha.com/storage/shops/2017/10/11/dwnvyRcyxkQiAsfeIFVt3KXVDB2ySdtBDwiJrjSS.jpeg","description":"星巴克","longitude":"118.509710","latitude":"31.682490","address":"安徽省马鞍山市花山区八佰伴1楼","phone":"0555-2212727","status":1,"send_amount":0,"service_amount":0,"sell_times":{"monday":[["08:00:00","20:59:01"]],"tuesday":[["08:00:00","20:59:01"]],"wednesday":[["08:00:00","20:59:01"]],"thursday":[["08:00:00","20:59:01"]],"friday":[["08:00:00","20:59:01"]],"saturday":[["08:00:00","20:59:01"]],"sunday":[["08:00:00","20:59:01"]]},"average_minutes":10}
	 * address : {"order_id":2,"name":"周杰伦","sex":0,"mobile":"18787898789","post_code":"","province":"广东省","city":"深圳市","area":"福田区","detail":"福中三路石化大厦303","longitude":"114.057868","latitude":"22.543099","tag":"无"}
	 * distribute : {"id":3,"status":2,"distributer_id":6,"distributer_name":"疯子","distributer_phone":"13355556666","deleted_at":null,"created_at":"2017-10-09 14:40:18","updated_at":"2017-10-11 11:49:09"}
	 */

	private String number;
	private int shop_id;
	private int user_id;
	private int order_num;
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
	private int can_take_coupons;
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

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
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

	public int getCan_take_coupons() {
		return can_take_coupons;
	}

	public void setCan_take_coupons(int can_take_coupons) {
		this.can_take_coupons = can_take_coupons;
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

	public static class ShopBean {
		/**
		 * id : 1
		 * name : 星巴克
		 * banner : https://bf.laowaishuosha.com/storage/shops/2017/10/11/dwnvyRcyxkQiAsfeIFVt3KXVDB2ySdtBDwiJrjSS.jpeg
		 * description : 星巴克
		 * longitude : 118.509710
		 * latitude : 31.682490
		 * address : 安徽省马鞍山市花山区八佰伴1楼
		 * phone : 0555-2212727
		 * status : 1
		 * send_amount : 0
		 * service_amount : 0
		 * sell_times : {"monday":[["08:00:00","20:59:01"]],"tuesday":[["08:00:00","20:59:01"]],"wednesday":[["08:00:00","20:59:01"]],"thursday":[["08:00:00","20:59:01"]],"friday":[["08:00:00","20:59:01"]],"saturday":[["08:00:00","20:59:01"]],"sunday":[["08:00:00","20:59:01"]]}
		 * average_minutes : 10
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

		public static class SellTimesBean {
			private List<List<String>> monday;
			private List<List<String>> tuesday;
			private List<List<String>> wednesday;
			private List<List<String>> thursday;
			private List<List<String>> friday;
			private List<List<String>> saturday;
			private List<List<String>> sunday;

			public List<List<String>> getMonday() {
				return monday;
			}

			public void setMonday(List<List<String>> monday) {
				this.monday = monday;
			}

			public List<List<String>> getTuesday() {
				return tuesday;
			}

			public void setTuesday(List<List<String>> tuesday) {
				this.tuesday = tuesday;
			}

			public List<List<String>> getWednesday() {
				return wednesday;
			}

			public void setWednesday(List<List<String>> wednesday) {
				this.wednesday = wednesday;
			}

			public List<List<String>> getThursday() {
				return thursday;
			}

			public void setThursday(List<List<String>> thursday) {
				this.thursday = thursday;
			}

			public List<List<String>> getFriday() {
				return friday;
			}

			public void setFriday(List<List<String>> friday) {
				this.friday = friday;
			}

			public List<List<String>> getSaturday() {
				return saturday;
			}

			public void setSaturday(List<List<String>> saturday) {
				this.saturday = saturday;
			}

			public List<List<String>> getSunday() {
				return sunday;
			}

			public void setSunday(List<List<String>> sunday) {
				this.sunday = sunday;
			}
		}
	}

	public static class AddressBean {
		/**
		 * order_id : 2
		 * name : 周杰伦
		 * sex : 0
		 * mobile : 18787898789
		 * post_code :
		 * province : 广东省
		 * city : 深圳市
		 * area : 福田区
		 * detail : 福中三路石化大厦303
		 * longitude : 114.057868
		 * latitude : 22.543099
		 * tag : 无
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
	}

	public static class DistributeBean {
		/**
		 * id : 3
		 * status : 2
		 * distributer_id : 6
		 * distributer_name : 疯子
		 * distributer_phone : 13355556666
		 * deleted_at : null
		 * created_at : 2017-10-09 14:40:18
		 * updated_at : 2017-10-11 11:49:09
		 */

		private int id;
		private int status;
		private int distributer_id;
		private String distributer_name;
		private String distributer_phone;
		private Object deleted_at;
		private String created_at;
		private String updated_at;

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

		public String getDistributer_phone() {
			return distributer_phone;
		}

		public void setDistributer_phone(String distributer_phone) {
			this.distributer_phone = distributer_phone;
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
	}

	public static class OrderProductsBean {
		/**
		 * id : 2
		 * product_id : 3
		 * name : 卡布奇诺
		 * num : 2
		 * packing_box_num : 0
		 * packing_box_price : 0
		 * specs : [{"name":"杯型","options":[{"value":"大杯","add_price":20}]}]
		 * price : 30
		 * remark :
		 * created_at : 2017-10-06 17:53:37
		 * updated_at : 2017-10-06 17:53:37
		 */

		private int id;
		private int product_id;
		private String name;
		private int num;
		private int packing_box_num;
		private double packing_box_price;
		private int price;
		private String remark;
		private String created_at;
		private String updated_at;
		private List<SpecsBean> specs;

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

		public double getPacking_box_price() {
			return packing_box_price;
		}

		public void setPacking_box_price(double packing_box_price) {
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

		public List<SpecsBean> getSpecs() {
			return specs;
		}

		public void setSpecs(List<SpecsBean> specs) {
			this.specs = specs;
		}

		public static class SpecsBean {
			/**
			 * name : 杯型
			 * options : [{"value":"大杯","add_price":20}]
			 */

			private String name;
			private List<OptionsBean> options;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public List<OptionsBean> getOptions() {
				return options;
			}

			public void setOptions(List<OptionsBean> options) {
				this.options = options;
			}

			public static class OptionsBean {
				/**
				 * value : 大杯
				 * add_price : 20
				 */

				private String value;
				private int add_price;

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}

				public int getAdd_price() {
					return add_price;
				}

				public void setAdd_price(int add_price) {
					this.add_price = add_price;
				}
			}
		}
	}
}
