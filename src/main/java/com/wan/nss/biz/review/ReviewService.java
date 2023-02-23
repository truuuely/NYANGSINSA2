package com.nss.review;

import java.util.ArrayList;

import com.nss.product.ProductVO;

public interface ReviewService {
	public ArrayList<ReviewVO> selectAll(ReviewVO rvo);
	public boolean insert(ReviewVO rvo);
	public boolean delete(ReviewVO rvo);
}
