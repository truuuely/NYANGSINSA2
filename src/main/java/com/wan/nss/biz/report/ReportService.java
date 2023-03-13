package com.wan.nss.biz.report;

import java.util.ArrayList;

public interface ReportService {
	public ArrayList<ReportVO> selectAll(ReportVO rpVo);
	public ReportVO selectOne(ReportVO rpVo);
	public boolean update(ReportVO rpVo);
}
