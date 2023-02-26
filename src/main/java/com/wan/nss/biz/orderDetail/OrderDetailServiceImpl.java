package com.wan.nss.biz.orderDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;

	@Override
	public boolean insertOrderDetail(OrderDetailVO vo) {
		return orderDetailDAO.insert(vo);
	}
	
	@Override
	public List<OrderDetailVO> selectAll(OrderDetailVO vo) {
		return orderDetailDAO.selectAll(vo);
	}

}
