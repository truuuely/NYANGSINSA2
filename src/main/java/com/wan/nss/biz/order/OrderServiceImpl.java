package com.wan.nss.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO OrderDAO;

	@Override
	public boolean insertOrder(OrderVO vo) {
		return OrderDAO.insertOrder(vo);
	}

	@Override
	public boolean updateOrder(OrderVO vo) {
		return OrderDAO.updateOrder(vo);
	}

	@Override
	public boolean deleteOrder(OrderVO vo) {
		return OrderDAO.deleteOrder(vo);
	}
	
	@Override
	public List<OrderVO> selectAll(OrderVO vo) {
		return OrderDAO.selectAll(vo);
	}

	@Override
	public OrderVO selectOne(OrderVO vo) {
		return OrderDAO.selectOne(vo);
	}

}
