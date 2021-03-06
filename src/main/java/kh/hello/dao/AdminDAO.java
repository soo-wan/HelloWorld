package kh.hello.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.hello.dto.ForcedOutMemberDTO;
import kh.hello.dto.InquiryDTO;
import kh.hello.dto.InquiryReplyDTO;
import kh.hello.dto.MemberDTO;
import kh.hello.dto.NoticeDTO;

@Repository
public class AdminDAO {

	@Autowired
	private SqlSessionTemplate jdbc;
	
	public int validLogin(String adminId, String password) {//관리자 로그인
		Map<String, String> param = new HashMap<>();
		param.put("adminId", adminId);
		param.put("password", password);
		return jdbc.selectOne("Admin.validLogin", param);
	}
	
	public String getAdminEmail(String adminId) {
		return jdbc.selectOne("Admin.getAdminEmail", adminId);
	}
	
	public int modifyInfo(String adminId, String password, String email) {//비밀번호/이메일 수정
		Map<String, String> param = new HashMap<>();
		param.put("adminId", adminId);
		param.put("password", password);
		param.put("email", email);
		return jdbc.update("Admin.modifyInfo", param);
	}
	
	public List<InquiryDTO> selectInquiryByPage(int start, int end){//일대일 문의 게시판 글 목록 받아오기(페이징O)
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		return jdbc.selectList("Admin.selectInquiryByPage", param);		
	}
	
	public int getInquiryTotal() {//일대일 문의 총게시글 수
		return jdbc.selectOne("Admin.getInquiryTotal");
	}
	
	public InquiryDTO inquiryDetailView(int seq) {//일대일 문의 디테일뷰
		return jdbc.selectOne("Admin.inquiryDetailView", seq);
	}
	
	public List<InquiryReplyDTO> getInquiryReply(int boardSeq) {//게시글 댓글 받아오기
		return jdbc.selectList("Admin.getInquiryReply", boardSeq);
	}
	
	public int writeInquiry(String reply, int boardSeq) {//댓글쓰기(관리자만 가능)
		Map<String, Object> param = new HashMap<>();
		param.put("reply", reply);
		param.put("boardSeq", boardSeq);
		return jdbc.insert("Admin.writeInquiry", param);
	}
	public int plusInquiryCount(int seq) {//일대일 문의 댓글수 +1
		return jdbc.update("Admin.plusInquiryCount", seq);
	}
	public int getLatestReplySeq() {//일대일문의 가장 마지막 댓글  seq 받아오기
		return jdbc.selectOne("Admin.getLatestReplySeq");
	}
	
	public InquiryReplyDTO getLatestReply(int seq) {//일대일문의 가장 마지막 댓글 받아오기
		return jdbc.selectOne("Admin.getLatestReply", seq);
	}
	
	public int deleteInquiryReply(int seq) {//일대일문의 댓글 삭제
		return jdbc.delete("Admin.deleteInquiryReply", seq);
	}
	
	public int minusInquiryCount(int seq) {
		return jdbc.update("Admin.minusInquiryCount", seq);
	}
	
	public List<MemberDTO> memberListByPage(int start, int end){//회원목록 페이지별로 받아오기
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		return jdbc.selectList("Admin.memberListByPage", param);
	}
	
	public int getMemberTotal(){//총 회원수
		return jdbc.selectOne("Admin.getMemberTotal");
	}
	
	public MemberDTO getMemberInfo(String id) {//회원 정보 받기
		return jdbc.selectOne("Admin.getMemberInfo", id);
	}
	
	public int memberModify(String id, String email, String phone) {//회원 이메일,전화번호 수정
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("email", email);
		param.put("phone", phone);
		return jdbc.update("Admin.memberModify", param);
	}
	
	public int memberStop(String id) {//활동정지(레벨1로 만들기)
		return jdbc.update("Admin.memberStop", id);
	}
	
	public int memberStart(String id) {//활동정지 해제(레벨2로 만들기)
		return jdbc.update("Admin.memberStart", id);
	}
	
	public int memberOut(String id) {//회원 강제탈퇴
		return jdbc.update("Admin.memberOut", id);
	}
	
	public String getEmailById(String id) {
		return jdbc.selectOne("Admin.getEmailById", id);
	}
	
