package com.wan.nss.biz.ordertable;

import java.util.List;

public interface OrderTableService {
	public boolean insertOrder(OrderTableVO vo);
	public boolean updateOrder(OrderTableVO vo);
	public boolean deleteOrder(OrderTableVO vo);
	public List<OrderTableVO> selectAll(OrderTableVO vo);
	public OrderTableVO selectOne(OrderTableVO vo);
}
