package com.wan.nss.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderTableService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderTableDAO;

	@Override
	public boolean insertOrder(OrderVO vo) {
		return orderTableDAO.insert(vo);
	}

	@Override
	public boolean updateOrder(OrderVO vo) {
		return orderTableDAO.update(vo);
	}

	@Override
	public boolean deleteOrder(OrderVO vo) {
		return orderTableDAO.delete(vo);
	}
	
	@Override
	public List<OrderVO> selectAll(OrderVO vo) {
		return orderTableDAO.selectAll(vo);
	}

	@Override
	public OrderVO selectOne(OrderVO vo) {
		return orderTableDAO.selectOne(vo);
	}

}
