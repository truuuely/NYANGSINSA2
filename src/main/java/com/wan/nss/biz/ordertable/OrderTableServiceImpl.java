package com.wan.nss.biz.ordertable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderTableService")
public class OrderTableServiceImpl implements OrderTableService {
	
	@Autowired
	private OrderTableDAO OrderTableDAO;

	@Override
	public boolean insertOrder(OrderTableVO vo) {
		return OrderTableDAO.insertOrder(vo);
	}

	@Override
	public boolean updateOrder(OrderTableVO vo) {
		return OrderTableDAO.updateOrder(vo);
	}

	@Override
	public boolean deleteOrder(OrderTableVO vo) {
		return OrderTableDAO.deleteOrder(vo);
	}
	
	@Override
	public List<OrderTableVO> selectAll(OrderTableVO vo) {
		return OrderTableDAO.selectAll(vo);
	}

	@Override
	public OrderTableVO selectOne(OrderTableVO vo) {
		return OrderTableDAO.selectOne(vo);
	}

}
