package com.wan.nss.biz.order;

import java.util.List;

public interface OrderService {
	public boolean insertOrder(OrderVO vo);
	public boolean updateOrder(OrderVO vo);
	public boolean deleteOrder(OrderVO vo);
	public List<OrderVO> selectAll(OrderVO vo);
	public OrderVO selectOne(OrderVO vo);
}
