package com.bfdelivery.cavaliers.dataset;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Panoo on 2017/8/14.
 */

public class ListOutlineData implements Parcelable {

	private String orderId;
	private String mRstName;
	private String mRstAddr;
	private String mUsrName;
	private String mUsrAddr;

	public ListOutlineData() {
	}

	protected ListOutlineData(Parcel in) {
		orderId = in.readString();
		mRstName = in.readString();
		mRstAddr = in.readString();
		mUsrName = in.readString();
		mUsrAddr = in.readString();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRstName() {
		return mRstName;
	}

	public void setRstName(String rstName) {
		mRstName = rstName;
	}

	public String getRstAddr() {
		return mRstAddr;
	}

	public void setRstAddr(String rstAddr) {
		mRstAddr = rstAddr;
	}

	public String getUsrName() {
		return mUsrName;
	}

	public void setUsrName(String usrName) {
		mUsrName = usrName;
	}

	public String getUsrAddr() {
		return mUsrAddr;
	}

	public void setUsrAddr(String usrAddr) {
		mUsrAddr = usrAddr;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(orderId);
		dest.writeString(mRstName);
		dest.writeString(mRstAddr);
		dest.writeString(mUsrName);
		dest.writeString(mUsrAddr);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ListOutlineData> CREATOR = new Creator<ListOutlineData>() {
		@Override
		public ListOutlineData createFromParcel(Parcel in) {
			return new ListOutlineData(in);
		}

		@Override
		public ListOutlineData[] newArray(int size) {
			return new ListOutlineData[size];
		}
	};
}
