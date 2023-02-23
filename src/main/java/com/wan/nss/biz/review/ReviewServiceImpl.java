package com.nss.review;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDAO ReviewDAO;
	
	@Override
	public ArrayList<ReviewVO> selectAll(ReviewVO rvo){
		return ReviewDAO.selectAll(rvo);
	}
	@Override
	public boolean insert(ReviewVO rvo) {
		return ReviewDAO.insert(rvo);
	}
	@Override
	public boolean delete(ReviewVO rvo) {
		return ReviewDAO.delete(rvo);
	}
}
