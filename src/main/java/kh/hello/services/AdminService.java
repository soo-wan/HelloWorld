package kh.hello.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.hello.configuration.Configuration;
import kh.hello.dao.AdminDAO;
import kh.hello.dto.InquiryDTO;
import kh.hello.dto.InquiryReplyDTO;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adao;
	
	public int validLogin(String adminId, String password) {
		return adao.validLogin(adminId, password);
	}
	
	public int modifyInfo(String adminId, String password, String email) {
		return adao.modifyInfo(adminId, password, email);
	}
	
	public List<InquiryDTO> selectInquiryByPage(int start, int end){			
		return adao.selectInquiryByPage(start, end);
	}
	
	public List<String> getInquiryPageNavi(int currentPage) {				
		int recordTotalCount = adao.getInquiryTotal();
		int pageTotalCount = 0;
		
		if(recordTotalCount% Configuration.recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / Configuration.recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / Configuration.recordCountPerPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = (currentPage - 1) / Configuration.naviCountPerPage * Configuration.naviCountPerPage + 1;
		int endNavi = startNavi + (Configuration.naviCountPerPage - 1);
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		if(startNavi == 1) {
			needPrev = false;
		}
		boolean needNext = true;
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		List<String> pages = new ArrayList<>();
		if(needPrev) pages.add("<a class=page-link href='inquiryList?page=" + (startNavi - 1) + "'>< </a>");
		
		for(int i = startNavi; i <= endNavi; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("<a class=page-link href='inquiryList?page="+ i +"'>");
			sb.append(i + " ");
			sb.append("</a>");
			pages.add(sb.toString());
		}
		
		if(needNext) pages.add("<a class=page-link href='inquiryList?page=" + (endNavi + 1) + "'>> </a>");
	
		return pages;
	}
	
	public InquiryDTO inquiryDetailView(int seq) {
		return adao.inquiryDetailView(seq);
	}
	
	public InquiryReplyDTO writeInquiry(String reply, int boardSeq) {
		//1. 댓글 입력
		int result = adao.writeInquiry(reply, boardSeq);
		if(result > 0) {
			//2. 댓글 내용 받아오기 (1. 마지막 시퀀스 2. 댓글 내용)
			int seq = adao.getLatestReplySeq();
			return adao.getLatestReply(seq);
		}else {
			return null;
		}
		
	}
}





