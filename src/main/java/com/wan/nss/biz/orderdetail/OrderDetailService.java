package com.wan.nss.biz.orderdetail;

import java.util.List;

public interface OrderDetailService {
	public boolean insertOrderDetail(OrderDetailVO vo);
	public List<OrderDetailVO> selectAll(OrderDetailVO vo);
}
