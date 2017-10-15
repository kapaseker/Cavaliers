package com.bfdelivery.cavaliers.util;

import com.bfdelivery.cavaliers.background.server.bean.response.OrderDetail;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 结果处理器
 */
public class ResultProcessor {

	/**
	 * 将产品结果的规格抽取出来。
	 *
	 * @param bean
	 * @return
	 */
	public static final String extractSpec(OrderDetail.OrderProductsBean bean) {

		List<String> specList = new ArrayList<>();

		List<OrderDetail.OrderProductsBean.SpecsBean> specsBeanList = bean.getSpecs();
		if (specsBeanList != null || specsBeanList.size() > 0) {
			for (OrderDetail.OrderProductsBean.SpecsBean item : specsBeanList) {
				List<OrderDetail.OrderProductsBean.SpecsBean.OptionsBean> beanList = item.getOptions();
				if (beanList != null || beanList.size() > 0) {
					for (OrderDetail.OrderProductsBean.SpecsBean.OptionsBean option : beanList) {
						specList.add(option.getValue());
					}
				}
			}
		}

		return StringUtils.join(specList, ",");
	}

	/**
	 * 获取所有商品的打包费用
	 *
	 * @param products
	 * @return
	 */
	public static final double extractPackageFee(List<OrderDetail.OrderProductsBean> products) {

		double packageFee = 0;
		for (OrderDetail.OrderProductsBean item : products) {
			packageFee += item.getPacking_box_price() * item.getPacking_box_num();
		}

		return packageFee;
	}
}
