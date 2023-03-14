package com.wan.nss.biz.report;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportDAO reportDAO;
	
	@Override
	public ArrayList<ReportVO> selectAll(ReportVO rpVo){
		return reportDAO.selectAll(rpVo);
	}

	@Override
	public ReportVO selectOne(ReportVO rpVo) {
		return reportDAO.selectOne(rpVo);
	}

	@Override
	public boolean update(ReportVO rpVo) {
		return reportDAO.update(rpVo);
	}
}
