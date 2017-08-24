package com.bfdelivery.cavaliers.dataset;

/**
 * Created by Panoo on 2017/8/24.
 */

public class NewOrderPushMsg {

	/**
	 * type : neworder
	 * number : order number
	 * shop : {"name":"名称","address":"地址"}
	 * address : {"name":"名称","sex":"性别","mobile":" 手机号","province":"","city":"","area":"","detail":"","longitude":"number","latitude":"number","tag":""}
	 */

	private String type;
	private String number;
	private ShopBean shop;
	private AddressBean address;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public static class ShopBean {
		/**
		 * name : 名称
		 * address : 地址
		 */

		private String name;
		private String address;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	}

	public static class AddressBean {
		/**
		 * name : 名称
		 * sex : 性别
		 * mobile :  手机号
		 * province :
		 * city :
		 * area :
		 * detail :
		 * longitude : number
		 * latitude : number
		 * tag :
		 */

		private String name;
		private String sex;
		private String mobile;
		private String province;
		private String city;
		private String area;
		private String detail;
		private String longitude;
		private String latitude;
		private String tag;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
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
}
