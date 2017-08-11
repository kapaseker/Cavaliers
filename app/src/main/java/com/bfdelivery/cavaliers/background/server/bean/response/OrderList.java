package com.bfdelivery.cavaliers.background.server.bean.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Panoo on 2017/8/6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderList {

	/**
	 * current_page : 1
	 * data : [{"number":"000100000002","shop_id":1,"user_id":2,"status":0,"pay_status":0,"pay_type":1,"product_amount":38700,"service_amount":0,"total_amount":38700,"coupon_amount":0,"coupon_id":0,"pay_amount":38700,"remark":"没啥备注","tags":[],"distribute_status":1,"created_at":"2017-08-07 11:07:50","updated_at":"2017-08-08 20:53:39","order_products":[{"id":1,"product_id":3,"name":"信息有限公司之滕春梅","num":3,"options":[{"name":"娄","value":"伟","add_price":0}],"price":7900,"remark":"","created_at":"2017-08-07 11:07:50","updated_at":"2017-08-07 11:07:50"},{"id":2,"product_id":5,"name":"信息有限公司之柏宁","num":3,"options":[],"price":5000,"remark":"","created_at":"2017-08-07 11:07:50","updated_at":"2017-08-07 11:07:50"}],"address":{"order_id":1,"name":"丁志明","mobile":"13611538449","post_code":"138400","province":"上海市","city":"济南","area":"双滦区","detail":"南方周末","longitude":"87.293129","latitude":"18.436163","tag":"家里"},"distribute":{"id":1,"status":1,"distributer_id":2,"distributer_name":"王小海","deleted_at":null,"created_at":"2017-08-08 20:53:39","updated_at":"2017-08-08 20:53:39"}}]
	 * last_page : 1
	 * per_page : 15
	 * total : 1
	 */

	private int current_page;
	private int last_page;
	private int per_page;
	private int total;
	private List<DataBean> data;

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getLast_page() {
		return last_page;
	}

	public void setLast_page(int last_page) {
		this.last_page = last_page;
	}

	public int getPer_page() {
		return per_page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
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
		 * number : 000100000002
		 * shop_id : 1
		 * user_id : 2
		 * status : 0
		 * pay_status : 0
		 * pay_type : 1
		 * product_amount : 38700
		 * service_amount : 0
		 * total_amount : 38700
		 * coupon_amount : 0
		 * coupon_id : 0
		 * pay_amount : 38700
		 * remark : 没啥备注
		 * tags : []
		 * distribute_status : 1
		 * created_at : 2017-08-07 11:07:50
		 * updated_at : 2017-08-08 20:53:39
		 * order_products : [{"id":1,"product_id":3,"name":"信息有限公司之滕春梅","num":3,"options":[{"name":"娄","value":"伟","add_price":0}],"price":7900,"remark":"","created_at":"2017-08-07 11:07:50","updated_at":"2017-08-07 11:07:50"},{"id":2,"product_id":5,"name":"信息有限公司之柏宁","num":3,"options":[],"price":5000,"remark":"","created_at":"2017-08-07 11:07:50","updated_at":"2017-08-07 11:07:50"}]
		 * address : {"order_id":1,"name":"丁志明","mobile":"13611538449","post_code":"138400","province":"上海市","city":"济南","area":"双滦区","detail":"南方周末","longitude":"87.293129","latitude":"18.436163","tag":"家里"}
		 * distribute : {"id":1,"status":1,"distributer_id":2,"distributer_name":"王小海","deleted_at":null,"created_at":"2017-08-08 20:53:39","updated_at":"2017-08-08 20:53:39"}
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
		private int distribute_status;
		private String created_at;
		private String updated_at;
		private AddressBean address;
		private DistributeBean distribute;
		private List<?> tags;
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

		public List<?> getTags() {
			return tags;
		}

		public void setTags(List<?> tags) {
			this.tags = tags;
		}

		public List<OrderProductsBean> getOrder_products() {
			return order_products;
		}

		public void setOrder_products(List<OrderProductsBean> order_products) {
			this.order_products = order_products;
		}

		public static class AddressBean {
			/**
			 * order_id : 1
			 * name : 丁志明
			 * mobile : 13611538449
			 * post_code : 138400
			 * province : 上海市
			 * city : 济南
			 * area : 双滦区
			 * detail : 南方周末
			 * longitude : 87.293129
			 * latitude : 18.436163
			 * tag : 家里
			 */

			private int order_id;
			private String name;
			private String mobile;
			private String post_code;
			private String province;
			private String city;
			private String area;
			private String detail;
			private String longitude;
			private String latitude;
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

			public String getLongitude() {
				return longitude;
			}

			public void setLongitude(String longitude) {
				this.longitude = longitude;
			}

			public String getLatitude() {
				return latitude;
			}

			public void setLatitude(String latitude) {
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
			 * id : 1
			 * status : 1
			 * distributer_id : 2
			 * distributer_name : 王小海
			 * deleted_at : null
			 * created_at : 2017-08-08 20:53:39
			 * updated_at : 2017-08-08 20:53:39
			 */

			private int id;
			private int status;
			private int distributer_id;
			private String distributer_name;
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
			 * id : 1
			 * product_id : 3
			 * name : 信息有限公司之滕春梅
			 * num : 3
			 * options : [{"name":"娄","value":"伟","add_price":0}]
			 * price : 7900
			 * remark :
			 * created_at : 2017-08-07 11:07:50
			 * updated_at : 2017-08-07 11:07:50
			 */

			private int id;
			private int product_id;
			private String name;
			private int num;
			private int price;
			private String remark;
			private String created_at;
			private String updated_at;
			private List<OptionsBean> options;

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

			public List<OptionsBean> getOptions() {
				return options;
			}

			public void setOptions(List<OptionsBean> options) {
				this.options = options;
			}

			public static class OptionsBean {
				/**
				 * name : 娄
				 * value : 伟
				 * add_price : 0
				 */

				private String name;
				private String value;
				private int add_price;

				public String getName() {
					return name;
				}

				public void setName(String name) {
					this.name = name;
				}

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
