package com.wan.nss.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderTableService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO OrderTableDAO;

	@Override
	public boolean insertOrder(OrderVO vo) {
		return OrderTableDAO.insertOrder(vo);
	}

	@Override
	public boolean updateOrder(OrderVO vo) {
		return OrderTableDAO.updateOrder(vo);
	}

	@Override
	public boolean deleteOrder(OrderVO vo) {
		return OrderTableDAO.deleteOrder(vo);
	}
	
	@Override
	public List<OrderVO> selectAll(OrderVO vo) {
		return OrderTableDAO.selectAll(vo);
	}

	@Override
	public OrderVO selectOne(OrderVO vo) {
		return OrderTableDAO.selectOne(vo);
	}

}