	public int memberOutList(String id, String email, String reason) {//강퇴 아이디와 사유 테이블에 기록
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("email", email);
		param.put("reason", reason);		
		return jdbc.insert("Admin.memberOutList", param);				
	}
	
	public int memberUp(String id) {
		return jdbc.update("Admin.memberUp", id);
	}
	
	public List<ForcedOutMemberDTO> forcedOutListByPage(int start, int end){//강퇴 아이디 리스트 페이지 별로 받아오기
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		return jdbc.selectList("Admin.forcedOutListByPage", param);
	}
	
	public int getForcedOutTotal() {//강퇴 회원 총합
		return jdbc.selectOne("Admin.getForcedOutTotal");
	}
	
	public int delEmail(int seq) {
		return jdbc.update("Admin.delEmail", seq);
	}
	
	public int forcedOutDel(int seq) {//강퇴 목록에서 지우기
		return jdbc.delete("Admin.forcedOutDel", seq);
	}
	
	public List<MemberDTO> getSearchMemberListByPage(String col, String searchWord, int start, int end){//멤버 검색해서 결과 페이지별로 받아오기
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		param.put("col", col);
		param.put("searchWord", "%"+searchWord+"%");
		return jdbc.selectList("Admin.getSearchMemberListByPage", param);
	}
	
	public int getSearchMemberResultTotal(String col, String searchWord) {//멤버 검색 결과 총합
		Map<String, String> param = new HashMap<>();
		param.put("col", col);
		param.put("searchWord", "%"+searchWord+"%");
		return jdbc.selectOne("Admin.getSearchMemberResultTotal", param);
	}
	
	public List<MemberDTO> getBlackList(int start, int end){//블랙리스트 페이지 별로 받아오기
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		return jdbc.selectList("Admin.getBlackList", param);
	}
	
	public int getBlackListTotal() {//블랙리스트 총 갯수
		return jdbc.selectOne("Admin.getBlackListTotal");
	}
	
	public List<MemberDTO> getSearchBlackListByPage(String col, String searchWord, int start, int end){//블랙리스트 검색 페이지별로 받아오기
		Map<String, Object> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		param.put("col", col);
		param.put("searchWord", "%"+searchWord+"%");
		return jdbc.selectList("Admin.getSearchBlackListByPage", param);
	}
	
	public int getSearchBlackResultTotal(String col, String searchWord) {//블랙리스트 검색결과 총 갯수
		Map<String, String> param = new HashMap<>();
		param.put("col", col);
		param.put("searchWord", "%"+searchWord+"%");
		return jdbc.selectOne("Admin.getSearchBlackResultTotal", param);
	}
	
	public List<NoticeDTO> noticeMainListByPage(int start, int end){
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		return jdbc.selectList("Admin.noticeMainListByPage", param);
	}
	
	public int getNoticeTotal() {
		return jdbc.selectOne("Admin.getNoticeTotal");
	}
	
	public int insertImg(int boardSeq, String sysName) {
		Map<String, Object> param = new HashMap<>();
		param.put("boardSeq", boardSeq);
		param.put("sysName", sysName);
		return jdbc.insert("Admin.insertImg", param);
	}
	
	public int getNoticeSeq() {
		return jdbc.selectOne("Admin.getNoticeSeq");
	}
	
	public int writeNotice(NoticeDTO dto) {
		return jdbc.insert("Admin.writeNotice", dto);
	}
	
	public NoticeDTO noticeDetailView(int seq) {
		return jdbc.selectOne("Admin.noticeDetailView", seq);
	}
	
	public int modifyNotice(NoticeDTO dto) {
		return jdbc.update("Admin.modifyNotice", dto);
	}
	
	public int deleteNotice(int seq) {
		return jdbc.delete("Admin.deleteNotice", seq);
	}
	
	public List<String> getImgsByBoardSeq(int boardSeq){
		return jdbc.selectList("Admin.getImgsByBoardSeq", boardSeq);
	}
	
	public int delImgsByBoardSeq(int boardSeq) {
		return jdbc.delete("Admin.delImgsByBoardSeq", boardSeq);
	}
	
	public String validOpen(String id) {
		return jdbc.selectOne("Admin.validOpen", id);
	}
	
	public int ifmOpenModify(String id, String check) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("check", check);
		return jdbc.update("Admin.ifmOpenModify", param);
	}	
}
















