package com.wan.nss.biz.orderDetail;

import java.util.List;

public interface OrderDetailService {
	public boolean insertOrderDetail(OrderDetailVO vo);
	public List<OrderDetailVO> selectAll(OrderDetailVO vo);
}
