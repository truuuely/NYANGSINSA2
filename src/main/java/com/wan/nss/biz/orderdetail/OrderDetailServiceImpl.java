package com.wan.nss.biz.orderdetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailDAO OrderDetailDAO;

	@Override
	public boolean insertOrderDetail(OrderDetailVO vo) {
		return OrderDetailDAO.insertOrderDetail(vo);
	}

	@Override
	public boolean updateOrderDetail(OrderDetailVO vo) {
		return OrderDetailDAO.updateOrderDetail(vo);
	}

	@Override
	public boolean deleteOrderDetail(OrderDetailVO vo) {
		return OrderDetailDAO.deleteOrderDetail(vo);
	}
	
	@Override
	public List<OrderDetailVO> selectAll(OrderDetailVO vo) {
		return OrderDetailDAO.selectAll(vo);
	}

	@Override
	public OrderDetailVO selectOne(OrderDetailVO vo) {
		return OrderDetailDAO.selectOne(vo);
	}

}
